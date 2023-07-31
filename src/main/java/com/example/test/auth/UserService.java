package com.example.test.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(User user) {

        userRepository.save(user);
        return user.getId();

    }

    public User login(String email, String password) {

        return userRepository.findByEmailPassword(email, password);

    }

}
