package org.example.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.example.Dto.*;
import org.example.Exception.NotFoundException;
import org.example.Service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/group")
public class GroupController {

    private final GroupService groupService;

    @Tag(name = "Create")
    @PutMapping()
    public ResponseEntity<?> save(@Valid @RequestBody GroupDto groupDto){
        try{
            var result = groupService.save(groupDto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Tag(name = "HomeWork")
    @GetMapping("/{number}/{offset}/{count}")
    public ResponseEntity<?> getListStudentByGroup(
                 @PathVariable
                 @Parameter(description = "Group number", required = true)
                 @Min(1)
                 @Max(12)
                     int number,
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
            var result = groupService.getListStudentByGroup(number, offset, count);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
