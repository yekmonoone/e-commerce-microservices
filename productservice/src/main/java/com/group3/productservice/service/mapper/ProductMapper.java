package com.group3.productservice.service.mapper;



import com.group3.productservice.entity.Product;
import com.group3.productservice.service.dto.response.CatalogueProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
    CatalogueProductResponse productDtoResponseFromProduct(Product product);
}
