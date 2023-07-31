package com.example.test.comment;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Comment comment) {

        em.persist(comment);

    }

    public Comment findOne(Long id) {

        return em.find(Comment.class, id);

    }

}
