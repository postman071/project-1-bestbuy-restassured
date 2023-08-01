package com.bestbuy.demo.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest
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
    //1) Extract the limit
    @Test
    public void test001()
    {
        int limit=response.extract().path("limit");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of limit is : " +limit);
        System.out.println("------------------End of Test---------------------------");
    }
    //2) Extract the total
    @Test
    public void test002()
    {
        int total=response.extract().path("total");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of total is : " +total);
        System.out.println("------------------End of Test---------------------------");
    }
    //3) Extract the name of 5th store
    @Test
    public void test003()
    {
        String nameOfFifthStore=response.extract().path("data[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name Of Fifth Store is : " +nameOfFifthStore);
        System.out.println("------------------End of Test---------------------------");
    }
    //4)Extract the names of all the store
    @Test
    public void test004()
    {
        //data[*].name
        List<String> storeNames = response.extract().path("data.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name Of All The Store is : " +storeNames);
        System.out.println("------------------End of Test---------------------------");
    }
    //5) Extract the storeId of all the store
    @Test
    public void test005()
    {
        //data[*].id
        List<String> storeIds = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Name Of All storeIds are  : " +storeIds);
        System.out.println("------------------End of Test---------------------------");

    }
    //6) Print the size of the data list
    @Test
    public void test006()
    {
        int dataSize=response.extract().path("data.size");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Size of the DataList : " +dataSize);
        System.out.println("------------------End of Test---------------------------");
    }
    //7) Get all the value of the store where store name = St Cloud
    @Test
    public void test007()
    {
        List<HashMap<String, ?>>storeName=response.extract().path("data.findAll{it.name=='St Cloud'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store data for Store name is: " +storeName);
        System.out.println("------------------End of Test---------------------------");
    }
    //8) Get the address of the store where store name = Rochester
    @Test
    public void test008()
    {
        List<String> address=response.extract().path("data.findAll{it.name == 'Rochester'}");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store name is: " +address);
        System.out.println("------------------End of Test---------------------------");
    }
    //9) Get all the services of 8th store
    @Test
    public void test009()
    {
        List<HashMap<String, ?>> services =response.extract().path("data[7].services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Services of 8th store: " +services);
        System.out.println("------------------End of Test---------------------------");
    }
    //10) Get storeServices of the store where service name = Windows Store
    @Test
    public void test010()
    {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.services == 'Windows Store'}.storeservices");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Store services for service name is: " +services);
        System.out.println("------------------End of Test---------------------------");
    }
    //11) Get all the storeId of all the store
    @Test
    public void test011()
    {
        List<Integer> allStoreIds = response.extract().path("data.services.storeservices.storeId");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeId of all the store are : " + allStoreIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //12) Get id of all the store
    @Test
    public void test012()
    {
        //    data[*].name
        List<Integer> ids = response.extract().path("data.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The id of all the store are : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test013()
    {
        List<String> storeName = response.extract().path("data.findAll{it.state == 'ND'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The store names Where state 'ND' are : " + storeName);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test014()
    {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name == 'Rochester'}.services");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The Total number of services for the store where store name 'Rochester' are : " + services);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test015()
    {
        List<?> createdAtName = response.extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices.createdAt");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The createdAt for all services whose name 'Windows Store' are : " + createdAtName);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test016()
    {
        List<HashMap<String, ?>> services = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The name of all services Where store name 'Fargo' are : " + services);
        System.out.println("------------------End of Test---------------------------");

    }
    @Test
    public void test017()
    {
        List<Integer> zip = response.extract().path("data.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of all the store is : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test018()
    {
        List<Integer> zip = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The zip of store name 'Roseville' is : " + zip);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test019()
    {
        List<String>service =response.extract().path("data.findAll{it.name =='Magnolia Home Theater'}.service");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The storeservices details of the service name 'Magnolia Home Theater' is : "+service);
        System.out.println("------------------End of Test---------------------------");
    }
    @Test
    public void test020()
    {
        List<Long> lat = response.extract().path("data.lat");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The lat of all the stores are : " + lat);
        System.out.println("------------------End of Test---------------------------");
    }

}
