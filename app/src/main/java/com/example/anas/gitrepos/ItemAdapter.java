package com.example.anas.gitrepos;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;


public class ItemAdapter extends PagedListAdapter<Item,ItemAdapter.ItemViewHolder> {

    private static DiffUtil.ItemCallback<Item> diffCallBack = new DiffUtil.ItemCallback<Item>() {
        @Override
        public boolean areItemsTheSame(@NonNull Item item, @NonNull Item t1) {
            return item.getId() == t1.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Item item, @NonNull Item t1) {
            return item.equals(t1);
        }
    };
    private final String DEBUG_TAG = "ItemAdapterTAG";
    private Context mContext;

    protected ItemAdapter(Context mContext) {
        super(diffCallBack);
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item,viewGroup,false);
        ItemViewHolder holder = new ItemViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {

        Item currentItem = getItem(i);

        if(currentItem != null) {
            Log.d(DEBUG_TAG,"ADAPTER - ITEM FOUND");
            itemViewHolder.repoNameTextView.setText(currentItem.getName());
            itemViewHolder.repoDescriptionTextView.setText(currentItem.getDescription());
            Glide.with(mContext)
                    .load(currentItem.getOwner().getAvatar_url())
                    .apply(new RequestOptions().override(20, 20))
                    .into(itemViewHolder.repoOwnerAvatar);
            itemViewHolder.repoOwnerUsername.setText(currentItem.getOwner().getLogin());
            itemViewHolder.repoStarsTextView.setText(currentItem.getStargazers_count());
        }

        else{
            Toast.makeText(mContext,"Item is null",Toast.LENGTH_SHORT).show();
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

         TextView repoNameTextView;
         TextView repoDescriptionTextView;
         TextView repoStarsTextView;
         TextView repoOwnerUsername;
         ImageView repoOwnerAvatar;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            repoNameTextView = itemView.findViewById(R.id.tv_repo_name);
            repoDescriptionTextView = itemView.findViewById(R.id.tv_repo_description);
            repoStarsTextView = itemView.findViewById(R.id.tv_star_numbers);
            repoOwnerUsername = itemView.findViewById(R.id.tv_username);
            repoOwnerAvatar = itemView.findViewById(R.id.iv_owner_avatar);
        }
    }


}
