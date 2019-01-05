package com.example.anas.gitrepos;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This Fragment's role is to display the repositories

public class TrendingFragment extends Fragment {

    private final String DEBUG_TAG = "TrendingFragmentTAG";
    private RecyclerView recyclerView;

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

                    if(response.isSuccessful()){
                        Log.d(DEBUG_TAG,"RESPONSE RECEIVED");
                            GithubApiResponse mResponse = response.body();
                            List<Item> repoList = new ArrayList<>();
                            for (Item item : mResponse.getItems()) {
                                repoList.add(item);
                            }

                            ItemAdapter itemAdapter = new ItemAdapter(getContext(), repoList);
                            recyclerView = getView().findViewById(R.id.rv_trending);
                            recyclerView.setAdapter(itemAdapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                    }

                    else Log.d(DEBUG_TAG,"REPONSE NOT RECEIVED");

            }

            @Override
            public void onFailure(Call<GithubApiResponse> call, Throwable t) {
                    Log.d(DEBUG_TAG,"ONFAILURE");
                    Log.d(DEBUG_TAG,t.getMessage());
            }
        });

    }
}
