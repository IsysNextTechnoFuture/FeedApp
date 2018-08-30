package com.isysnext.feedapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.isysnext.feedapp.R;
import com.isysnext.feedapp.utility.Utils;
import com.twitter.sdk.android.core.models.Tweet;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Anuved on 28/08/18.
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedsVH> {
    //Declaration of variable
    private ArrayList<Tweet> tweetList;
    private Context context;

    //Constructor
    public FeedsAdapter(Context context) {
        this.context = context;
        tweetList = new ArrayList<>();
    }

    @NonNull
    @Override
    public FeedsVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tweets, viewGroup, false);
        return new FeedsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FeedsVH feedsVH, int i) {

        feedsVH.text.setText(tweetList.get(i).text);
        if (tweetList.get(i).entities.media.isEmpty()) {
            feedsVH.image.setVisibility(View.GONE);
        } else {
            Glide.with(context)
                    .load(URLDecoder.decode(tweetList.get(feedsVH.getAdapterPosition()).entities.media.get(0).mediaUrl))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(feedsVH.image);

            feedsVH.image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Utils.showFullImage(view.getContext(), tweetList.get(feedsVH.getAdapterPosition()).entities.media.get(0).mediaUrl);
                }
            });
        }


    }
    //Method for adding tweet list
    public void addTweetList(ArrayList<Tweet> tweets) {
        tweetList.addAll(tweets);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return tweetList.size();
    }

    //Class for holding view
    static class FeedsVH extends RecyclerView.ViewHolder {
        TextView text;
        ImageView image;

        public FeedsVH(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.text);
            image = itemView.findViewById(R.id.image);
        }
    }
}