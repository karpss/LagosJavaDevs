package com.example.ola.lagosjavadevs;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by 246 on 3/15/2017.
 */

public interface MyGithubInterface {

    @GET("/search/users")
    Call<Dev> getDevsInLagos(@Query("q") String developers );




}
