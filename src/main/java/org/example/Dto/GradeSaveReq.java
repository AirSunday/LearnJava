package org.example.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GradeSaveReq {

    @Schema(description = "Students name")
    @NotBlank
    private String nameStudent;

    @Schema(description = "Students family")
    @NotBlank
    private String familyStudent;

    @Schema(description = "Students group")
    @Positive
    @Min(1)
    private Integer groupStudent;

    @Schema(description = "Students grade by subject")
    @Positive
    private Integer grade;

    @Schema(description = "Name subject")
    @NotBlank
    private String subject;
}
