package org.example.Api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupSaveReq;
import org.example.Dto.ListResponse;
import org.example.Dto.SimpleResponse;
import org.example.Entity.StudentEntity;
import org.example.Service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupApi {

    private final GroupService groupService;

    @Tag(name = "Create", description = "Create entity")
    @Operation(
            summary = "Create group",
            description = "Create group by number (If it exists, then return the Id)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Long.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @PutMapping()
    public ResponseEntity<SimpleResponse<Long>> save(GroupSaveReq req){
        try{
            var result = groupService.save(req);
            return ResponseEntity.ok(new SimpleResponse<>(result));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @Tag(name = "HomeWork")
    @Operation(
            summary = "Get list student",
            description = "Get list student by group (You can select the page number and the number of students per page)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = ListResponse.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)
    })
    @GetMapping("/{group}/{offset}/{count}")
    public ResponseEntity<ListResponse<StudentEntity>> getListStudentByGroup(
                 @PathVariable
                 @Parameter(description = "Group number", required = true)
                 @Min(1)
                     int group,
                 @PathVariable
                 @Parameter(description = "Number page",required = true)
                 @Min(1)
                     int offset,
                 @PathVariable
                 @Parameter(description = "Count students in page", required = true)
                 @Min(1)
                     int count
    ){
        try{
            var result = groupService.getListStudentByGroup(group, offset, count);
            return ResponseEntity.ok(new ListResponse<>(result));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
