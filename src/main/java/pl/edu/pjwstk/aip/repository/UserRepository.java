package pl.edu.pjwstk.aip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pjwstk.aip.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
