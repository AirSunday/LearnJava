package org.example.Api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GradeSaveReq;
import org.example.Dto.GroupSaveReq;
import org.example.Dto.SimpleResponse;
import org.example.Service.GradeService;
import org.example.Service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/grade")
public class GradeApi {

    private final GradeService gradeService;

    @Tag(name = "Create", description = "Create entity")
    @Operation(
            summary = "Create grade or update",
            description = "Create grade by student and subject (or update)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Long.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<SimpleResponse<Long>> save(GradeSaveReq req){
        try{
            var result = gradeService.save(req);
            return ResponseEntity.ok(new SimpleResponse<>(result));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
