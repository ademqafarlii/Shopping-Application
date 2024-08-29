package org.adem.ratingservice.service.impl;

import org.adem.ratingservice.dto.RatingRequest;
import org.adem.ratingservice.dto.product.ProductResponse;
import org.adem.ratingservice.exception.AlreadyRatedException;
import org.adem.ratingservice.exception.ProductNotFoundException;
import org.adem.ratingservice.mapper.RatingMapper;
import org.adem.ratingservice.model.Rating;
import org.adem.ratingservice.repository.RatingRepository;
import org.adem.ratingservice.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final RatingMapper ratingMapper;
    private final RestTemplate restTemplate;

    public RatingServiceImpl(RatingRepository ratingRepository, RatingMapper ratingMapper, RestTemplate restTemplate) {
        this.ratingRepository = ratingRepository;
        this.ratingMapper = ratingMapper;
        this.restTemplate = restTemplate;
    }


    @Override
    public void rate(RatingRequest ratingRequest) {
        Optional<Rating> ratingByProductId = ratingRepository.findByProductId(ratingRequest.getProductId());
        if (ratingByProductId.isPresent()) {
            throw new AlreadyRatedException("This product already rated");
        }
        ProductResponse productResponse = restTemplate.getForObject(
                "http://PRODUCT/v1/product/exist-product-by-product-id?id={id}",
                ProductResponse.class,
                ratingRequest.getProductId()
        );
        if (productResponse == null || !ratingRequest.getProductId().equals(productResponse.getId())) {
            throw new ProductNotFoundException("Product not found");
        }
        ratingRepository.save(ratingMapper.toRating(ratingRequest));
    }

    @Override
    public void deleteRateByID(Integer id) {
        ratingRepository.deleteById(id);
    }
}
