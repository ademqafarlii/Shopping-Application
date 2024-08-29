package org.adem.bookmarkservice.service.impl;

import org.adem.bookmarkservice.dto.BookmarkPageResponse;
import org.adem.bookmarkservice.dto.BookmarkRequest;
import org.adem.bookmarkservice.dto.BookmarkResponse;
import org.adem.bookmarkservice.dto.product.ProductResponse;
import org.adem.bookmarkservice.exception.AlreadyAddedBookmarkException;
import org.adem.bookmarkservice.exception.BookmarkNotFoundException;
import org.adem.bookmarkservice.exception.ProductNotFoundException;
import org.adem.bookmarkservice.mapper.BookmarkMapper;
import org.adem.bookmarkservice.model.Bookmark;
import org.adem.bookmarkservice.repository.BookmarkRepository;
import org.adem.bookmarkservice.service.BookmarkService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.awt.print.Book;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookmarkServiceImpl implements BookmarkService {
    private final BookmarkRepository bookmarkRepository;
    private final BookmarkMapper bookmarkMapper;
    private final WebClient.Builder webClientBuilder;

    public BookmarkServiceImpl(BookmarkRepository bookmarkRepository, BookmarkMapper bookmarkMapper, WebClient.Builder webClientBuilder) {
        this.bookmarkRepository = bookmarkRepository;
        this.bookmarkMapper = bookmarkMapper;
        this.webClientBuilder = webClientBuilder;
    }


    @Override
    public void bookmark(BookmarkRequest bookmarkRequest) {
        Optional<Bookmark> byProductId = bookmarkRepository.findByProductId(bookmarkRequest.getProductId());
        if (byProductId.isPresent()){
            throw new AlreadyAddedBookmarkException("Already added to bookmark");
        }

        ProductResponse productDto = webClientBuilder.build().get()
                .uri("http://PRODUCT/v1/product/exist-product-by-product-id", uriBuilder -> uriBuilder.queryParam("id", bookmarkRequest.getProductId()).build())
                .retrieve()
                .bodyToMono(ProductResponse.class)
                .block();
        if (productDto==null){
            throw new ProductNotFoundException("Product not found");
        }
        bookmarkRepository.save(bookmarkMapper.toBookMark(bookmarkRequest));
    }

    @Override
    public BookmarkResponse getBookmarkById(Integer id) {
        return bookmarkRepository.findById(id)
                .stream()
                .map(bookmarkMapper::toBookMarkResponse)
                .findFirst()
                .orElseThrow(() -> new BookmarkNotFoundException("Bookmark not found"));
    }

    @Override
    public BookmarkPageResponse getAllBookmarks(int page, int count) {
        Page<Bookmark> bookmarks = bookmarkRepository.findAll(PageRequest.of(page, count));
        if (bookmarks.isEmpty()) {
            throw new BookmarkNotFoundException("Bookmark not found");
        }
        return new BookmarkPageResponse(
                bookmarks.getContent().stream().map(bookmarkMapper::toBookMarkResponse).collect(Collectors.toList()),
                bookmarks.getTotalElements(),
                bookmarks.getTotalPages(),
                bookmarks.hasNext()
        );
    }

    @Override
    public void deleteBookMarkById(Integer id) {
        Bookmark byId = bookmarkRepository.findById(id)
                .orElseThrow(() -> new BookmarkNotFoundException("Bookmark not found"));
        bookmarkRepository.deleteById(byId.getId());
    }
}
