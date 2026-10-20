package com.leslie.demo.designpatterns;

public class AbsrtractFactory {
    public static void main(String[] args) {
        Factory factory = new FactoryA();
        Product product = factory.createProduct();
        System.out.println("Created product: " + product.getClass().getSimpleName());
    }
}

interface Product {}
class A implements Product {}
class B implements Product {}

abstract class Factory {
    abstract Product createProduct();
}

class FactoryA extends Factory {
    Product createProduct() { return new A(); }
}