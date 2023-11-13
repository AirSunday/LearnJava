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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "group_id")
    private GroupEntity group;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<GradeEntity> grades;

    public Double calculateAverageGrade() {
        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (GradeEntity grade : grades) {
            sum += grade.getGrade();
        }

        double average = sum / grades.size();
        BigDecimal roundedAverage = BigDecimal.valueOf(average).setScale(3, RoundingMode.HALF_UP);

        return roundedAverage.doubleValue();
    }
}
