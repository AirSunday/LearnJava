package org.example.Repository;

import org.example.Entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GroupRepository extends JpaRepository<GroupEntity, Long> {

    @Query("select g from GroupEntity g where g.number = :query")
    GroupEntity search(@Param("query") Integer query);
}
