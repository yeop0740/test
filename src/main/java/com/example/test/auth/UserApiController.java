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
    public static final UUID uuid = UUID.fromString("2a95f24f-e21f-4500-8d93-a717a00fbc81");;

    @PostMapping("/api/v1/join")
    public JoinMemberResponse joinMember(@RequestBody @Valid JoinMemberRequest request) {

//        UUID uuid = UUID.randomUUID();
        User user = new User(uuid, request.getNickname(), request.getEmail(), request.getPassword());
        userService.join(user);
        return new JoinMemberResponse(uuid);

    }

    @PostMapping("api/v1/login")
    public LoginResponse loginMember(@RequestBody @Valid LoginRequest request) {

        User user = userService.login(request.getEmail(), request.getPassword());
        return new LoginResponse(user.getNickname());

    }

}
