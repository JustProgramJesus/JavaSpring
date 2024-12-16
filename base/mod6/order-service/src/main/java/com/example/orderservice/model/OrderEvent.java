package com.example.orderservice.model;

public class OrderEvent {
    private String product;
    private Integer quantity;

    // Default constructor
    public OrderEvent() {}

    // Parameterized constructor
    public OrderEvent(String product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Getters and Setters
    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderEvent{" +
                "product='" + product + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}