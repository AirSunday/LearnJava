package org.example.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.Entity.StudentEntity;

@Getter
@Setter
@AllArgsConstructor
public class StudentMidGradeRes {
    @Schema(description = "Student info")
    private StudentEntity student;

    @Schema(description = "Student mid grade")
    private Double MidGrade;
}
