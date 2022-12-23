package com.example.productmanager.repository;


import com.example.productmanager.domain.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryProductRepositoryTest {
     MemoryProductRepository repository = new MemoryProductRepository();

     @AfterEach
     public void afterEach() {
         repository.clearStore();
     }

    @Test
    public void save() {
        // given
        Product product = new Product();
        product.setName("spring");

        //when
        repository.save(product);

        //then
        Product result = repository.findByNo(product.getNo()).get();
        assertThat(result).isEqualTo(product);
    }

    @Test
    public void findByName() {
        Product product1 = new Product();
        product1.setName("spring1");
        repository.save(product1);

        Product product2 = new Product();
        product2.setName("spring2");
        repository.save(product2);

        //when
        Product result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(product1);

    }
    @Test
    public void findByAll() {
        // given
        Product member1 = new Product();
        member1.setName("spring1");
        repository.save(member1);

        Product member2 = new Product();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Product> result = repository.findAll();
        //then
        assertThat(result.size()).isEqualTo(2);

    }

}


