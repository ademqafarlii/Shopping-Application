package org.adem.bookmarkservice.repository;

import org.adem.bookmarkservice.model.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookmarkRepository extends JpaRepository<Bookmark,Integer> {
    Optional<Bookmark> findByProductId(Integer productId);
}
