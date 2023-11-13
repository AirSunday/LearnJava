package org.example.Service;

import org.example.Entity.StudentEntity;
import org.example.Entity.SubjectEntity;
import org.example.Entity.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("select s from StudentEntity s where s.name = :name and " +
                                                "s.family = :family and " +
                                                "s.group.number = :group")
    StudentEntity search(@Param("name") String name,
                         @Param("family") String family,
                         @Param("group") Integer group  );
}
