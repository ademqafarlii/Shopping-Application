package org.adem.followservice.dto.follow;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class FollowPageResponse {
    List<FollowRequest> followedCustomersList;
    long totalElements;
    int totalPages;
    boolean hasNextPage;
}
