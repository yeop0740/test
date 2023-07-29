package com.example.test.post;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {

    private final EntityManager em;

    public void save(Post post) {

        em.persist(post);

    }

    public Post findOne(Long id) {

        return em.find(Post.class, id);

    }

    public List<Post> findPosts() {

        return em.createQuery("select p from Post p", Post.class)
                .getResultList();

    }

}
