package com.example.anas.gitrepos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This Fragment's role is to display the repositories

public class TrendingFragment extends Fragment {

    private final String DEBUG_TAG = "TrendingFragmentTAG";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending,container,false);
    }

    @Override
    public void onResume() {
        super.onResume();

        RetrofitClient.getmInstance()
                .getGithubApiInterface()
                .getRepos("created:>2019-01-04","stars","desc",1)
                .enqueue(new Callback<GithubApiResponse>() {
            @Override
            public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                    Log.d(DEBUG_TAG,"ONRESPONSE");

                    if(response.isSuccessful()) Log.d(DEBUG_TAG,"WE GOOD");
                    else Log.d(DEBUG_TAG,"DAMMIT");

            }

            @Override
            public void onFailure(Call<GithubApiResponse> call, Throwable t) {
                    Log.d(DEBUG_TAG,"ONFAILURE");
                    Log.d(DEBUG_TAG,t.getMessage());
            }
        });

    }
}
