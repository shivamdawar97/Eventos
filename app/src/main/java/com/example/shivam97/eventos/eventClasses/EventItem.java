package com.example.shivam97.eventos.eventClasses;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity
public class EventItem {

    @PrimaryKey
    @NonNull
    @ColumnInfo
    private int eventId;

    @ColumnInfo private String jsonObject;

    @NonNull
    public int getEventId() {
        return eventId;
    }

    public void setEventId(@NonNull int eventId) {
        this.eventId = eventId;
    }

    public String getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(String jsonObject) {
        this.jsonObject = jsonObject;
    }
}
