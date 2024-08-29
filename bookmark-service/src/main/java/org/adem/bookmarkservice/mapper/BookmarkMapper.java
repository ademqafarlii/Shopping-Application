package org.adem.bookmarkservice.mapper;

import org.adem.bookmarkservice.dto.BookmarkRequest;
import org.adem.bookmarkservice.dto.BookmarkResponse;
import org.adem.bookmarkservice.exception.BookmarkNotFoundException;
import org.adem.bookmarkservice.model.Bookmark;
import org.springframework.stereotype.Service;

@Service
public class BookmarkMapper {
    public Bookmark toBookMark(BookmarkRequest bookmarkRequest){
        if (bookmarkRequest==null){
            throw new BookmarkNotFoundException("Bookmark not found");
        }
        return Bookmark.builder()
                .productId(bookmarkRequest.getProductId())
                .build();
    }

    public BookmarkResponse toBookMarkResponse(Bookmark bookmark){
        if (bookmark==null){
            throw new BookmarkNotFoundException("Bookmark not found");
        }
        return BookmarkResponse.builder()
                .productId(bookmark.getProductId())
                .build();
    }
}
