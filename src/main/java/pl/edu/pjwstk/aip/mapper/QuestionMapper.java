package pl.edu.pjwstk.aip.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.aip.dto.QuestionDto;
import pl.edu.pjwstk.aip.dto.UserDto;
import pl.edu.pjwstk.aip.entity.Question;
import pl.edu.pjwstk.aip.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public class QuestionMapper {

    public QuestionDto toDto(Question question) {
        return QuestionDto.builder()
                .id(question.getId())
                .test(question.getTest())
                .questionText(question.getQuestionText())
                .answerA(question.isAnswerA())
                .answerB(question.isAnswerB())
                .answerC(question.isAnswerC())
                .answerD(question.isAnswerD())
                .build();
    }
    public QuestionDto toDtoWithOptional(Optional<Question> question) {

        return question.map(value -> QuestionDto.builder()
                .id(value.getId())
                .test(value.getTest())
                .questionText(value.getQuestionText())
                .answerA(value.isAnswerA())
                .answerB(value.isAnswerB())
                .answerC(value.isAnswerC())
                .answerD(value.isAnswerD())
                .build()).orElse(null);
    }

    public Question toEntity(QuestionDto questionDto) {
        return Question.builder()
                .id(questionDto.getId())
                .test(questionDto.getTest())
                .questionText(questionDto.getQuestionText())
                .answerA(questionDto.isAnswerA())
                .answerB(questionDto.isAnswerB())
                .answerC(questionDto.isAnswerC())
                .answerD(questionDto.isAnswerD())
                .build();
    }

    public List<QuestionDto> toUserListDto(List<Question> questions) {
        return questions.stream().map(this::toDto)
                .collect(Collectors.toList());
    }
}
