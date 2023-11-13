package org.example.Repository;

import org.example.Entity.StudentEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    @Query("select s from StudentEntity s where s.name = :name and " +
                                                "s.family = :family and " +
                                                "s.group.number = :group")
    StudentEntity search(@Param("name") String name,
                         @Param("family") String family,
                         @Param("group") Integer group  );

    @Query("select s from StudentEntity s where s.group.number = :group " +
            "group by s.id, s.group " +
            "order by s.id")
    List<StudentEntity> searchByNumber(@Param("group") Integer group,
                                       Pageable pageable);

}
