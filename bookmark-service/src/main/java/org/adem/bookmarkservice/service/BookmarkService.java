package org.adem.bookmarkservice.service;

import org.adem.bookmarkservice.dto.BookmarkPageResponse;
import org.adem.bookmarkservice.dto.BookmarkRequest;
import org.adem.bookmarkservice.dto.BookmarkResponse;

public interface BookmarkService {

    void bookmark(BookmarkRequest bookmarkRequest);
    BookmarkResponse getBookmarkById(Integer id);
    BookmarkPageResponse getAllBookmarks(int page, int count);
    void deleteBookMarkById(Integer id);
}
