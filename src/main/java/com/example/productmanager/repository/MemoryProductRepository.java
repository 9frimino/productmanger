package com.example.productmanager.repository;

import com.example.productmanager.domain.Product;

import java.util.*;

public class MemoryProductRepository implements ProductRepository {
    private static Map<Long, Product> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Product save(Product product) {
        product.setNo(++sequence);
        store.put(product.getNo(), product);
        return product;
    }
    @Override
    public Optional<Product> findByNo(Long no) {

        return Optional.ofNullable(store.get(no));
    }
    @Override
    public Optional<Product> findByName(String name) {
        return store.values().stream()
                .filter(product -> product.getName().equals(name))
                .findAny();
    }
    @Override
    public List<Product> findAll() {

        return new ArrayList<>(store.values());
    }

    @Override
    public void delete(Product product) {

    }

    public void clearStore() {
        store.clear();
    }
}
