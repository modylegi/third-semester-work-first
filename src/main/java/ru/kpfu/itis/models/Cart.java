package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
//@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Pizza{
    private int quantity;
    private int totalSum;
}
