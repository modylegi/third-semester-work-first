package ru.kpfu.itis.services.impl;

import ru.kpfu.itis.dao.base.PizzaRepository;
import ru.kpfu.itis.dto.PizzaDto;
import ru.kpfu.itis.models.Pizza;
import ru.kpfu.itis.services.base.PizzaService;

public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;

    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }



    @Override
    public PizzaDto addPizza(PizzaDto pizzaDto) {
        Pizza pizza = Pizza.builder()
                .id(pizzaDto.getId())
                .pizza_name(pizzaDto.getIngredients())
                .ingredients(pizzaDto.getIngredients())
                .price(pizzaDto.getPrice())
                .photoPath(pizzaDto.getPhotoPath())
                .build();
//                .author(User.builder()
//                        .id(postDto.getAuthor().getId())
//                        .avatarId(postDto.getAuthor().getAvatarId())
//                        .email(postDto.getAuthor().getEmail())
//                        .firstName(postDto.getAuthor().getFirstName())
//                        .lastName(postDto.getAuthor().getLastName())
//                        .build())
//                .content(postDto.getContent())
//                .createdAt(postDto.getCreatedAt())
//                .build();
        Pizza savedPizza = pizzaRepository.save(pizza);
        return PizzaDto.from(savedPizza);
    }
}
