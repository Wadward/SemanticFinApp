package com.wycstock.semanticfin.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wycstock.semanticfin.R;
import com.wycstock.semanticfin.model.News;
import com.wycstock.semanticfin.view.news_list.NewsViewHolder;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter
{
    private static final int VIEW_TYPE_NEWS = 1;
    private static final int VIEW_TYPE_LOADING = 2;
    private List<News> data;
    private LoadMoreListener loadMoreListener;
    private boolean showLoading;

    public NewsListAdapter(@NonNull List<News> data, @NonNull LoadMoreListener loadMoreListener)
    {
        this.data = data;
        this.loadMoreListener = loadMoreListener;
        this.showLoading = true;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        if(viewType == VIEW_TYPE_NEWS)
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_news, parent, false);
            return new NewsViewHolder(view);
        }
        else
        {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.list_item_loading, parent, false);
            return new RecyclerView.ViewHolder(view)
            {
            };
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position)
    {
        final int viewType = getItemViewType(position);

        if(viewType == VIEW_TYPE_LOADING)
        {
            loadMoreListener.onLoadMore();
        }
        else
        {
            final News news = data.get(position);
            NewsViewHolder newsViewHolder = (NewsViewHolder) holder;
            newsViewHolder.image.setImageResource(R.drawable.news_placeholder);
            newsViewHolder.likeCount.setText(String.valueOf(news.likes_count));
            newsViewHolder.bucketCount.setText(String.valueOf(news.buckets_count));
            newsViewHolder.viewCount.setText(String.valueOf(news.views_count));

            /*
            // play gif automatically
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(Uri.parse(shot.getImageUrl()))
                    .setAutoPlayAnimations(true)
                    .build();
            shotViewHolder.image.setController(controller);

            shotViewHolder.cover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = holder.itemView.getContext();
                    Intent intent = new Intent(context, ShotActivity.class);
                    intent.putExtra(ShotFragment.KEY_SHOT,
                            ModelUtils.toString(shot, new TypeToken<Shot>(){}));
                    intent.putExtra(ShotActivity.KEY_SHOT_TITLE, shot.title);
                    context.startActivity(intent);
                }
            });
            */
        }
    }

    @Override
    public int getItemCount()
    {
        return showLoading ? data.size() + 1 : data.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        return position < data.size()
                ? VIEW_TYPE_NEWS
                : VIEW_TYPE_LOADING;
    }

    public void append(@NonNull List<News> moreNews)
    {
        data.addAll(moreNews);
        notifyDataSetChanged();
    }

    public int getDataCount()
    {
        return data.size();
    }

    public void setShowLoading(boolean showLoading)
    {
        this.showLoading = showLoading;
        notifyDataSetChanged();
    }

    public interface LoadMoreListener
    {
        void onLoadMore();
    }
}