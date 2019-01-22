package com.example.shivam97.eventos.eventClasses;

import android.app.Application;

import com.example.shivam97.eventos.MyRoomDatabase;

import static com.example.shivam97.eventos.Eventos.database;

public class EventsRepository {


    private EventDao eventDao;


    public EventsRepository() {
        eventDao=database.eventDao();
        makeHttpRequest();
    }

    private void makeHttpRequest() {
        //Make http volley request here


    }


}
