package utils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class APIConstants {

    //Base URI for the API
    public static final String BASE_URI="http://hrm.syntaxtechs.net/syntaxapi/api";

    //Endpoints
    public static final String CREATE_EMPLOYEE = "/createUser.php";
    //Headers
    public static final String HEADER_CONTENT_TYPE_KEY = "Content/Type";
    public static final String HEADER_CONTENT_TYPE_VALUE ="application/json";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    //The path to config.properties
    public static final String CONFIG_FILE_PATH="src/resources/config/config.properties";

    //Request and Response objects
    public static RequestSpecification request;

    public static Response response;
}
