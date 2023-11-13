package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupSaveReq;
import org.example.Dto.ListResponse;
import org.example.Dto.StudentMidGradeRes;
import org.example.Entity.GroupEntity;
import org.example.Entity.StudentEntity;
import org.example.Repository.GroupRepository;
import org.example.Repository.StudentRepository;
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

    public ListResponse<StudentMidGradeRes> getListStudentByGroup(int number, int offset, int count){
        GroupEntity group = groupRepository.search(number);

        if(group == null){
            throw new RuntimeException("Группы не существует");
        }

        List<StudentEntity> allStudents = group.getStudents();

        if(allStudents.size() == 0){
            throw new RuntimeException("Группа пуста");
        }

        // Применяем пагинацию к списку студентов
        int fromIndex = (offset - 1) * count;
        int toIndex = Math.min(fromIndex + count, allStudents.size());

        if(toIndex < fromIndex){
            throw new RuntimeException("Страница пуста");
        }

        List<StudentEntity> paginatedStudents = allStudents.subList(fromIndex, toIndex);

        List<StudentMidGradeRes> studentMidGrad = calculateMidGrades(paginatedStudents);

        return new ListResponse<>(studentMidGrad, group.getStudents().size());

    }

    public List<StudentMidGradeRes> calculateMidGrades(List<StudentEntity> paginatedStudents) {
        return paginatedStudents.stream()
                .map(studentEntity -> new StudentMidGradeRes(studentEntity, studentEntity.calculateAverageGrade()))
                .collect(Collectors.toList());
    }
}
