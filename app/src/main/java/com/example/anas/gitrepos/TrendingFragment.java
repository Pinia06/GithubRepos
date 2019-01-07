package com.example.anas.gitrepos;

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

//This Fragment's role is to display the repositories

public class TrendingFragment extends Fragment {

    private final String DEBUG_TAG = "TrendingFragmentTAG";

    private RecyclerView recyclerView;
    private ItemViewModel itemViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(DEBUG_TAG,"TRENDING - ONCREATEVIEW");

        View view = inflater.inflate(R.layout.fragment_trending,container,false);

        recyclerView = view.findViewById(R.id.rv_trending);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d(DEBUG_TAG,"TRENDING - ONACTIVITYCREATED");

        final ItemAdapter adapter = new ItemAdapter(getContext());
        recyclerView.setAdapter(adapter);

        itemViewModel = ViewModelProviders.of(getActivity()).get(ItemViewModel.class);

        itemViewModel.getItemPagedList().observe(getViewLifecycleOwner(), new Observer<PagedList<Item>>() {

            @Override
            public void onChanged(@Nullable PagedList<Item> items) {
                Log.d(DEBUG_TAG,"ONCHANGED");
                adapter.submitList(items);
            }
        });

    }

}
