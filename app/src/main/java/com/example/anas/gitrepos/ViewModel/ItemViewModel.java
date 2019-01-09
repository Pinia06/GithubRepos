package com.example.anas.gitrepos.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.example.anas.gitrepos.Model.Item;
import com.example.anas.gitrepos.Model.ItemDataSourceFactory;

//This Class will allow us to observe any LiveData change from our TrendingFragment
public class ItemViewModel extends ViewModel {

    private LiveData<PagedList<Item>> itemPagedList;

    public ItemViewModel(){
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        //Since the Github API return 30 Items per Page, we can fix the loading size to 30
        int PAGE_SIZE = 30;
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(PAGE_SIZE)
                .build();

        //The LivePagedListBuilder allows us to get live data object of type PagedList
        itemPagedList = new LivePagedListBuilder(itemDataSourceFactory,config).build();
    }


    public LiveData<PagedList<Item>> getItemPagedList(){ return itemPagedList;}

}
