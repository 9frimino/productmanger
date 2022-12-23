package com.example.productmanager.controller;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.SpringDataProductRepository;
import com.example.productmanager.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductController {

    private final ProductService productService;
    private final SpringDataProductRepository springDataProductRepository;

    @Autowired
    public ProductController(ProductService productService,
                             SpringDataProductRepository springDataProductRepository){

        this.productService = productService;
        this.springDataProductRepository = springDataProductRepository;
    }

    @GetMapping(value = "/products/new")
    public String createForm() {

        return "products/createProductForm";
    }

    @PostMapping(value = "/products/new")
    public String create(ProductForm form) {

        Product product = new Product();
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());

        productService.join(product);
        
        return "redirect:/";
    }

    @GetMapping(value = "/products")
    public String list(Model model) {
        List<Product> products = productService.findProducts();
        model.addAttribute("products", products);
        return "products/productList";
    }

    @GetMapping(value = "/products/{no}/edit")
    public String updateProductForm(@PathVariable("no") Long no, Model model) {
        Product product = productService.findOne(no).get();
        model.addAttribute("product", product);
        return "products/productUpdateForm";
    }

    @PostMapping(value = "/products/{no}/edit")
    public String updateProduct(@PathVariable("no") Long no, Product form) {

        // client 에서 데이터 받은 값을 가공해서 service 로 보내줘야한다.
        Product product = new Product();
        product.setNo(no);
        product.setName(form.getName());
        product.setPrice(form.getPrice());
        product.setStock(form.getStock());

        productService.update(product);

        return "redirect:/products";
    }

    @GetMapping(value = "/products/{no}/delete")
    public String deleteProductForm(@PathVariable Long no, Model model) {

        Product product = productService.findOne(no).get();

        productService.delete(product);

        return "redirect:/products";
    }

}


