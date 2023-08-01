package com.bestbuy.demo.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;

public class StoresAssertionTest
{
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt()
    {
        RestAssured.baseURI="http://localhost";
        RestAssured.port=3030;
        response= given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1) Verify the if the total is equal to 1561
    @Test
    public void test001()
    {
        response.body("total",equalTo(1561));
    }
    // 2) Verify the if the stores of limit is equal to 10
    @Test
    public void test002()
    {
        response.body("limit",equalTo(10));
    }
    // 3) Check the single ‘Name’ in the Array list ( Inver Grove Heights)
    @Test
    public void test003()
    {
        response.body("data[1]",hasKey("name"));
    }
    //4) Check the multiple ‘Names’ in the ArrayList (Roseville, Burnsville, Maplewood)
    @Test
    public void test004()

    {
        //data[2,3,4].name
        response.body("data[2,3,4].categories[0]",hasKey("name"));
    }
    //5) Verify the storied=7 inside store services of the third store of second services
    @Test
    public void test005()
    {
        //data[2].services[1].storeservices.serviceId
        response.body("data[2].services[1].storeservices",hasKey("serviceId"));
    }
    //6) Check hash map values ‘createdAt’ inside store services map where store name = Roseville
    @Test
    public void test006()
    {
        List<HashMap<String,?>> storeServices=response.extract().path("data.findAll{it.name='Roseville'}.services");
    }
    //7) Verify the state = MN of forth store
    @Test
    public void testFourthStateIsMN()
    {//data[3].state
        response.body("data[3]",hasKey("state"));
    }
    //8) Verify the store name = Rochester of 9th store
    @Test
    public void test008()
    {
        //data[8].name
        response.body("data[8]",hasKey(""));
    }
    //9) Verify the storeId = 11 for the 6th store
    @Test
    public void test009()
    {
        //data[8].services[6]
        List<HashMap<String, ?>> storeId=response.extract().path("data.findAll{it.storeId=='6'}.storeId");
    }
    //10) Verify the serviceId = 4 for the 7th store of forth service
    @Test
    public void test010()
    {
        List<HashMap<String, ?>> serviceId=response.extract().path("data.findAll{it.serviceId=='7'}.storeId");
    }
}
