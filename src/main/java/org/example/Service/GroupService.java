package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupSaveReq;
import org.example.Entity.GroupEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;


@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;

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
}
