package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupDto;
import org.example.Dto.ListResponse;
import org.example.Dto.StudentMidGradeDTO;
import org.example.Entity.GroupEntity;
import org.example.Exception.NotFoundException;
import org.example.Repository.GradeRepository;
import org.example.Repository.GroupRepository;
import org.example.Repository.StudentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;

    public Long save(@Valid GroupDto groupDto) {
        GroupEntity group = new GroupEntity();
        group.setNumber(groupDto.getNumber());

        GroupEntity result = groupRepository.findByNumber(groupDto.getNumber());
        if(result == null) {
            groupRepository.save(group);
            return group.getId();
        }

        return result.getId();
    }

    public ListResponse<StudentMidGradeDTO> getListStudentByGroup(int number, int offset, int count) {

        Pageable pageable = PageRequest.of(offset-1, count);
        Page<StudentMidGradeDTO> studentMidGradePage = gradeRepository.findStudentMidGradeByGroupNumber(number, pageable);
        List<StudentMidGradeDTO> studentMidGradeList = studentMidGradePage.getContent();

        long totalElements = studentMidGradePage.getTotalElements();

        return new ListResponse<>(studentMidGradeList, totalElements);

    }
}
