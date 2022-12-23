package com.example.productmanager.service;

import com.example.productmanager.domain.Product;
import com.example.productmanager.repository.MemoryProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProductServiceTest {

    ProductService productService;
    MemoryProductRepository productRepository;

    @BeforeEach
    public void beforeEach() {
        productRepository = new MemoryProductRepository();
        productService = new ProductService(productRepository);
    }

    @AfterEach
    public void afterEach() {

        productRepository.clearStore();
    }
    @Test
    public void 상품기입() throws Exception {
        //Given
        Product product = new Product();
        product.setName("hello");

        //When
        Long saveNo = productService.join(product);

        //Then
        Product findMember = productRepository.findByNo(saveNo).get();
        assertEquals(product.getName(), findMember.getName());
    }
    @Test
    public void 중복_상품_예외() throws Exception {
        //Given
        Product product1 = new Product();
        product1.setName("spring");
        Product product2 = new Product();
        product2.setName("spring");

        //When
        productService.join(product1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> productService.join(product2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 상품입니다.");
    }


}
