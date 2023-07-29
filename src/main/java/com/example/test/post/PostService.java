package com.example.test.post;

import com.example.test.auth.User;
import com.example.test.auth.UserRepository;
import com.example.test.image.ImageRepository;
import com.example.test.image.ImageUploader;
import com.example.test.image.PostImage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    private final ImageRepository imageRepository;

    private final ImageUploader imageUploader;

    @Transactional
    public Long createPost(UUID uuid, String content, List<MultipartFile> images) throws IOException {

        User findUser = userRepository.findByUuid(uuid);
        Post post = new Post();
        post.createPost(findUser, content);
        postRepository.save(post);
        List<String> keys = imageUploader.uploadImages(images, uuid);

        for (String key : keys) {

            PostImage image = PostImage.createPostImage(key, post);
            imageRepository.save(image);

        }

        return post.getId();

    }

    public Post findPost(Long id) {

        return postRepository.findOne(id);

    }

    public List<Post> findPosts() {

        return postRepository.findPosts();

    }

}
