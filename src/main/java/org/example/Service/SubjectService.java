package org.example.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Dto.SubjectDto;
import org.example.Entity.SubjectEntity;
import org.example.Repository.SubjectRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class SubjectService {
    private final SubjectRepository subjectRepository;

    public Long save(@Valid SubjectDto subjectDto) {

        SubjectEntity subject = new SubjectEntity();
        subject.setName(subjectDto.getName());

        SubjectEntity subjectSearch = subjectRepository.findByName(subjectDto.getName());

        if(subjectSearch == null) {
            subjectRepository.save(subject);
            return subject.getId();
        }

        return subjectSearch.getId();
    }
}
