package pl.edu.pjwstk.aip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.aip.entity.Question;
import pl.edu.pjwstk.aip.entity.Test;
import pl.edu.pjwstk.aip.exception.EntityNotFoundException;
import pl.edu.pjwstk.aip.repository.QuestionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;

    public List<Question> getAllQuestions() {
        log.info("Getting all questions from database");
        return questionRepository.findAll();
    }


    public Optional<Question> getQuestion(Long questionId) {
        log.info("Get question {} from database", questionId);
        return Optional.ofNullable(questionRepository.findById(questionId)
                .orElseThrow(() -> new EntityNotFoundException("Question with ID: '" + questionId + "' not found.")));

    }

    public void deleteTest(Long questionId) {
        log.info("Delete question {} from database", questionId);
        if (!questionRepository.existsById(questionId)) throw new EntityNotFoundException(
                String.format("Question with ID: %s  not exist.", questionId));
        questionRepository.deleteById(questionId);
    }

    public Question saveTest(Question question) {
        log.info("Save question {} into database", question.getId());
        return questionRepository.save(question);
    }
}
