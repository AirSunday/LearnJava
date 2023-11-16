package org.example.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "t_student")
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String family;
    private Integer age;

    @ManyToOne
    @JoinColumn(name= "group_id")
    private GroupEntity group;

    @OneToMany(mappedBy = "id.student", cascade = CascadeType.ALL)
    private List<GradeEntity> grades;
}
