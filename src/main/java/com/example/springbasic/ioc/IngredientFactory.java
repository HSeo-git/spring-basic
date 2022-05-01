package com.example.springbasic.ioc;


import org.springframework.stereotype.Component;

@Component //make this class an object and register it to IoC container
public class IngredientFactory {
    public Ingredient get(String menu) {
        switch (menu) {
            case "pizza":
                return new Pork("ham");
            case "stake":
                return new Beef("wagyu");
            case "chicken":
                return new Fried("fried");
            default:
                return null;
        }
    }
}
