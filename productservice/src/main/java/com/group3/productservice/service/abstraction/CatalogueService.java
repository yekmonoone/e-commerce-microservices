package com.group3.productservice.service.abstraction;



import com.group3.productservice.service.dto.response.CatalogueProductResponse;

import java.util.List;

public interface CatalogueService {

    List<CatalogueProductResponse> getCatalogueProducts(Integer catalogueId);
}
