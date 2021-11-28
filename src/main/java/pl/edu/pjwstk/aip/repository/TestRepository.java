package pl.edu.pjwstk.aip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.aip.entity.Test;

public interface TestRepository extends JpaRepository<Test,Long> {
}
