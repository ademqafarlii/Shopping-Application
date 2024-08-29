package org.adem.cartservice.repository;

import org.adem.cartservice.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer >{
    Optional<Cart> findBySaveWithThisName(String name);
    Optional<Cart> findByProductID(Integer productId);
}
