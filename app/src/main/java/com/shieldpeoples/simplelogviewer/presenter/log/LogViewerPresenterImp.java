package com.shieldpeoples.simplelogviewer.presenter.log;

import android.content.Context;
import android.util.Log;

import com.shieldpeoples.simplelogviewer.Interactor.LogListLoader;
import com.shieldpeoples.simplelogviewer.model.log.LogViewerModel;
import com.shieldpeoples.simplelogviewer.mvp.log.LogViewer;

import java.util.ArrayList;

/**
 * Created by Shahanur on 1/25/2017.
 */

public class LogViewerPresenterImp implements LogViewer.model, LogViewer.presenter {
    Context context;
    LogViewer.view view;
    LogListLoader loader;

    public LogViewerPresenterImp(Context context, LogViewer.view view) {
        this.view = view;
        this.context = context;
    }

    @Override
    public void dataLoadUpdate(ArrayList<LogViewerModel> dataItems) {
        if (view != null) {

            Log.d("LogListLoaderTag", "Item in presenter" + dataItems.size());
            view.addToView(dataItems);
        }
    }

    @Override
    public void dataLoadComplete(boolean isSuccess) {
        if (view != null) {

            view.loadFinish(isSuccess);
        }
    }

    @Override
    public void loadData(String searchText, String filterBy) {
        view.loadStarted();
        view.resetView(new ArrayList<LogViewerModel>());
        loader = new LogListLoader(context, this);
        loader.execute();
    }

    @Override
    public void stopOperation() {
        if (loader != null) {
            loader.stopOperation();
        }
    }
}
