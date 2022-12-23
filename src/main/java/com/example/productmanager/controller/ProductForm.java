package com.example.productmanager.controller;

import lombok.Getter;
import lombok.Setter;

//import javax.persistence.criteria.From;
@Getter
@Setter
public class ProductForm {
    private String name;
    private int price;
    private int stock;
}

