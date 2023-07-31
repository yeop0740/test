package com.example.test.comment;

import com.example.test.auth.User;
import com.example.test.auth.UserRepository;
import com.example.test.post.Post;
import com.example.test.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final PostRepository postRepository;

    private final UserRepository userRepository;

    @Transactional
    public Long createComment(Long postId, Long parentId, UUID uuid,  String content) {

        Post findPost = postRepository.findOne(postId);
        User findUser = userRepository.findByUuid(uuid);

        Comment findComment = null;

        if (parentId != null) {

            findComment = commentRepository.findOne(parentId);

        }

        Comment comment = Comment.createComment(findPost, findComment, findUser, content);
        commentRepository.save(comment);

        return comment.getId();

    }

    public User findCommentsWriter(UUID uuid) {

        User findUser = userRepository.findByUuid(uuid);
        List<Comment> comments = findUser.getComments();
        comments.size();

        if (!comments.isEmpty()) {

            for (Comment comment : comments) {

                comment.getChildren().size();

                if (comment.getParent() != null) {

                    comment.getParent().getId();

                }

            }

        }

        return findUser;

    }

    public Post findCommentsPost(Long postId) {

        Post findPost = postRepository.findOne(postId);
        findPost.getWriter().getId();
        List<Comment> comments = findPost.getComments();

        for (Comment comment : comments) {

            Comment parent = comment.getParent();

            if (parent != null) {

                comment.getParent().getId();

            }

            comment.getWriter().getId();

        }

        return findPost;

    }

    @Transactional
    public Long updateComment(Long commentId, String content) {

        Comment findComment = commentRepository.findOne(commentId);
        findComment.change(content);
        return findComment.getId();

    }

}
