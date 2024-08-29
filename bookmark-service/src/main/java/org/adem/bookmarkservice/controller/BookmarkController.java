package org.adem.bookmarkservice.controller;

import org.adem.bookmarkservice.dto.BookmarkPageResponse;
import org.adem.bookmarkservice.dto.BookmarkRequest;
import org.adem.bookmarkservice.dto.BookmarkResponse;
import org.adem.bookmarkservice.service.BookmarkService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @PostMapping("/bookmark-product")
    @ResponseStatus(HttpStatus.CREATED)
    void bookmark(@RequestBody BookmarkRequest bookmarkRequest) {
        bookmarkService.bookmark(bookmarkRequest);
    }

    @GetMapping("/get-product-by-id/{id}")
    @ResponseStatus(HttpStatus.OK)
    BookmarkResponse getBookmarkById(@PathVariable Integer id) {
        return bookmarkService.getBookmarkById(id);
    }

    @GetMapping("/get-all-products")
    @ResponseStatus(HttpStatus.OK)
    BookmarkPageResponse getAllBookmarks(@RequestParam int page, @RequestParam int count) {
        return bookmarkService.getAllBookmarks(page, count);
    }

    @DeleteMapping("/delete-bookmark-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteBookMarkById(@PathVariable Integer id) {
        bookmarkService.deleteBookMarkById(id);
    }
}
