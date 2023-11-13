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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String family;
    private Integer age;
    private Double midGrade;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name= "group_id")
    private GroupEntity group;

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<GradeEntity> grades;

    public void setMidGrade(){
        double sum = 0;
        for(GradeEntity grade : grades){
            sum += grade.getGrade();
        }
        if (grades.size() > 0) {
            double average = sum / grades.size();
            BigDecimal roundedAverage = BigDecimal.valueOf(average).setScale(3, RoundingMode.HALF_UP);
            this.midGrade = roundedAverage.doubleValue();
        } else {
            this.midGrade = 0.0;
        }
    }
}
