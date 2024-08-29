package org.adem.ratingservice.mapper;

import org.adem.ratingservice.dto.RatingRequest;
import org.adem.ratingservice.dto.RatingResponse;
import org.adem.ratingservice.exception.RatingNotFoundException;
import org.adem.ratingservice.model.Rating;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class RatingMapper {
    public Rating toRating(RatingRequest ratingRequest){
        if (ratingRequest==null){
            throw new RatingNotFoundException("Rating not found");
        }
        return Rating.builder()
                .ratedAt(LocalDateTime.now())
                .comment(ratingRequest.getComment())
                .countOfStars(ratingRequest.getCountOfStars())
                .helpfulVotes(ratingRequest.getHelpfulVotes())
                .productId(ratingRequest.getProductId())
                .build();
    }

    public RatingResponse toRatingResponse(Rating rating){
        if (rating==null){
            throw new RatingNotFoundException("Rating not found");
        }
        return RatingResponse.builder()
                .ratedAt(rating.getRatedAt())
                .comment(rating.getComment())
                .countOfStars(rating.getCountOfStars())
                .helpfulVotes(rating.getHelpfulVotes())
                .productId(rating.getProductId())
                .build();
    }
}
