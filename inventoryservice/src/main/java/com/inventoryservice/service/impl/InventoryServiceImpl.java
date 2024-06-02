package com.inventoryservice.service.impl;

import com.inventoryservice.client.ProductServiceClient;
import com.inventoryservice.dto.InventoryDto;
import com.inventoryservice.entity.Inventory;
import com.inventoryservice.exception.DuplicateInventoryException;
import com.inventoryservice.exception.ResourceNotFoundException;
import com.inventoryservice.mapper.InventoryMapper;
import com.inventoryservice.repository.InventoryRepository;
import com.inventoryservice.service.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private InventoryRepository inventoryRepository;
    private final ProductServiceClient productServiceClient;


    @Override
    public List<InventoryDto> getAllInventories() {
        List<Inventory> inventories = inventoryRepository.findAll();

        return inventories.stream().map((inventory) -> InventoryMapper.mapToInventoryDto(inventory))
                .collect(Collectors.toList());
    }
    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto) {
        boolean productExists = productServiceClient.checkProductExists(inventoryDto.getProductId());

        if (!productExists) {
            throw new ResourceNotFoundException("Product with ID " + inventoryDto.getProductId() + " does not exist.");
        }

        Inventory existingInventory = inventoryRepository.findByProductId(inventoryDto.getProductId());

        if (existingInventory != null) {
            throw new DuplicateInventoryException("Inventory for product ID " + inventoryDto.getProductId() + " already exists.");
        }

        Inventory inventory = InventoryMapper.mapToInventory(inventoryDto);
        Inventory savedInventory = inventoryRepository.save(inventory);
        return InventoryMapper.mapToInventoryDto(savedInventory);
    }
    public Integer getStock(int productId) {

        boolean productExists = productServiceClient.checkProductExists(productId);
        if (!productExists) {
            throw new ResourceNotFoundException("Product with ID " + productId + " does not exist.");
        }
        Inventory inventory = inventoryRepository.findByProductId(productId);

        if (inventory == null) {
            throw new ResourceNotFoundException("Stock not found");
        }
        return  inventory.getStockQuantity();
    }

    @Override
    public InventoryDto updateStock(int productId, int stockQuantity) {

        boolean productExists = productServiceClient.checkProductExists(productId);
        if (!productExists) {
            throw new ResourceNotFoundException("Product with ID " + productId + " does not exist.");
        }
        Inventory inventory = inventoryRepository.findByProductId(productId);

        if(inventory == null){
            new ResourceNotFoundException("Stock is not exist with given id:" + productId);
        }

        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be less than 0.");
        }

        inventory.setStockQuantity(stockQuantity);

        Inventory updatedInventoryObj = inventoryRepository.save(inventory);

        return InventoryMapper.mapToInventoryDto(updatedInventoryObj);

    }

    @Override
    public void deleteInventory(Long inventoryId) {
        Inventory inventory = inventoryRepository.findById(inventoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Stock is not exist with given id:" + inventoryId));
        inventoryRepository.deleteById(inventoryId);

    }

}
