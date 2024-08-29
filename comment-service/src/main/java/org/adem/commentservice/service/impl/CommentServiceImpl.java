package org.adem.commentservice.service.impl;

import org.adem.commentservice.dto.CommentRequest;
import org.adem.commentservice.dto.product.ProductResponse;
import org.adem.commentservice.exception.CommentNotFoundException;
import org.adem.commentservice.exception.ProductNotFoundException;
import org.adem.commentservice.mapper.CommentMapper;
import org.adem.commentservice.model.Comment;
import org.adem.commentservice.repository.CommentRepository;
import org.adem.commentservice.service.CommentService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final WebClient.Builder webClientBuilder;

    public CommentServiceImpl(CommentMapper commentMapper, CommentRepository commentRepository, WebClient.Builder webClientBuilder) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public void addComment(CommentRequest commentRequest) {

        ProductResponse productResponse = webClientBuilder.build().get()
                .uri("http://PRODUCT/v1/product/exist-product-by-product-id", uriBuilder -> uriBuilder.queryParam("id", commentRequest.getProductId()).build())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
        if (productResponse == null || !commentRequest.getProductId().equals(productResponse.getId())) {
           throw new ProductNotFoundException("Product not found");
        }
        commentRepository.save(commentMapper.toComment(commentRequest));
    }

    @Override
    public void deleteCommentByID(Integer id) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isEmpty()){
            throw new CommentNotFoundException("Comment not found");
        }
        commentRepository.deleteById(id);
    }
}
