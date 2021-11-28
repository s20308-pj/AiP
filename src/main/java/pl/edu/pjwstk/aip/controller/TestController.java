package pl.edu.pjwstk.aip.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.aip.dto.TestDto;
import pl.edu.pjwstk.aip.exception.CustomResponse;
import pl.edu.pjwstk.aip.exception.EntityNotFoundException;
import pl.edu.pjwstk.aip.mapper.TestMapper;
import pl.edu.pjwstk.aip.service.TestService;


import java.util.List;

import static pl.edu.pjwstk.aip.util.LogConstants.TEST_WITH_ID;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
@Validated
public class TestController {


    private final TestService testService;
    private final TestMapper testMapper;

    @GetMapping()
    public ResponseEntity<List<TestDto>> getAllTests() {
        return ResponseEntity.ok(testMapper.toUserListDto(testService.getAllTests()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<TestDto> getTestById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(testMapper.toDtoWithOptional(testService.getTest(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteTest(@PathVariable Long id) throws EntityNotFoundException {
        if (testService.getTest(id).isPresent()) {
            this.testService.deleteTest(id);
            return new ResponseEntity<>(
                    new CustomResponse(HttpStatus.OK.value(),
                            TEST_WITH_ID + id + "' deleted."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new CustomResponse(HttpStatus.NOT_FOUND.value(),
                            TEST_WITH_ID + id + "' not found."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<TestDto> saveTest(@RequestBody @Validated TestDto employee) {
        return ResponseEntity.ok(testMapper.toDto(testService.saveTest(testMapper.toEntity(employee))));
    }
}
