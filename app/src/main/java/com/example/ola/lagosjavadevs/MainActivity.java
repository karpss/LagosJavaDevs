package com.example.ola.lagosjavadevs;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String GITHUB_URL = "https://api.github.com/";


    private RecyclerView recyclerView;
    private DevAdapter dAdapter;
    List<Dev.ItemComponents> Devs;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        OkHttpClient okClient = new OkHttpClient.Builder()
                .addInterceptor(
                        new Interceptor() {
                            @Override
                            public Response intercept(Interceptor.Chain chain) throws IOException {
                                Request request = chain.request().newBuilder()
                                        .addHeader("Accept", "Application/JSON").build();
                                return chain.proceed(request);
                            }
                        }).build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GITHUB_URL)
                .client(okClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MyGithubInterface service = retrofit.create(MyGithubInterface.class);

        Call<Dev> call = service.getDevsInLagos("location:lagos language:java");
        call.enqueue(new Callback <Dev>() {
            @Override
            public void onResponse(Call<Dev> call, retrofit2.Response<Dev> response) {
                //public void onResponse(Response<List<Student>> response, Retrofit retrofit) {

                // Toast.makeText(MainActivity.this, "Status Code = " + response.code(), Toast.LENGTH_LONG).show();

                if (response.isSuccessful()) {

                    Devs = new ArrayList<>();
                    Dev result = response.body();

                    // Toast.makeText(MainActivity.this, "response = " + new Gson().toJson(result), Toast.LENGTH_LONG).show();
                    Devs = result.getItems();



                    dAdapter = new DevAdapter(getApplicationContext(),Devs);


                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(dAdapter);
                }
            }

            @Override
            public void onFailure(Call<Dev> call, Throwable t) {

            }
        });


    }

}
