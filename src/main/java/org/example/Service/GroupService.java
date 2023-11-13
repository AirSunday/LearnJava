package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupSaveReq;
import org.example.Entity.GroupEntity;
import org.example.Entity.StudentEntity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;


@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;

    public Long save(@Valid GroupSaveReq req) {
        GroupEntity group = new GroupEntity();
        group.setNumber(req.getNumber());

        GroupEntity result = groupRepository.search(req.getNumber());
        if(result == null) {
            groupRepository.save(group);
            return group.getId();
        }

        return result.getId();
    }

    public List<StudentEntity> getListStudentByGroup(int groupNumber, int offset, int count){
        GroupEntity group = groupRepository.search(groupNumber);

        if(group == null){
            throw new RuntimeException("Группы не существует");
        }

        Pageable pageable = PageRequest.of(offset-1, count);
        List<StudentEntity> students = studentRepository.searchByNumber(group, pageable);

        return students;
    }
}
