package org.adem.inventory.repository;



import org.adem.inventory.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InventoryRepository extends JpaRepository<Inventory,Integer> {


    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
