package com.example.onthick.fontend.pks;

import com.example.onthick.fontend.models.Order;
import com.example.onthick.fontend.models.Product;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Setter @Getter
public class OrderDetailPK implements Serializable {
    private Order order;
    private Product product;
}
