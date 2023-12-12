package com.example.onthick.backend.controller;

import com.example.onthick.fontend.models.Customer;
import com.example.onthick.fontend.services.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private CustomerService customerServices;

    @GetMapping("/")
    public static String HelloWorld(){
        return "login";
    }

    @GetMapping("/login3")
    public String checkLoGin(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        List<Customer> customerList = customerServices.getAll();
        for (Customer customer : customerList) {
            if (customer.getEmail().equals(username)) {
                // Nếu thông tin đăng nhập chính xác, thực hiện đăng nhập và chuyển hướng đến trang quản trị
                return "redirect:/shopping";
            }
        }
        // Nếu thông tin không đúng, thiết lập thông báo lỗi trong session và trả về trang đăng nhập
        session.setAttribute("error", "Tài khoản hoặc mật khẩu không chính xác");
        return "login";
    }


}
