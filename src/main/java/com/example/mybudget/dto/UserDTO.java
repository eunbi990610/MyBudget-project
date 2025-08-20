package com.example.mybudget.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private Long userId;
    private String userName;
    private String userBirthday;
    private String loginId;
    private String password;
    private String email;
    private String createdAt;

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userBirthday='" + userBirthday + '\'' +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", createdAt='" + createdAt + '\'' +
                '}';
    }
}
