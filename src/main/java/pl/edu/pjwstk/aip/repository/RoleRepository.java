package pl.edu.pjwstk.aip.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.pjwstk.aip.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("Select r from Role r where r.name=:name")
    Role findByName(String name);
}
