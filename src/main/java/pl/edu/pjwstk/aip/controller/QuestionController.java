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
import pl.edu.pjwstk.aip.dto.QuestionDto;
import pl.edu.pjwstk.aip.dto.TestDto;
import pl.edu.pjwstk.aip.exception.CustomResponse;
import pl.edu.pjwstk.aip.exception.EntityNotFoundException;
import pl.edu.pjwstk.aip.mapper.QuestionMapper;
import pl.edu.pjwstk.aip.mapper.TestMapper;
import pl.edu.pjwstk.aip.service.QuestionService;
import pl.edu.pjwstk.aip.service.TestService;

import java.util.List;

import static pl.edu.pjwstk.aip.util.LogConstants.QUESTION_WITH_ID;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
@Validated
public class QuestionController {

    private final QuestionService questionService;
    private final QuestionMapper questionMapper;

    @GetMapping()
    public ResponseEntity<List<QuestionDto>> getAllQuestions() {
        return ResponseEntity.ok(questionMapper.toUserListDto(questionService.getAllQuestions()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestionDto> getQuestionById(@PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(questionMapper.toDtoWithOptional(questionService.getQuestion(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable Long id) throws EntityNotFoundException {
        if (questionService.getQuestion(id).isPresent()) {
            this.questionService.deleteTest(id);
            return new ResponseEntity<>(
                    new CustomResponse(HttpStatus.OK.value(),
                            QUESTION_WITH_ID + id + "' deleted."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new CustomResponse(HttpStatus.NOT_FOUND.value(),
                            QUESTION_WITH_ID + id + "' not found."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<QuestionDto> saveQuestion(@RequestBody @Validated QuestionDto employee) {
        return ResponseEntity.ok(questionMapper.toDto(questionService.saveTest(questionMapper.toEntity(employee))));
    }
}
