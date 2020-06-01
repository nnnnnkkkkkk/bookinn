package com.example.appocalypse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface bookApi {
    @GET("search/index.xml?key=m8U2NWbpqELmrnV1v9JkTA&")
    Call<apiResponse> getBookInfo(/*@Query("key") String key,*/@Query("q") String bookTitle);
}
