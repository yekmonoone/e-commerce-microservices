package com.group3.productservice.repository;


import com.group3.productservice.entity.Catalogue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {
    Optional<Catalogue> findById(Integer catalogueId);

}
