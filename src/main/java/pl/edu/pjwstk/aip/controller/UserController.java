package pl.edu.pjwstk.aip.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.pjwstk.aip.dto.UserDto;
import pl.edu.pjwstk.aip.exception.CustomResponse;
import pl.edu.pjwstk.aip.exception.EntityNotFoundException;
import pl.edu.pjwstk.aip.mapper.UserMapper;
import pl.edu.pjwstk.aip.service.UserService;

import java.util.List;

import static pl.edu.pjwstk.aip.util.LogConstants.USER_WITH_ID;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Validated
public class UserController {


    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userMapper.toUserListDto(userService.getAllUsers()));

    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(

            @PathVariable Long id) throws EntityNotFoundException {
        return ResponseEntity.ok(userMapper.toDtoWithOptional(userService.getUser(id)));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteUser(@PathVariable Long id) throws EntityNotFoundException {
        if (userService.getUser(id).isPresent()) {
            this.userService.deleteUser(id);
            return new ResponseEntity<>(
                    new CustomResponse(HttpStatus.OK.value(),
                            USER_WITH_ID + id + "' deleted."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(
                    new CustomResponse(HttpStatus.NOT_FOUND.value(),
                            USER_WITH_ID + id + "' not found."), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<UserDto> saveUser(@RequestBody @Validated UserDto employee) {
        return ResponseEntity.ok(userMapper.toDto(userService.saveUser(userMapper.toEntity(employee))));
    }
}
