package karadahitotsu.fashionshop.controllers;

import karadahitotsu.fashionshop.Entity.Products;
import karadahitotsu.fashionshop.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainRestController {
    @Autowired
    ProductsRepository productsRepository;
    @PostMapping("/image")
    public void image(@RequestBody Products products){
        productsRepository.save(products);

    }

}
