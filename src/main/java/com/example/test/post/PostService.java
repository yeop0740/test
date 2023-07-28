package com.example.test.post;

import com.example.test.auth.User;
import com.example.test.auth.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long createPost(UUID uuid, String content) {

        User findUser = userRepository.findByUuid(uuid);
        Post post = Post.createPost(findUser, content);
        postRepository.save(post);
        return post.getId();

    }

    public Post findPost(Long id) {

        return postRepository.findOne(id);

    }

    public List<Post> findPosts() {

        return postRepository.findPosts();

    }

}
