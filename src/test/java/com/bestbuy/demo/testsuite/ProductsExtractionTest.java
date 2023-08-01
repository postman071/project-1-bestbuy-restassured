package com.bestbuy.demo.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class ProductsExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/products")
                .then().statusCode(200);
    }

    // Extract the limit
    @Test
    public void test001() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    // Extract the total
    @Test
    public void test002() {
        int total = response.extract().path("total");
        System.out.println("The total is : " + total);
    }

    //Extract the name of 5th product
    @Test
    public void test003()
    {
        String name = response.extract().path("data[4].name");
        System.out.println("The name of 5th product is : " + name);
    }

    // Extract the names of all the products
    @Test
    public void test004() {
        List<String> names = response.extract().path("data.name");
        System.out.println("The name of all products are : " + names);
    }

    //Extract the productId of all the products
    @Test
    public void test005() {
        List<Integer> productId = response.extract().path("data.id");
        System.out.println("The productId of all the products is : " + productId);
    }

    //Print the size of the data list
    @Test
    public void test006() {
        List<Objects> dataList = response.extract().path("data");
        System.out.println("The size of the data list is : " + dataList.size());
    }

    // Get all the value of the product where product name = Energizer - MAX Batteries AA (4-Pack)
    @Test
    public void test007() {
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}");
        System.out.println("The value of product where product name 'Energizer - MAX Batteries AA (4-Pack)' is : " + values);
    }

    // Get the model of the product where product name = Energizer - N Cell E90 Batteries (2-Pack)
    @Test
    public void test008() {
        List<String> model = response.extract().path("data.findAll{it.name == 'Energizer - N Cell E90 Batteries (2-Pack)'}.model");
        System.out.println("The model of the product where product name 'Energizer - N Cell E90 Batteries (2-Pack)' is : " + model);
    }

    //Get all the categories of 8th products
    @Test
    public void test009() {
        List<Objects> categories = response.extract().path("data[7].categories");
        System.out.println("All the categories of 8th products are : " + categories);
    }

    // Get categories of the store where product id = 150115
    @Test
    public void test010()
    {
        List<Objects> categories = response.extract().path("data.findAll{it.id == 150115}.categories");
        System.out.println("The categories of the store where product id '150115' : " + categories);
    }

    //Get all the descriptions of all the products
    @Test
    public void test011()
    {
        List<String> descriptions = response.extract().path("data.description");
        System.out.println("The descriptions of all the products : " + descriptions);
    }

    // Get id of all the all categories of all the products
    @Test
    public void test012()
    {
        List<String> ids = response.extract().path("data.categories.id");
        System.out.println("The id of all the all categories of all the products : " + ids);
    }

    // Find the product names Where type = HardGood
    @Test
    public void test013()
    {
        List<String> productName = response.extract().path("data.findAll{it.type == 'HardGood'}.name");
        System.out.println("The product names Where type 'HardGood' : " + productName);
    }

    // Find the Total number of categories for the product where product name = Duracell - AA 1.5V CopperTop Batteries (4-Pack)
    @Test
    public void test014()
    {
        List<Objects> numberOfCategories = response.extract().path("data.findAll{it.name == 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)'}.categories");
        System.out.println("The Total number of categories for the product where product name 'Duracell - AA 1.5V CopperTop Batteries (4-Pack)' : " + numberOfCategories);
    }

    //Find the createdAt for all products whose price < 5.49
    @Test
    public void test015()
    {
        List<String> createdAt = response.extract().path("data.findAll{it.price < 5.49}.createdAt");
        System.out.println("The createdAt for all products whose price < 5.49 : " + createdAt);
    }

    // Find the name of all categories Where product name = “Energizer - MAX Batteries AA (4-Pack)”
    @Test
    public void test016()
    {
        List<String> names = response.extract().path("data.findAll{it.name == 'Energizer - MAX Batteries AA (4-Pack)'}.categories.name");
        System.out.println("The name of all categories Where product name 'Energizer - MAX Batteries AA (4-Pack)' : " + names);
    }

    //Find the manufacturer of all the products
    @Test
    public void test017()
    {
        List<String> manufacturer = response.extract().path("data.manufacturer");
        System.out.println("The manufacturer of all the products : " + manufacturer);
    }

    //Find the image of products whose manufacturer is = Energizer
    @Test
    public void test018()
    {
        List<String> image = response.extract().path("data.findAll{it.manufacturer == 'Energizer'}.image");
        System.out.println("The image of products whose manufacturer is 'Energizer' : " + image);
    }

    // Find the createdAt for all categories products whose price > 5.99
    @Test
    public void test019()
    {
        List<String> createdAt = response.extract().path("data.findAll{it.price > 5.99}.categories.createdAt");
        System.out.println("The createdAt for all categories products whose price > 5.99 : " + createdAt);
    }

    // Find the uri of all the products
    @Test
    public void test020()
    {
        List<String> url = response.extract().path("data.url");
        System.out.println("The uri of all the products : " + url);
    }


}
