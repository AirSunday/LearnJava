package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GradeDto;
import org.example.Entity.*;
import org.example.Exception.NotFoundException;
import org.example.Repository.GradeRepository;
import org.example.Repository.StudentRepository;
import org.example.Repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;
    private final StudentRepository studentRepository;

    public String save(@Valid GradeDto gradeDto) throws NotFoundException {
        StudentEntity student = studentRepository.findByNameAndFamilyAndGroup_Number(
                gradeDto.getNameStudent(),
                gradeDto.getFamilyStudent(),
                gradeDto.getGroupStudent()
        );

        if(student == null){
            throw new NotFoundException("Студент не найден");
        }

        SubjectEntity subject = subjectRepository.findByName(gradeDto.getSubject());

        if(subject == null){
            throw new NotFoundException("Предмет не найден");
        }

        GradeEntity searchGrade = gradeRepository.findById_StudentAndId_Subject(student, subject);

        if(searchGrade != null){
            searchGrade.setGrade(gradeDto.getGrade());
            gradeRepository.save(searchGrade);
            return searchGrade.getStudent().getId() + "; " + searchGrade.getSubject().getId();
        }

        GradeEntity grade = new GradeEntity();
        grade.setGrade(gradeDto.getGrade());

        GradeEntity.GradeId gradeId = new GradeEntity.GradeId();
        gradeId.setStudent(student);
        gradeId.setSubject(subject);
        grade.setId(gradeId);

        gradeRepository.save(grade);

        return grade.getStudent().getId() + "; " + grade.getSubject().getId();
    }

}
