package com.shieldpeoples.simplelogviewer.activity.log;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.shieldpeoples.simplelogviewer.R;
import com.shieldpeoples.simplelogviewer.adapter.LogViewerAdapter;
import com.shieldpeoples.simplelogviewer.model.log.LogViewerModel;
import com.shieldpeoples.simplelogviewer.mvp.log.LogViewer;
import com.shieldpeoples.simplelogviewer.presenter.log.LogViewerPresenterImp;
import com.shieldpeoples.simplelogviewer.utils.log.LogViewerUtils;
import com.shieldpeoples.simplelogviewer.utils.ui.CustomLayoutManager;

import java.util.ArrayList;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Shahanur on 1/25/2017.
 */

public class LogViewerActivity extends AppCompatActivity implements LogViewer.view {
    @Bind(R.id.rv_logs)
    RecyclerView rvShowLogs;
    @Bind(R.id.sr_refresh)
    SwipeRefreshLayout srRefresh;
    Context context;
    LogViewerAdapter adapter;
    LogViewer.presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_viewer);
        ButterKnife.bind(this);
        this.context = getApplicationContext();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Log Viewer");
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        adapter = new LogViewerAdapter(context, new ArrayList<LogViewerModel>());
        CustomLayoutManager llm = new CustomLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        llm.setExtraLayoutSpace();
        rvShowLogs.setLayoutManager(llm);
        rvShowLogs.setAdapter(adapter);
        presenter = new LogViewerPresenterImp(context, this);
        presenter.loadData(null, null);
        srRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                int random = new Random().nextInt(20);
                LogViewerUtils.addNewLog(context, "Status code" + random, "description....." + random, random % 3);
                presenter.loadData(null, null);
            }
        });
    }

    @Override
    public void resetView(ArrayList<LogViewerModel> dataItems) {
        if (adapter != null) {
            adapter.reset(dataItems);
        }
    }

    @Override
    public void addToView(ArrayList<LogViewerModel> dataItems) {
        if (adapter != null) {
            Log.d("LogListLoaderTag", "adding views");
            adapter.addAll(dataItems);
        }
    }

    @Override
    public void loadStarted() {

        Log.d("LogListLoaderTag", "Load started");
        if (srRefresh != null && !srRefresh.isRefreshing()) {
            srRefresh.post(new Runnable() {
                @Override
                public void run() {

                    Log.d("LogListLoaderTag", "Force swipe");
                    //srRefresh.setRefreshing(true);
                }
            });
        }
    }

    @Override
    public void loadFinish(boolean isSuccess) {
        if (srRefresh != null && srRefresh.isRefreshing()) {
            Log.d("LogListLoaderTag", "Load finish");
            srRefresh.setRefreshing(false);
        }
    }
}