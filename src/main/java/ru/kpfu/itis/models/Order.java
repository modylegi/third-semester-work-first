package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Order {
    private Long orderId;
    private Long userId;
    private int quantity;
    private String listOfOrderedPizza;
    private double orderCost;
    private String dateOfOrder;
}
