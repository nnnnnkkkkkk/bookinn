package com.example.appocalypse;

import android.app.Application;
import android.util.Log;

import androidx.room.Room;

import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class App extends Application {
    private static com.example.appocalypse.bookApi bookApi;
    private static BookDatabase bookDatabase;
    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.goodreads.com/")
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();
        bookApi = retrofit.create(bookApi.class);
        Log.d("MYCALL", "api: "+bookApi);

        bookDatabase = Room.databaseBuilder(getApplicationContext(), BookDatabase.class,
                "book_database")
                .build();
    }

    public static BookDatabase getBookDatabase() {
        return bookDatabase;
    }

    public static bookApi getBookApi() { return bookApi; }
}
