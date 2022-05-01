package com.example.springbasic.ioc;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest //for IoC DI
class ChefTest {
    @Autowired IngredientFactory ingredientFactory; //Get the component from IoC
    @Autowired Chef chef;

    @Test
    void make_pizza(){
        //prepare
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "pizza";

        //execution
        String food = chef.cook(menu);

        //expectation
        String expected = "ham pizza";

        //compare
        assertEquals(expected, food);
        System.out.println(food);

    }

    @Test
    void make_stake() {
        //prepare
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "stake";
        //execution
        String food = chef.cook(menu);
        //expect
        String expected = "wagyu stake";
        //compare
        assertEquals(expected, food);
        System.out.println(food);
    }

    @Test
    void cook_chicken() {
        //prepare
        //IngredientFactory ingredientFactory = new IngredientFactory();
        //Chef chef = new Chef(ingredientFactory);
        String menu = "chicken";
        //execution
        String food = chef.cook(menu);
        //expect
        String expected = "fried chicken";
        //compare
        assertEquals(expected, food);
        System.out.println(food);
    }
}