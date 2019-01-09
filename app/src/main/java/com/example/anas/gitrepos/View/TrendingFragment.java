package com.example.anas.gitrepos.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
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

import com.example.anas.gitrepos.Adapter.ItemAdapter;
import com.example.anas.gitrepos.Model.Item;
import com.example.anas.gitrepos.R;
import com.example.anas.gitrepos.ViewModel.ItemViewModel;

//This Fragment's role is to display the repositories

public class TrendingFragment extends Fragment {

    private final String DEBUG_TAG = "TrendingFragmentTAG";

    private RecyclerView recyclerView;
    private ItemViewModel itemViewModel;
    private ItemAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trending,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rv_trending);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(DEBUG_TAG,"TRENDING - ONACTIVITYCREATED");

        configureRecyclerView();

        //We create the ItemViewModel and pass the current fragment context
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //This allows us to observe any LiveData change and approve it by the submitList method
        itemViewModel.getItemPagedList().observe(this, new Observer<PagedList<Item>>() {

            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                Log.d(DEBUG_TAG,"ONCHANGED");
                adapter.submitList(items);
            }
        });

    }

    private void configureRecyclerView() {

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        adapter = new ItemAdapter(getContext());
        recyclerView.setAdapter(adapter);
    }

}
