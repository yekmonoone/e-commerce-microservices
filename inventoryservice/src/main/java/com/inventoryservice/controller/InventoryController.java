package com.inventoryservice.controller;

import com.inventoryservice.dto.InventoryDto;
import com.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3001")
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping("/add")
    public ResponseEntity<InventoryDto> createInventory(@RequestBody InventoryDto inventoryDto){

        InventoryDto savedStock = inventoryService.createInventory(inventoryDto);
        return new ResponseEntity<>(savedStock, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<InventoryDto>> getAllInventories(){
        List<InventoryDto> inventories = inventoryService.getAllInventories();
        return ResponseEntity.ok(inventories);
    }

    @GetMapping("/check/{productId}")
    public ResponseEntity<Integer> getStock(@PathVariable("productId") int productId){
        Integer stock = inventoryService.getStock(productId);
        return ResponseEntity.ok(stock);
    }

    @PutMapping("/update/{id}/{stock}")
    public ResponseEntity<InventoryDto> updateStock(@PathVariable("id") int productId,
                                                  @PathVariable("stock") int stockQuantity){
        InventoryDto courseDto = inventoryService.updateStock(productId, stockQuantity);
        return ResponseEntity.ok(courseDto);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") Long inventoryId){
        inventoryService.deleteInventory(inventoryId);
        return ResponseEntity.ok("Stock deleted successfully!");
    }

}
