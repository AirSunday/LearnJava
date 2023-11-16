package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.StudentDto;
import org.example.Entity.*;
import org.example.Exception.NotFoundException;
import org.example.Repository.GroupRepository;
import org.example.Repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    public Long save(@Valid StudentDto studentDto) throws NotFoundException {

        GroupEntity group = groupRepository.findByNumber(studentDto.getGroup_number());

        if(group == null){
            throw new NotFoundException("Группа не найдена");
        }

        StudentEntity student = new StudentEntity();
        student.setName(studentDto.getName());
        student.setFamily(studentDto.getFamily());
        student.setAge(studentDto.getAge());
        student.setGroup(group);

        studentRepository.save(student);

        return student.getId();
    }

}
