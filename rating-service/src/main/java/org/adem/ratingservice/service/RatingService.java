package org.adem.ratingservice.service;

import org.adem.ratingservice.dto.RatingRequest;

public interface RatingService {
    void rate(RatingRequest ratingRequest);
    void deleteRateByID(Integer id);
}
