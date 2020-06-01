package com.example.appocalypse;

import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class repository {
    private Executor executor = Executors.newSingleThreadExecutor();
    private BookDatabase database = App.getBookDatabase();
    private com.example.appocalypse.bookDao bookDao = database.getBookDao();
    private final static String TAG = "Database operation";

    private Books qBook;
    private String qStatus;
    private int qId;
    private int currId;
    private String sDate;
    private String eDate;

    public void putData(Books lbook) {
        this.qBook = lbook;
        qBook.setStatus("Planning");
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.insertBook(qBook);
            }
        });
    }

    public void updateStart(String date, int id) {
        Log.d(TAG, "updateStart: "+date);
        this.sDate = date;
        this.qId = id;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.updateStartDate(sDate, qId);
            }
        });
    }

    public void updateEnd(String date, int id) {
        Log.d(TAG, "updateEnd: "+date);
        this.eDate = date;
        this.qId = id;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.updateEndDate(eDate, qId);
            }
        });
    }

    public void updateStatus(String status, int id) {
        this.qStatus = status;
        this.qId = id;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.updateStatus(qStatus, qId);
            }
        });
    }

    public void updateCurrent(int id) {
        this.qId = id;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.updateCurrent("Planning", "Reading");
                bookDao.updateStatus("Reading", qId);
            }
        });
    }

    public LiveData<Books> getCurrentBook() {
        return bookDao.getCurrentBook("Reading");
    }

    public LiveData<List<Books>> getPlannedBooks() {
        return bookDao.getBooksWithStatus("Planning");
    }

    public LiveData<List<Books>> getCompletedBooks() {
        return bookDao.getBooksWithStatus("Completed");
    }

    public void deleteBook(Books book) {
        this.qBook = book;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                bookDao.deleteBook(qBook);
            }
        });
    }

    public LiveData<Books> getBookById(int id) {
        return bookDao.getBooksWithId(id);
    }

    public boolean isReading() { return !(bookDao.getBooksWithStatus("Reading").getValue() == null); }

}