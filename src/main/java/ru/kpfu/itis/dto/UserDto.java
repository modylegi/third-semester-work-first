package ru.kpfu.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.kpfu.itis.models.User;
@Data
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String role;
    private String token;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
