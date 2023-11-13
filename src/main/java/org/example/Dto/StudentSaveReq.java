package org.example.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentSaveReq {
    @Schema(description = "students name")
    @NotBlank
    private String name;

    @Schema(description = "students family")
    @NotBlank
    private String family;

    @Schema(description = "students age")
    @Positive
    @Min(1)
    private int age;

    @Schema(description = "students group number")
    @Positive
    @Min(1)
    private int group_number;
}
