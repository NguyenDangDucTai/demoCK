package com.example.onthick.fontend.repositories;

import com.example.onthick.fontend.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long>, JpaSpecificationExecutor<ProductImage> {
}
