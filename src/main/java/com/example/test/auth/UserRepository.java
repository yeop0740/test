package com.example.test.auth;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void save(User user) {

        em.persist(user);

    }

    public User findByEmailPassword(String email, String password) {

        return em.createQuery("select u from USERS u " +
                                "where u.email = :email and u.password = :password", User.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();

    }
}
