package utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class APIConstants {

    public static final String BaseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";

    public static final String GENERATE_TOKEN = BaseURI + "/generateToken.php";
    public static final String CREATE_EMPLOYEE = BaseURI + "/createUser.php";

    public static final String HEADER_CONTENT_TYPE_KEY = "Content-Type";
    public static final String HEADER_CONTENT_TYPE_VALUE = "application/json";

    public static RequestSpecification request;
    public static Response response;
    public static String token;
}