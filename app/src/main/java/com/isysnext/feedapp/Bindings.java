package com.isysnext.feedapp;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.isysnext.feedapp.utility.Utils;
import com.twitter.sdk.android.core.models.Tweet;
import java.net.URLDecoder;

/**
 * Created by Anuved on 29/08/18.
 */
public class Bindings {
    @BindingAdapter("bind:image")
    public static void bindImage(ImageView imageView, final String url) {
        Glide.with(imageView.getContext())
                .load(url)
                .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Utils.showFullImage(view.getContext(), url);
            }
        });
    }

    @BindingAdapter("bind:tweetImage")
    public static void bindTweetImage(ImageView imageView, Tweet tweet) {

        if (!tweet.entities.media.isEmpty())
            Glide.with(imageView.getContext())
                    .load(URLDecoder.decode(tweet.entities.media.get(0).mediaUrl))
                    .apply(new RequestOptions().placeholder(R.drawable.ic_launcher_background)
                            .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .into(imageView);
        else {
            imageView.setVisibility(View.GONE);
        }
    }
}
