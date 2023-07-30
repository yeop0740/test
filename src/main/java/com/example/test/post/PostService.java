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
import java.util.ArrayList;
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

            if (key.isEmpty())
                continue;

            PostImage image = PostImage.createPostImage(key, post);
            imageRepository.save(image);

        }



        return post.getId();

    }

    public Post findPost(Long id) {

        Post findPost = postRepository.findOne(id);
        findPost.getImages().size();
        findPost.getComments().size();
        findPost.getWriter().getNickname();
        return findPost;

    }

    public List<Post> findPosts() {

        List<Post> posts = postRepository.findPosts();

        for (Post post : posts) {

            post.getImages().size();
            post.getComments().size();
            post.getWriter().getPets().size();

        }

        return posts;

    }

    @Transactional
    public Long update(Long id, String content, List<MultipartFile> multipartFiles) throws IOException {

        Post findPost = postRepository.findOne(id);
        List<PostImage> removeImages = findPost.getImages();
        int size = removeImages.size();

        for (int i = size - 1; i >= 0; i--) {

            findPost.removeImage(findPost.getImages().get(i));

        }

        findPost.change(content);

        List<String> keys = imageUploader.uploadImages(multipartFiles, findPost.getWriter().getUuid());

        for (String key : keys) {

            PostImage image = PostImage.createPostImage(key, findPost);
            imageRepository.save(image);

        }

        return findPost.getId();

    }

    @Transactional
    public Long delete(Long postId) {

        Post findPost = postRepository.findOne(postId);
        Long id = findPost.getId();
        postRepository.delete(findPost);
        return id;

    }


}
