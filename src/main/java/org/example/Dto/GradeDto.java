package org.example.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeDto {

    @NotBlank
    private String nameStudent;

    @NotBlank
    private String familyStudent;

    @Min(1)
    @Max(12)
    private Integer groupStudent;

    @Min(1)
    @Max(5)
    private Integer grade;

    @NotBlank
    private String subject;
}
