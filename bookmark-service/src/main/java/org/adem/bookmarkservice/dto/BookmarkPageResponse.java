package org.adem.bookmarkservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class BookmarkPageResponse {
    private List<BookmarkResponse> bookmarkList;
    private long totalElements;
    private long totalPages;
    private boolean hasNext;
}
