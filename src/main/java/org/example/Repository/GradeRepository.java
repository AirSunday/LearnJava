package org.example.Repository;

import org.example.Entity.GradeEntity;
import org.example.Entity.StudentEntity;
import org.example.Entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GradeRepository extends JpaRepository<GradeEntity, Long> {

    @Query("select g from GradeEntity g where g.student = :student and " +
                                                "g.subject = :subject ")
    GradeEntity search(@Param("student") StudentEntity student,
                         @Param("subject") SubjectEntity subject);
}
