package pl.edu.pjwstk.aip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjwstk.aip.entity.Test;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto {

    private Long id;

    private Test test;

    private String questionText;

    private boolean answerA;

    private boolean answerB;

    private boolean answerC;

    private boolean answerD;
}
