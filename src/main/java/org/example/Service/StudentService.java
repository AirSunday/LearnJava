package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupSaveReq;
import org.example.Dto.StudentSaveReq;
import org.example.Entity.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class StudentService {

    private final GroupService groupService;
    private final GradeService gradeService;
    private final SubjectService subjectService;
    private final GradeRepository gradeRepository;
    private final SubjectRepository subjectRepository;

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public Long save(@Valid StudentSaveReq req) {

        GroupEntity group = groupRepository.search(req.getGroup_number());

        if(group == null){
            throw new RuntimeException("Группа не найдена");
        }

        StudentEntity student = new StudentEntity();
        student.setName(req.getName());
        student.setFamily(req.getFamily());
        student.setAge(req.getAge());
        student.setGroup(group);
        student.setMidGrade();

        studentRepository.save(student);

        return student.getId();
    }

}
