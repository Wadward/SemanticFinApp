package com.wycstock.semanticfin.view.news_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wycstock.semanticfin.R;
import com.wycstock.semanticfin.view.base.BaseViewHolder;

public class NewsViewHolder extends BaseViewHolder
{
    public View cover;
    public ImageView image;
    public TextView likeCount;
    public TextView bucketCount;
    public TextView viewCount;

    public NewsViewHolder(View itemView)
    {
        super(itemView);
        cover = itemView.findViewById(R.id.news_clickable_cover);
        image = itemView.findViewById(R.id.news_image);
        likeCount = itemView.findViewById(R.id.news_like_count);
        bucketCount = itemView.findViewById(R.id.news_bucket_count);
        viewCount = itemView.findViewById(R.id.news_view_count);
    }
}
