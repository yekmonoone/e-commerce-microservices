package com.group3.productservice.service.implementation;


import com.group3.productservice.entity.Catalogue;
import com.group3.productservice.entity.Product;
import com.group3.productservice.repository.CatalogueRepository;
import com.group3.productservice.service.abstraction.CatalogueService;
import com.group3.productservice.service.dto.response.CatalogueProductResponse;
import com.group3.productservice.service.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogueServiceImpl implements CatalogueService {
    private CatalogueRepository catalogueRepository;


    @Override
    public List<CatalogueProductResponse> getCatalogueProducts(Integer catalogueId) {
        Catalogue catalogue = catalogueRepository.findById(catalogueId).orElseThrow(()-> new RuntimeException("No Catalogue Found"));
        List<Product> products = catalogue.getProducts();
        List<CatalogueProductResponse> catalogueProductResponse = new ArrayList<>();
        for (Product product : products) {
            catalogueProductResponse.add(ProductMapper.INSTANCE.productDtoResponseFromProduct(product));
        }
        return catalogueProductResponse;
    }
}

