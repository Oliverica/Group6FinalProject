package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIConstants {




    public static  RequestSpecification request;

     public static Response response;

    public static final String BaseURI=RestAssured.baseURI="http://hrm.syntaxtechs.net/syntaxapi/api";

    public static final String CREATE_EMPLOYEE=BaseURI+"/createUser.php";

}
