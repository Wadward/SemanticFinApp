package com.wycstock.semanticfin.view.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.os.AsyncTaskCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wycstock.semanticfin.R;
import com.wycstock.semanticfin.adapter.NewsListAdapter;
import com.wycstock.semanticfin.model.News;
import com.wycstock.semanticfin.view.base.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsFragment extends Fragment
{
    private final static int TOTAL_PAGE = 3;
    private final static int PAGE_DATA_COUNT = 20;
    private final static int LAST_PAGE_DATA_COUNT = 10;
    private View view;
    private RecyclerView recyclerView;
    private NewsListAdapter adapter;

    class SleepTask extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected Void doInBackground(Void... voids)
        {
            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(Void v)
        {
            List<News> moreData = fakeData(adapter.getDataCount());
            adapter.append(moreData);

            if(moreData.size() < PAGE_DATA_COUNT)
                adapter.setShowLoading(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.news_fragment,
                container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        recyclerView = (RecyclerView) getView().findViewById(R.id.news_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.spacing_medium)));

        adapter = new NewsListAdapter(fakeData(1), new NewsListAdapter.LoadMoreListener()
        {
            @Override
            public void onLoadMore()
            {
                // this method will be called when the RecyclerView is displayed
                // page starts from 1
                //AsyncTaskCompat.executeParallel(
                //        new LoadShotTask(adapter.getDataCount() / Dribbble.COUNT_PER_PAGE + 1));

                /*
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            Thread.sleep(2000);
                            getActivity().runOnUiThread(new Runnable()
                            {
                                @Override
                                public void run()
                                {
                                    adapter.append(fakeData());
                                }
                            });
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
                */

                new SleepTask().execute();
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private List<News> fakeData(int page)
    {
        int count = page < TOTAL_PAGE ? PAGE_DATA_COUNT : LAST_PAGE_DATA_COUNT;
        List<News> shotList = new ArrayList<>();
        Random random = new Random();

        for(int i = 0; i < count; ++i)
        {
            News news = new News();
            news.views_count = random.nextInt(10000);
            news.likes_count = random.nextInt(200);
            news.buckets_count = random.nextInt(50);
            shotList.add(news);
        }

        return shotList;
    }
}