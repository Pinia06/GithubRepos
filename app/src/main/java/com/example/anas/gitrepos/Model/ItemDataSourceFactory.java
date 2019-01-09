package com.example.anas.gitrepos.Model;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

//This Class is the constructor of our ItemDataSource object, and does return a LiveData to the ViewModel
public class ItemDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer,Item>> itemLiveData = new MutableLiveData<>();

    @Override
    public DataSource create() {
        ItemDataSource itemDataSource = new ItemDataSource();
        itemLiveData.postValue(itemDataSource);
        return itemDataSource;
    }


    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveData() {
        return itemLiveData;
    }

}
