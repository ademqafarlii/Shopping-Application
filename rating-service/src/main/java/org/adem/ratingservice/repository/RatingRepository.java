package org.adem.ratingservice.repository;

import org.adem.ratingservice.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Integer> {
    Optional<Rating> findByProductId(Integer id);
}
