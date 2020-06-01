package com.example.appocalypse;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface bookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertBook(Books book);

    @Query("select * from books")
    LiveData<List<Books>> getBooks();

    @Query("select * from books where books.status = :status")
    LiveData<Books> getCurrentBook(String status);

    @Query("select * from books where books.status = :status")
    LiveData<List<Books>> getBooksWithStatus(String status);

    @Query("select * from books where books.author = :author")
    LiveData<List<Books>> getBooksWithAuthor(String author);

    @Query("select * from books where books.id = :id")
    LiveData<Books> getBooksWithId(int id);

    @Query("update books set status = :status where books.id = :id")
    void updateStatus(String status, int id);

    @Query("update books set started = :start where books.id = :id")
    void updateStartDate(String start, int id);

    @Query("update books set ended = :end where books.id = :id")
    void updateEndDate(String end, int id);

    @Query("update books set status = :status where books.status = :status2")
    void updateCurrent(String status, String status2);

    @Delete
    void deleteBook(Books book);


}
