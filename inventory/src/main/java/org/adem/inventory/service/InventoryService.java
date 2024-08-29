package org.adem.inventory.service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.adem.inventory.dto.InventoryResponse;
import org.adem.inventory.mapper.InventoryMapper;
import org.adem.inventory.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;

    public InventoryService(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode){
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(inventoryMapper::toInventoryResponse)
                .collect(Collectors.toList());
    }
}
