package utils;

import org.json.JSONObject;

public class APIPayloadConstants {

<<<<<<< HEAD
    public static String createUser(String name,
                                    String email,
                                    String password){
        if (email.equalsIgnoreCase("dynamic")) {
            email = "user" + System.currentTimeMillis() + "@Anything.com";
        }


        JSONObject jsonObject=new JSONObject();
        jsonObject.put("name",name);
        jsonObject.put("email",email);
        jsonObject.put("password",password.isEmpty()? null: password);




        return jsonObject.toString();
    }


}
=======
    public static String generateTokenPayload(String email, String password) {
        JSONObject obj = new JSONObject();
        obj.put("email", email);
        obj.put("password", password);
        return obj.toString();
    }


}
>>>>>>> Marina
