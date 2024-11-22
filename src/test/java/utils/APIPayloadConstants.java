package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

    public static String generateTokenPayload(String email, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }


}