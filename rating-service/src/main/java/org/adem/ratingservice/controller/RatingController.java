package org.adem.ratingservice.controller;

import org.adem.ratingservice.dto.RatingRequest;
import org.adem.ratingservice.service.RatingService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/rating")
public class RatingController {
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/rate")
    @ResponseStatus(HttpStatus.CREATED)
    void rate(@RequestBody RatingRequest ratingRequest){
        ratingService.rate(ratingRequest);
    }

    @DeleteMapping("/delete-rate-by-id/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    void deleteRateByID(@PathVariable Integer id){
        ratingService.deleteRateByID(id);
    }
}
