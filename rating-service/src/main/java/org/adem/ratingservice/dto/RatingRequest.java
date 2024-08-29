package org.adem.ratingservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RatingRequest {
    private Integer countOfStars;
    private Integer productId;
    private String comment;
    private LocalDateTime ratedAt;
    private Integer helpfulVotes;
}
