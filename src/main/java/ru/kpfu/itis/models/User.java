package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class User {
    private Long id;
    private String email;
    private String hashPassword;
    private String role;
}