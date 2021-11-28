package pl.edu.pjwstk.aip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.aip.entity.Test;
import pl.edu.pjwstk.aip.exception.EntityNotFoundException;
import pl.edu.pjwstk.aip.repository.TestRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestService {

    private final TestRepository testRepository;


    public List<Test> getAllTests() {
        log.info("Getting all tests from database");
        return testRepository.findAll();
    }


    public Optional<Test> getTest(Long testId) {
        log.info("Get test {} from database", testId);
        return Optional.ofNullable(testRepository.findById(testId)
                .orElseThrow(() -> new EntityNotFoundException("Test with ID: '" + testId + "' not found.")));

    }

    public void deleteTest(Long testId) {
        log.info("Delete tet {} from database", testId);
        if (!testRepository.existsById(testId)) throw new EntityNotFoundException(
                String.format("Test with ID: %s  not exist.", testId));
        testRepository.deleteById(testId);
    }

    public Test saveTest(Test test) {
        log.info("Save test {} into database", test.getId());
        return testRepository.save(test);
    }
}
