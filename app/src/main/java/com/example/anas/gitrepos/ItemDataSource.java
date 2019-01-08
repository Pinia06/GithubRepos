package com.example.anas.gitrepos;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer,Item> {

    private static final int FIRST_PAGE = 1;
    private final String DEBUG_TAG = "ItemDataSourceTAG";
    private final String sort = "stars";

    private String query = "created:>";
    private String order;


    public ItemDataSource() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-30);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        String startingDate = mFormat.format(calendar.getTime());
        this.query += startingDate;
        Log.d(DEBUG_TAG,query);

        Log.d(DEBUG_TAG,MainActivity.orderParam);
        this.order = MainActivity.orderParam;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RetrofitClient.getmInstance()
                .getGithubApiInterface()
                .getRepos(query,sort,order,FIRST_PAGE)
                .enqueue(new Callback<GithubApiResponse>() {
                    @Override
                    public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                        Log.d(DEBUG_TAG,"LoadInitial - ONRESPONSE");

                        if(response.isSuccessful()) {
                            Log.d(DEBUG_TAG, "LoadInitial - RESPONSE RECEIVED");
                            callback.onResult(response.body().getItems(),null,FIRST_PAGE + 1);
                        }

                        else Log.d(DEBUG_TAG,"LoadInitial - NULL BODY");

                    }

                    @Override
                    public void onFailure(Call<GithubApiResponse> call, Throwable t) {
                        Log.d(DEBUG_TAG,"LoadInitial - ONFAILURE");
                        Log.d(DEBUG_TAG,t.getMessage());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Item> callback) {

    }

    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {

        Log.d(DEBUG_TAG, "Loading " + params.key + " Count " + params.requestedLoadSize);

        RetrofitClient.getmInstance()
                .getGithubApiInterface()
                .getRepos(query,sort,order,params.key)
                .enqueue(new Callback<GithubApiResponse>() {
                    @Override
                    public void onResponse(Call<GithubApiResponse> call, Response<GithubApiResponse> response) {
                        Log.d(DEBUG_TAG,"LoadAfter - ONRESPONSE");

                        Integer key = response.body().getTotal_count() != 0 ? params.key + 1 : null;

                        if(response.isSuccessful()) {
                            Log.d(DEBUG_TAG, "LoadAfter - RESPONSE RECEIVED");
                            callback.onResult(response.body().getItems(),key);
                        }
                    }

                    @Override
                    public void onFailure(Call<GithubApiResponse> call, Throwable t) {
                        Log.d(DEBUG_TAG,"LoadAfter - ONFAILURE");
                        Log.d(DEBUG_TAG,t.getMessage());
                    }
                });
    }
}
