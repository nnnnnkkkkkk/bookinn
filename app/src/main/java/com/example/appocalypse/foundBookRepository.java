package com.example.appocalypse;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Response;


public class foundBookRepository {
    private MutableLiveData<List<Work>> liveData = new MutableLiveData<>();
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<List<Work>> getData() {
        return liveData;
    }

    public List<Work> getResponse(String query) throws IOException {
        Log.d("MYCALL", "for "+query);
        apiResponse myResponse = new apiResponse();
        bookApi myApi = App.getBookApi();
        Call<apiResponse> call = myApi.getBookInfo(query);
        Log.d("MYCALL", call.request().url().toString());
        Response response = call.execute();
        myResponse = (apiResponse) response.body();
        return myResponse.getResult().getWork();
    }

    public LiveData<List<Work>> getFoundBooks(final String query) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    liveData.postValue(getResponse(query));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        Log.d("LIVEDATA", ""+(liveData == null));
        return getData();
    }

}