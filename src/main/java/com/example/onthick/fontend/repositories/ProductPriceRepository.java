package com.example.onthick.fontend.repositories;

import com.example.onthick.fontend.models.Product;
import com.example.onthick.fontend.models.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductPriceRepository extends JpaRepository<ProductPrice,Long>, JpaSpecificationExecutor<ProductPrice> {
    @Query("SELECT pp FROM ProductPrice pp WHERE pp.product = :product")
    List<ProductPrice> findByProduct(Product product);
}
