package ru.kpfu.itis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.kpfu.itis.models.Pizza;

@Data
@AllArgsConstructor
@Builder
public class PizzaDto {
    private Long id;
    private String pizza_name;
    private String ingredients;
    private int price;
    private String photoPath;

    public static PizzaDto from(Pizza pizza) {
        return PizzaDto.builder()
                .id(pizza.getId())
                .pizza_name(pizza.getPizza_name())
                .ingredients(pizza.getIngredients())
                .price(pizza.getPrice())
                .photoPath(pizza.getPhotoPath())
                .build();
    }
}
