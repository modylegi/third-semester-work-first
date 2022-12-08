package ru.kpfu.itis.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {
    private Long id;
    private String pizza_name;
    private String ingredients;
    private int price;

    private String photoPath;


    public Pizza(String pizza_name, String ingredients, int price, String photoPath) {
        this.pizza_name = pizza_name;
        this.ingredients = ingredients;
        this.price = price;
        this.photoPath = photoPath;
    }
}
