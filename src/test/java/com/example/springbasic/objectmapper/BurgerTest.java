package com.example.springbasic.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BurgerTest {

    @Test
    public void convert_jave_to_json() throws JsonProcessingException {
        //prepare
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> ingredients = Arrays.asList("shirimp, beef, tomato, bread");
        Burger burger = new Burger("cheeseburger", 5500, ingredients);
        //execution
        String json =  objectMapper.writeValueAsString(burger);
        //expect
        String expected = "{\"name\":\"cheeseburger\",\"price\":5500,\"ingredients\":[\"shirimp, beef, tomato, bread\"]}";
        //check
        assertEquals(expected, json);
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
    }

    @Test
    public void convert_json_to_java() throws JsonProcessingException {
        //prepare
        ObjectMapper objectMapper = new ObjectMapper();
        /*
        {
         "name" : "cheeseburger"
         "price" : 5500
         "ingredients" : ["shirimp", "beef", "tomato", "bread"]
         }
         */
         ObjectNode objectNode = objectMapper.createObjectNode();
         objectNode.put("name", "cheeseburger");
         objectNode.put("price", "5500");

         ArrayNode arrayNode = objectMapper.createArrayNode();
         arrayNode.add("shirimp");
         arrayNode.add("beef");
         arrayNode.add("tomato");
         arrayNode.add("bread");
         objectNode.set("ingredients", arrayNode);
         String json = objectNode.toString();


        //execution
        Burger burger = objectMapper.readValue(json, Burger.class);

        //expect
        List<String> ingredients = Arrays.asList("shirimp", "beef", "tomato", "bread");
        Burger expected = new Burger("cheeseburger", 5500, ingredients);

        //check
        assertEquals(expected.toString(), burger.toString());
        JsonNode jsonNode = objectMapper.readTree(json);
        System.out.println(jsonNode.toPrettyString());
        System.out.println(burger.toString());
    }
}