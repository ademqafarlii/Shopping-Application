package org.adem.commentservice.service;

import org.adem.commentservice.dto.CommentRequest;

public interface CommentService {

    void addComment(CommentRequest commentRequest);

    void deleteCommentByID(Integer id);
}
