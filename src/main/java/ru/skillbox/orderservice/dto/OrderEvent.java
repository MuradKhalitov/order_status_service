package ru.skillbox.orderservice.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private String product;
    private Integer quantity;
}
