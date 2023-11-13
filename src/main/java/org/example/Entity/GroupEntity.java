package org.example.Entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_group")
@RequiredArgsConstructor
public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer number;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL)
    private List<StudentEntity> students;

}
