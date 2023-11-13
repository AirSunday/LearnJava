package org.example.Api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.Dto.GroupSaveReq;
import org.example.Dto.SimpleResponse;
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


}
