package com.inventoryservice.service;

import com.inventoryservice.dto.InventoryDto;

import java.util.List;

public interface InventoryService {

    List<InventoryDto> getAllInventories();
    InventoryDto createInventory(InventoryDto inventoryDto);
    Integer getStock(int productId);
    InventoryDto updateStock(int productId, int stockQuantity);

    void deleteInventory(Long inventoryId);


}
