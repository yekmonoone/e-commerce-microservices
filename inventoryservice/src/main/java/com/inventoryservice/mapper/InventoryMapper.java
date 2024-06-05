package com.inventoryservice.mapper;

import com.inventoryservice.dto.InventoryDto;
import com.inventoryservice.entity.Inventory;

public class InventoryMapper {

    public  static InventoryDto mapToInventoryDto(Inventory inventory){
        return new InventoryDto(
                inventory.getId(),
                inventory.getProductId(),
                inventory.getStockQuantity()
        );
    }
    public static Inventory mapToInventory(InventoryDto inventoryDto){
        return new Inventory(
                inventoryDto.getId(),
                inventoryDto.getProductId(),
                inventoryDto.getStockQuantity()
        );
    }
}
