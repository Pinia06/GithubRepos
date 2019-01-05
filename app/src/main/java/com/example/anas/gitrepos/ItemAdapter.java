package com.example.anas.gitrepos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private final String DEBUG_TAG = "ItemAdapterTAG";

    private List<Item> repoList;
    private Item currentItem;
    private Context mContext;

    public ItemAdapter(Context mContext,List<Item> repoList) {
        this.repoList = repoList;
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

        currentItem = repoList.get(i);

        itemViewHolder.repoNameTextView.setText(currentItem.getName());
        itemViewHolder.repoDescriptionTextView.setText(currentItem.getDescription());
        Glide.with(mContext)
                .load(currentItem.getOwner().getAvatar_url())
                .apply(new RequestOptions().override(20,20))
                .into(itemViewHolder.repoOwnerAvatar);
        itemViewHolder.repoOwnerUsername.setText(currentItem.getOwner().getLogin());
        itemViewHolder.repoStarsTextView.setText(currentItem.getStargazers_count());
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        public TextView repoNameTextView;
        public TextView repoDescriptionTextView;
        public TextView repoStarsTextView;
        public TextView repoOwnerUsername;
        public ImageView repoOwnerAvatar;

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
