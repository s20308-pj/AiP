package pl.edu.pjwstk.aip.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.edu.pjwstk.aip.entity.Role;
import pl.edu.pjwstk.aip.entity.User;
import pl.edu.pjwstk.aip.exception.EntityNotFoundException;
import pl.edu.pjwstk.aip.repository.RoleRepository;
import pl.edu.pjwstk.aip.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;


    public List<User> getAllUsers() {
        log.info("Getting all users from database");
        return userRepository.findAll();
    }


    public Optional<User> getUser(Long userId) {
        log.info("Get user {} from database", userId);
        return Optional.ofNullable(userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("Employee with ID: '" + userId + "' not found.")));

    }

    public void deleteUser(Long userId) {
        log.info("Delete user {} from database", userId);
        if (!userRepository.existsById(userId)) throw new EntityNotFoundException(
                String.format("Employee with ID: %s  not exist.", userId));
        userRepository.deleteById(userId);
    }

    public User saveUser(User user) {
        log.info("Save user {} into database", user.getUsername());
        return userRepository.save(user);
    }
}
