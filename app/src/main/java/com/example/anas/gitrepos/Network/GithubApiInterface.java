package com.example.anas.gitrepos.Network;

import com.example.anas.gitrepos.Model.GithubApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GithubApiInterface {

    //This interface will allow us to get the path and parameters
    @GET("search/repositories")
    Call<GithubApiResponse> getRepos(
            @Query("q") String query,
            @Query("sort") String sort,
            @Query("order") String order,
            @Query("page") int page
    );

}
