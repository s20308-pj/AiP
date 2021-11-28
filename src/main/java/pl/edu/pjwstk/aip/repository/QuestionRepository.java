package pl.edu.pjwstk.aip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.aip.entity.Question;

public interface QuestionRepository extends JpaRepository<Question,Long> {
}
