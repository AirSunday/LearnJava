package org.example.Repository;

import org.example.Dto.StudentMidGradeDTO;
import org.example.Entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    StudentEntity findByNameAndFamilyAndGroup_Number(String name, String family, Integer group_number);
}
