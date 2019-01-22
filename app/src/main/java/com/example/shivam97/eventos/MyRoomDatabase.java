package com.example.shivam97.eventos;


import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.shivam97.eventos.eventClasses.EventDao;
import com.example.shivam97.eventos.eventClasses.EventItem;


@Database(entities = {EventItem.class},version = 1,exportSchema = false)

public abstract class MyRoomDatabase  extends RoomDatabase {
    private static volatile MyRoomDatabase INSTANCE;

    public abstract EventDao eventDao();

    public static MyRoomDatabase getDatabase(final Context context){
        if(INSTANCE==null){
            synchronized (MyRoomDatabase.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            MyRoomDatabase.class,"word_database")
                            .build();

                }            }
        }
        return INSTANCE;
    }


}
