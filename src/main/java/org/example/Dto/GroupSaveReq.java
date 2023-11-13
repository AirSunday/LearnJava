package org.example.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GroupSaveReq {
    @Schema(description = "Groups number")
    @Positive
    @Min(1)
    private int number;
}
