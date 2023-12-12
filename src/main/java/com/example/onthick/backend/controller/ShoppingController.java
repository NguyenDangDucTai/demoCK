package com.example.onthick.backend.controller;


import com.example.onthick.backend.dto.CartItem;
import com.example.onthick.fontend.models.Product;
import com.example.onthick.fontend.models.ProductImage;
import com.example.onthick.fontend.models.ProductPrice;
import com.example.onthick.fontend.repositories.ProductImageRepository;
import com.example.onthick.fontend.repositories.ProductPriceRepository;
import com.example.onthick.fontend.repositories.ProductRepository;
import com.example.onthick.fontend.services.ProductService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;
    @Autowired
    private ProductImageRepository productImageRepository;


    @GetMapping
    public String showProduct(
            HttpSession session,
            Model model,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size
    ){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<Product> candidatePage = productService.findPaginated(currentPage - 1,
                pageSize, "name", "asc");
        //Đưa dữ liệu qua cho html
        model.addAttribute("productPage", candidatePage);


        int totalPages = candidatePage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "shopping";
    }


    @GetMapping("/add2cart/{id}")
    public String addToCart(HttpSession httpSession, Model model, @PathVariable("id") long id){
        List<CartItem> cartItemList = (List<CartItem>) httpSession.getAttribute("cartItemList");
        if (cartItemList == null) {
            cartItemList = new ArrayList<>();
        }
        Product product = productRepository.findById(id).orElse(new Product());
        boolean found = false;
        for (CartItem cartItem : cartItemList) {
            if (cartItem.getProduct().getProduct_id() == id) {
                cartItem.setAmount(cartItem.getAmount() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            CartItem newCartItem = new CartItem();
            newCartItem.setProduct(product);
            newCartItem.setAmount(1);
            cartItemList.add(newCartItem);
        }
        List<ProductImage> productImageLis = productImageRepository.findAll();
        for (CartItem cartItem : cartItemList) {
            cartItem.getProduct().getProductImageList().get(0).setPath(productImageLis.get(0).getPath());
            ProductPrice productPrice = productPriceRepository.findByProduct(cartItem.getProduct()).get(0);
            cartItem.getProduct().getProductPrices().get(0).setPrice(productPrice.getPrice());
        }
        Double total = 0.0;
        for (CartItem cartItem : cartItemList) {
            total += cartItem.getAmount() * cartItem.getProduct().getProductPrices().get(0).getPrice();
        }
        System.out.println(cartItemList);
        httpSession.setAttribute("total", total);
        httpSession.setAttribute("cartItemList", cartItemList);
        httpSession.setAttribute("sizecartItemList", cartItemList.size());

        return "redirect:/shopping";
    }

}
