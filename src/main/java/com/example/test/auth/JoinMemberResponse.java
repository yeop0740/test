package com.example.test.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class JoinMemberResponse {

    @NotEmpty
    private UUID uuid;

}
