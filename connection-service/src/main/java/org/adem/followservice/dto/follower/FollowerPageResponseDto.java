package org.adem.followservice.dto.follower;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class FollowerPageResponseDto {
    private List<FollowerDto> followerList;
    private long getTotalElements;
    private int getTotalPages;
    private boolean hasNext;
}
