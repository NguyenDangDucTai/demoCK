package com.example.onthick.backend.dto;

import com.example.onthick.fontend.models.Product;
import lombok.*;

import java.io.Serializable;



@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CartItem implements Serializable {
    private Product product;
    private int amount;
}
