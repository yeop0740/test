package com.example.test.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/v1/join")
    public JoinMemberResponse joinMember(@RequestBody @Valid JoinMemberRequest request) {

        UUID uuid = UUID.randomUUID();
        User user = User.builder()
                .uuid(uuid)
                .email(request.getEmail())
                .password(request.getPassword())
                .nickname(request.getNickname())
                .build();
        userService.join(user);
        return new JoinMemberResponse(uuid);

    }

}
