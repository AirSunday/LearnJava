package org.example.Repository;

import org.example.Dto.StudentMidGradeDTO;
import org.example.Entity.GradeEntity;
import org.example.Entity.StudentEntity;
import org.example.Entity.SubjectEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GradeRepository extends JpaRepository<GradeEntity, GradeEntity.GradeId> {
    GradeEntity findById_StudentAndId_Subject(StudentEntity student, SubjectEntity subject);

    @Query("""
        SELECT new org.example.Dto.StudentMidGradeDTO(GR.id.student.name, GR.id.student.family, GR.id.student.age, GR.id.student.group.number, AVG(GR.grade))
            FROM GradeEntity GR
                WHERE GR.id.student.group.number = :groupNumber
                GROUP BY GR.id.student.name, GR.id.student.family, GR.id.student.age, GR.id.student.group.number
    """)
    Page<StudentMidGradeDTO> findStudentMidGradeByGroupNumber (@Param("groupNumber") int groupNumber, Pageable pageable);
}
