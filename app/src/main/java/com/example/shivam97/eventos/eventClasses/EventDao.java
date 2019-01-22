package com.example.shivam97.eventos.eventClasses;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EventDao {

    @Insert
    void insertEventData(EventItem event);

    @Query("select * from EventItem")
    LiveData<List<EventItem>> getAllEvents();

}
