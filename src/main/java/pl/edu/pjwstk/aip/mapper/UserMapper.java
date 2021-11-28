package pl.edu.pjwstk.aip.mapper;


import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import pl.edu.pjwstk.aip.dto.UserDto;
import pl.edu.pjwstk.aip.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Mapper(componentModel = "spring")
public class UserMapper {

    public UserDto toDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }
    public UserDto toDtoWithOptional(Optional<User> user) {
        return user.map(value -> UserDto.builder()
                .id(value.getId())
                .username(value.getUsername())
                .build()).orElse(null);
    }

    public User toEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .build();
    }

    public List<UserDto> toUserListDto(List<User> users) {
        return users.stream().map(this::toDto)
                .collect(Collectors.toList());
    }

}
