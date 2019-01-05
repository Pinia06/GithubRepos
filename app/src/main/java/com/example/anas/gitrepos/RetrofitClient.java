package com.example.anas.gitrepos;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final String BASE_URL = "https://api.github.com/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static synchronized RetrofitClient getmInstance(){
        if(mInstance == null) mInstance = new RetrofitClient();

        return mInstance;
    }

    public GithubApiInterface getGithubApiInterface(){
        return retrofit.create(GithubApiInterface.class);
    }


}
