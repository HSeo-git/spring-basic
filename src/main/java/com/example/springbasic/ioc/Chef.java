package com.example.springbasic.ioc;

import org.springframework.stereotype.Component;

@Component
public class Chef {
    //Chef know Ingredient Factory

    private IngredientFactory ingredientFactory;

    //DI for the cooperation of Chef and IngredientFactory
    public Chef(IngredientFactory ingredientFactory) {
        this.ingredientFactory = ingredientFactory;
    }

    public String cook(String menu) {
        //prepare ingredients
        Ingredient ingredient = ingredientFactory.get(menu);
        //return food
        return ingredient.getName()+" "+menu;
    }
}
