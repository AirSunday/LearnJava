package org.example.Service;


import org.example.Entity.SubjectEntity;
import org.example.Entity.SubjectType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Long> {
    @Query("select s from SubjectEntity s where s.name = :query")
    SubjectEntity search(@Param("query") SubjectType query);
}
