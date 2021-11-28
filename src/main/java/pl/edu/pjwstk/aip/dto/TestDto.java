package pl.edu.pjwstk.aip.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.edu.pjwstk.aip.category.Category;
import pl.edu.pjwstk.aip.entity.Question;


import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TestDto {

    private Long id;

    private List<Question> questionList;

    private Category category;

}
