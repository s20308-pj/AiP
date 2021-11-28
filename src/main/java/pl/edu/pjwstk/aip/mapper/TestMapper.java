package pl.edu.pjwstk.aip.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.aip.dto.TestDto;
import pl.edu.pjwstk.aip.entity.Test;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public class TestMapper {

    public TestDto toDto(Test test) {
        return TestDto.builder()
                .id(test.getId())
                .category(test.getCategory())
                .build();
    }
    public TestDto toDtoWithOptional(Optional<Test> test) {
        return test.map(value -> TestDto.builder()
                .id(value.getId())
                .category(value.getCategory())
                .questionList(value.getQuestionList())
                .build()).orElse(null);
    }

    public Test toEntity(TestDto testDto) {
        return Test.builder()
                .id(testDto.getId())
                .category(testDto.getCategory())
                .questionList(testDto.getQuestionList())
                .build();
    }

    public List<TestDto> toUserListDto(List<Test> tests) {
        return tests.stream().map(this::toDto)
                .collect(Collectors.toList());
    }
}
