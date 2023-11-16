package org.example.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(name="t_grade")
public class GradeEntity {

    @EmbeddedId
    private GradeId id;

    private Integer grade;

    @Embeddable
    @Setter
    public static class GradeId implements Serializable {
        @ManyToOne
        @JoinColumn(name = "subject_id")
        private SubjectEntity subject;

        @ManyToOne
        @JoinColumn(name = "student_id")
        private StudentEntity student;
    }

    public StudentEntity getStudent(){
        return id.student;
    }

    public SubjectEntity getSubject(){
        return id.subject;
    }
}
