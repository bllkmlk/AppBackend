package com.project.questapp;

import com.project.questapp.entities.Comment;
import com.project.questapp.entities.Post;
import com.project.questapp.entities.User;
import com.project.questapp.repos.CommentRepository;
import com.project.questapp.requests.CommentCreateRequest;
import com.project.questapp.requests.CommentUpdateRequest;
import com.project.questapp.services.CommentService;
import com.project.questapp.services.PostService;
import com.project.questapp.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private UserService userService;

    @Mock
    private PostService postService;

    @InjectMocks
    private CommentService commentService;

    @Test
    public void testGetAllCommentsWithParam() {
        // Arrange
        Long userId = 1L;
        Long postId = 2L;
        List<Comment> expectedComments = new ArrayList<>();
        when(commentRepository.findByUserIdAndPostId(userId, postId)).thenReturn(expectedComments);

        // Act
        List<Comment> result = commentService.getAllCommentWithParam(Optional.of(userId), Optional.of(postId));

        // Assert
        assertEquals(expectedComments, result);
    }

    @Test
    public void testGetOneCommentById() {
        // Arrange
        Long commentId = 1L;
        Comment expectedComment = new Comment();
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(expectedComment));

        // Act
        Comment result = commentService.getOneCommentById(commentId);

        // Assert
        assertEquals(expectedComment, result);
    }

    @Test
    public void testCreateOneComment() {
        // Arrange
        CommentCreateRequest request = new CommentCreateRequest();
        request.setUserId(1L);
        request.setPostId(2L);
        request.setText("Test comment");

        User user = new User();
        when(userService.getOneUserById(request.getUserId())).thenReturn(user);

        Post post = new Post();
        when(postService.getOnePostById(request.getPostId())).thenReturn(post);

        Comment savedComment = new Comment();
        when(commentRepository.save(any(Comment.class))).thenReturn(savedComment);

        // Act
        Comment result = commentService.createOneComment(request);

        // Assert
        assertEquals(savedComment, result);
    }

    @Test
    public void testUpdateOneCommentId() {
        // Arrange
        Long commentId = 1L;
        CommentUpdateRequest updateRequest = new CommentUpdateRequest();
        updateRequest.setText("Updated text");

        Comment existingComment = new Comment();
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        Comment updatedComment = new Comment();
        when(commentRepository.save(any(Comment.class))).thenReturn(updatedComment);

        // Act
        Comment result = commentService.updateOneCommentId(commentId, updateRequest);

        // Assert
        assertEquals(updatedComment, result);
    }

    @Test
    public void testDeleteOneCommentById() {
        // Arrange
        Long commentId = 1L;

        // Act
        commentService.deleteOneCommentById(commentId);

        // Assert
        verify(commentRepository, times(1)).deleteById(commentId);
    }
}


