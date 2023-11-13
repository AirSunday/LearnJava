package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GradeSaveReq;
import org.example.Entity.*;
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

    public Long save(@Valid GradeSaveReq req) {
        StudentEntity student = studentRepository.search(
                req.getNameStudent(),
                req.getFamilyStudent(),
                req.getGroupStudent()
        );

        if(student == null){
            throw new RuntimeException("Студент не найден");
        }

        SubjectType subjectType = SubjectType.fromName(req.getSubject());
        SubjectEntity subject = subjectRepository.search(subjectType);

        if(subject == null){
            throw new RuntimeException("Предмет не найден");
        }

        GradeEntity searchGrade = gradeRepository.search(student, subject);

        if(searchGrade != null){
            searchGrade.setGrade(req.getGrade());
            gradeRepository.save(searchGrade);
            return searchGrade.getId();
        }

        GradeEntity grade = new GradeEntity();
        grade.setStudent(student);
        grade.setSubject(subject);
        grade.setGrade(req.getGrade());

        gradeRepository.save(grade);

        return grade.getId();
    }

}
