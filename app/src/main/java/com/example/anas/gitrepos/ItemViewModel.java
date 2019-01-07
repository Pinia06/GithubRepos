package com.example.anas.gitrepos;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

public class ItemViewModel extends ViewModel {

    private final int PAGE_SIZE = 30;

    private LiveData<PagedList<Item>> itemPagedList;

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();

        itemPagedList = new LivePagedListBuilder(itemDataSourceFactory,config).build();
    }

    public LiveData<PagedList<Item>> getItemPagedList(){ return itemPagedList;}

}
