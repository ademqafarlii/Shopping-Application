package org.adem.inventory.mapper;


import org.adem.inventory.dto.InventoryResponse;
import org.adem.inventory.exception.InventoryNotFoundException;
import org.adem.inventory.model.Inventory;
import org.springframework.stereotype.Service;

@Service
public class InventoryMapper {

    public InventoryResponse toInventoryResponse(Inventory inventory){
        if (inventory==null){
            throw new InventoryNotFoundException("Inventory not found");
        }
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity()>0)
                .quantity(inventory.getQuantity())
                .build();
    }
}
