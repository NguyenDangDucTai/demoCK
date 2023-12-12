package com.example.onthick;

import com.example.onthick.fontend.enums.ProductStatus;
import com.example.onthick.fontend.models.Customer;
import com.example.onthick.fontend.models.Product;
import com.example.onthick.fontend.models.ProductImage;
import com.example.onthick.fontend.models.ProductPrice;
import com.example.onthick.fontend.repositories.CustomerRepository;
import com.example.onthick.fontend.repositories.ProductImageRepository;
import com.example.onthick.fontend.repositories.ProductPriceRepository;
import com.example.onthick.fontend.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import net.datafaker.Faker;
import net.datafaker.providers.base.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.Random;

@SpringBootApplication
public class OnthiCkApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(OnthiCkApplication.class, args);
    }

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductPriceRepository productPriceRepository;


    @Override
    @Transactional
    public void run(String... args){
        Faker faker = new Faker();
        Random rnd = new Random();
        Device device =faker.device();

                // Tạo dữ liệu mẫu cho Customer
        for (int i = 0; i < 10; i++) {
            Customer customer = new Customer(
                    faker.name().fullName(),
                    faker.internet().emailAddress(),
                    faker.phoneNumber().cellPhone(),
                    faker.address().fullAddress()
            );
            customerRepository.save(customer);
        }

        for(int i = 0; i<30; i++){
            Product product = new Product(
                    device.modelName(),
                    faker.lorem().paragraph(30),
//                    rnd.nextInt(10)%2==0?"Kg":"piece",
                    "piece",
                    device.manufacturer(),
                    ProductStatus.ACTIVE
            );
            productRepository.save(product);
            ProductImage img = new ProductImage("assets/img.png", "sample image");
            img.setProduct(product);
            productImageRepository.save(img);

            ProductPrice price = new ProductPrice(LocalDateTime.now(), rnd.nextInt(1500), "Note #" + i);
            price.setProduct(product);
            productPriceRepository.save(price);
        }




    }
}
