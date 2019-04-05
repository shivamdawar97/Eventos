package com.example.shivam97.eventos.addeventsclasses;

import org.json.JSONObject;

public class OrganiserModel {
    private String organiserObject,name,imageUrl,OrganiserType;//faculty or student
    private int organiseId;

    public OrganiserModel(JSONObject organiserJson) {
        //parse organiserJson
    }

    public int getOrganiseId() {
        return organiseId;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getOrganiserType() {
        return OrganiserType;
    }
}
