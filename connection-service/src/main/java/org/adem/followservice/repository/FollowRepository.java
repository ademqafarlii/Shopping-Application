package org.adem.followservice.repository;

import org.adem.followservice.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<Follow,Integer> {
    Optional<Follow> getFollowByUsername(String username);

    void deleteByUsername(String username);
}
