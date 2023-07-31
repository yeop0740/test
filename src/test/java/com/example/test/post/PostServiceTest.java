package com.example.test.post;

import com.example.test.auth.User;
import com.example.test.image.ImageRepository;
import com.example.test.image.PostImage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class PostServiceTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ImageRepository imageRepository;

    @Test
    public void 이미지_삭제() {

        String nickname = "";
        String email = "";
        String password = "";

        String content = "";
        
        String key = "";

        User user = new User(UUID.randomUUID(), nickname, email, password);
        em.persist(user);
        Post post = new Post();
        post.createPost(user, content);
        em.persist(post);
        PostImage image = PostImage.createPostImage(key, post);
        em.persist(image);

        Long imageId = image.getId();
        Long postId = post.getId();
        em.flush();
        em.clear();
        Post findPost = em.find(Post.class, postId);
        em.remove(findPost);
//        imageRepository.deletePostImage(image);
//        imageRepository.findOne(imageId);
//
//        Assertions.assertThrows(NullPointerException.class, () -> imageRepository.findOne(imageId).getId());

    }

}