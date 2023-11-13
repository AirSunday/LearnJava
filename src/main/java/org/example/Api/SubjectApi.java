package org.example.Api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.Dto.SimpleResponse;
import org.example.Dto.SubjectSaveReq;
import org.example.Service.SubjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subject")
public class SubjectApi {

    private final SubjectService subjectService;

    @Tag(name = "Create", description = "Create entity")
    @Operation(
            summary = "Create subject",
            description = "Create subject by name (If it exists, then return the Id)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Long.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<SimpleResponse<Long>> save(@RequestBody SubjectSaveReq req){
        try{
            var result = subjectService.save(req);
            return ResponseEntity.ok(new SimpleResponse<>(result));
        }
        catch (Exception e){
            SimpleResponse<Long> errorResponse = new SimpleResponse<>();
            errorResponse.setError(true);
            errorResponse.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }
}
