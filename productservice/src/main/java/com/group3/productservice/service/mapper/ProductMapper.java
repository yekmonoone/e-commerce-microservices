package com.group3.productservice.service.mapper;



import com.group3.productservice.entity.Product;
import com.group3.productservice.service.dto.request.AddProductRequest;
import com.group3.productservice.service.dto.response.CatalogueProductResponse;
import com.group3.productservice.service.dto.response.GetProductByIdResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
    CatalogueProductResponse productDtoResponseFromProduct(Product product);
    Product productFromAddRequestToProduct(AddProductRequest addProductRequest);
    GetProductByIdResponse productFromGetProduct(Product product);
}
