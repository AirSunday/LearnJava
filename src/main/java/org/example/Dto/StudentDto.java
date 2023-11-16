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
public class StudentDto {
    @NotBlank
    private String name;

    @NotBlank
    private String family;

    @Min(1)
    private int age;

    @Min(1)
    @Max(12)
    private int group_number;
}
