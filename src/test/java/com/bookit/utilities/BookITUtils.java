package com.bookit.utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookITUtils {


    public static  String generateToken(String email, String password){


        Response response = given().accept(ContentType.JSON)
                .queryParams("email", email)
                .queryParams("password", password)
                .when()
                .get(ConfigurationReader.get("apiUrl") + "/sign");

        String token = response.path("accessToken");

        String finalToken = "Bearer " + token;
        return finalToken;

    }

    public static String[] getMYInfo(String email, String password){
        String[] myInfo = new String[3];

        for (int i = 0; i < 3; i++) {
            if (i==0){
                Response response = given().accept(ContentType.JSON)
                        .and().header("Authorization", generateToken(email, password))
                        .when()
                        .get(ConfigurationReader.get("apiUrl")+"/api/teams/my");

                JsonPath jsonPath = response.jsonPath();
                myInfo[i]= jsonPath.getString("name");
            }
            if(i==1){
                //get team request
                Response response = given().accept(ContentType.JSON)
                        .and().header("Authorization", generateToken(email, password))
                        .when()
                        .get(ConfigurationReader.get("apiUrl")+"/api/batches/my");

                JsonPath jsonPath = response.jsonPath();
                myInfo[i]= jsonPath.getString("number");
            }
            if(i==2){
                //get campus request
                Response response = given().accept(ContentType.JSON)
                        .and().header("Authorization", generateToken(email, password))
                        .when()
                        .get(ConfigurationReader.get("apiUrl")+"/api/campuses/my");

                JsonPath jsonPath = response.jsonPath();
                myInfo[i]= jsonPath.getString("location");
            }
        }
        return myInfo;
    }
}
