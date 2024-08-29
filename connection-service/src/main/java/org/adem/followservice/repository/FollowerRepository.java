package org.adem.followservice.repository;

import org.adem.followservice.model.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowerRepository extends JpaRepository<Follower,Integer> {
    Optional<Follower> findByUsername(String username);

    void deleteByUsername(String userName);
}
