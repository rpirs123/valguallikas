package org.example;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Product {

    @JsonProperty
    private final String name;
    @JsonProperty
    private final String kcal;
    @JsonProperty
    private final String fat;
    @JsonProperty
    private final String carbohydrates;
    @JsonProperty
    private final String sugars;
    @JsonProperty
    private final String protein;
    @JsonProperty
    private final String salt;
    @JsonProperty
    private final String created;


    public Product(String name, String kcal, String fat, String carbohydrates,String sugars, String protein, String salt) {
        this.name = name;
        this.kcal = kcal;
        this.fat = fat;
        this.carbohydrates = carbohydrates;
        this.sugars = sugars;
        this.protein = protein;
        this.salt = salt;
        this.created = LocalDateTime.now().toString();
    }

    @Override
    public String toString(){
        return "Product{" +
                "name=" + name + "" +
                "kcal=" + kcal + "" +
                "fat=" + fat + "" +
                "carbohydrates=" + carbohydrates + "" +
                "sugars=" + sugars + "" +
                "protein=" + protein + "" +
                "salt=" + salt + "" +
                "created=" + created + "}";
    }

    public static void writeProductsToJson(List<Product> products, String filename){
        System.out.println("writing json");
        ObjectMapper objectMapper = new ObjectMapper();
        List<Product> filteredProducts = products.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        try {
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.writeValue(new File("nutritionData/" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy_MM_dd")) + "_"+ filename + ".json"),filteredProducts);
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

}

