package com.example.onthick.backend.controller;

import com.example.onthick.backend.dto.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {
    @GetMapping("/view-cart")
    public String view_cart(Model model, HttpSession session) {
        return "cart";
    }

    @GetMapping("/plus/{id}")
    public String plus(HttpSession session, @PathVariable("id") String id) {
        List<CartItem> cartItemList = (List<CartItem>) session.getAttribute("cartItemList");
        if (cartItemList == null) {
            return "";
        } else {
            for (CartItem cartItem : cartItemList) {
                if (cartItem.getProduct().getProduct_id() == Long.valueOf(id)) {
                    cartItem.setAmount(cartItem.getAmount() + 1);
                    break;
                }
            }
            Double total = 0.0;
            for (CartItem cartItem : cartItemList) {
                total += cartItem.getAmount() * cartItem.getProduct().getProductPrices().get(0).getPrice();
            }
            session.setAttribute("total", total);
            session.setAttribute("cartItemList", cartItemList);
            return "cart";
        }
    }

    @GetMapping("/minus/{id}")
    public String minus(HttpSession session, @PathVariable("id") String id) {
        List<CartItem> cartItemList = (List<CartItem>) session.getAttribute("cartItemList");
        if (cartItemList == null) {
            return "";
        } else {
            for (CartItem cartItem : cartItemList) {
                if (cartItem.getProduct().getProduct_id() == Long.valueOf(id)) {
                    if (cartItem.getAmount() <= 1) {
                        cartItem.setAmount(1);
                        break;
                    } else {
                        cartItem.setAmount(cartItem.getAmount() - 1);
                        break;
                    }
                }
            }
            Double total = 0.0;
            for (CartItem cartItem : cartItemList) {
                total += cartItem.getAmount() * cartItem.getProduct().getProductPrices().get(0).getPrice();
            }
            session.setAttribute("total", total);
            session.setAttribute("cartItemList", cartItemList);
            return "cart";
        }
    }

}
