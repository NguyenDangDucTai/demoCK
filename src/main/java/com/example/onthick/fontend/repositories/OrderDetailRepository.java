package com.example.onthick.fontend.repositories;

import com.example.onthick.fontend.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>, JpaSpecificationExecutor<OrderDetail> {

}
