package com.example.productmanager.service;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {

        this.productRepository = productRepository;
    }
    // 상품 등록
    public Long join(Product product) {
        validateDuplicateMember(product); //중복 상품 검증
        productRepository.save(product);
        return product.getNo();
    }
    private void validateDuplicateMember(Product product) {
        productRepository.findByName(product.getName())
                .ifPresent(p -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 상품 조회
    public List<Product> findProducts() {

        return productRepository.findAll();
    }

    public Optional<Product> findByNo(Long productNo) {

        return productRepository.findByNo(productNo);
    }

    public Optional<Product> findByName(String productName) {

        return productRepository.findByName(productName);
    }
    public Optional<Product> findOne(Long productNo) {

        return productRepository.findByNo(productNo);
    }

    public void update(Product product) {
        //
        // db 에서 no로 식별
        // 식별 된 데이터 를 가지고 온다
        // 데이터에서 대입
        Product productToUpdate = productRepository.findByNo(product.getNo()).get();
        productToUpdate.setName(product.getName());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setStock(product.getStock());
        System.out.println(product.getName());
        System.out.println(product.getPrice());
        System.out.println(product.getStock());

    }

    public void delete(Product product) {

        productRepository.delete(product);
    }



}
