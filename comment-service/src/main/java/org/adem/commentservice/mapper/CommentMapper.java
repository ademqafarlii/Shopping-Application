package org.adem.commentservice.mapper;

import org.adem.commentservice.dto.CommentRequest;
import org.adem.commentservice.dto.CommentResponse;
import org.adem.commentservice.exception.CommentNotFoundException;
import org.adem.commentservice.model.Comment;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentMapper {
    public Comment toComment(CommentRequest commentRequest){
        if (commentRequest==null){
            throw new CommentNotFoundException("Comment not found");
        }
        return Comment.builder()
                .productId(commentRequest.getProductId())
                .commentedAt(LocalDateTime.now())
                .build();
    }

    public CommentResponse toCommentResponse(Comment comment){
        if (comment==null){
            throw new CommentNotFoundException("Comment not found");
        }
        return CommentResponse.builder()
                .productId(comment.getProductId())
                .commentedAt(comment.getCommentedAt())
                .build();
    }
}
