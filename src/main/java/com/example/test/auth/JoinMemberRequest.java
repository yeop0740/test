package com.example.test.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class JoinMemberRequest {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

    @NotEmpty
    private String nickname;
}
