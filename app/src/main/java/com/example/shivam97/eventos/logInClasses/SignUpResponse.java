package com.example.shivam97.eventos.logInClasses;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpResponse {

    private String status;
    private String userId;

    public SignUpResponse(String jsonResponse) throws JSONException {
        JSONObject object = new JSONObject(jsonResponse);
            status= object.getString("status");
            userId= object.getJSONObject("data").getString("id");
    }

    String getStatus(){
    return status;
    }

    String getUserId(){
        return userId;
    }


}
