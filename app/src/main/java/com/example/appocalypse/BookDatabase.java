package com.example.appocalypse;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = Books.class, version = 1)
public abstract class BookDatabase extends RoomDatabase {
    public abstract bookDao getBookDao();
}
