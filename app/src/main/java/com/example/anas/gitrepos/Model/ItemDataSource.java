package com.example.anas.gitrepos.Model;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.anas.gitrepos.MainActivity;
import com.example.anas.gitrepos.Network.RetrofitClient;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//This Class is the source of our data, it is responsible of requesting data and returning content to the PageList
//It extends PageKeyedDataSource because we our requesting data based on a page key in the URL

public class ItemDataSource extends PageKeyedDataSource<Integer,Item> {

    private static final int FIRST_PAGE = 1;
    private final String DEBUG_TAG = "ItemDataSourceTAG";
    private final String sort = "stars";
    private String query = "created:>";
    private String order;


    public ItemDataSource() {
        getCurrentDate();
        query += getCurrentDate();
        Log.d(DEBUG_TAG,query);

        Log.d(DEBUG_TAG,MainActivity.getOrderParam());
        this.order = MainActivity.getOrderParam();

    }

    //This method gets the current date of the device and returns the date 30 days before
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,-30);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
        return mFormat.format(calendar.getTime());
    }


    //This method will be called the first time the API is called
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

                        else{
                            Log.d(DEBUG_TAG,"LoadInitial - NULL BODY");
                        }

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

                        //We check getTotal_count attribute if is equal to 0, if it is the case, we have reached the last page of the API
                        //else we increment our key to get next page data
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
