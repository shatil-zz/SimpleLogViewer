package com.shieldpeoples.simplelogviewer.Interactor;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.shieldpeoples.simplelogviewer.model.log.LogViewerModel;
import com.shieldpeoples.simplelogviewer.mvp.log.LogViewer;
import com.shieldpeoples.simplelogviewer.utils.log.LogViewerUtils;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Shahanur on 1/25/2017.
 */

public class LogListLoader extends AsyncTask<String, ArrayList<LogViewerModel>, Boolean> {
    LogViewer.model listener;
    Context context;
    boolean stopOperation;

    public LogListLoader(Context context, LogViewer.model listener) {
        this.context = context;
        this.listener = listener;
        stopOperation = false;
    }

    private Map<Long, LogViewerModel> loadDataItems(String search, String filter) throws Exception {
        JSONArray dataArray = LogViewerUtils.getLogList(context);
        Log.d("LogListLoaderTag", dataArray.toString());
        Map<Long, LogViewerModel> dataItems = new TreeMap<>(Collections.reverseOrder());
        long length = dataArray.length();
        String senderName = LogViewerUtils.getSenderName(context);
        long senderId = LogViewerUtils.getSenderId(context);
        LogViewerModel model;
        for (int position = 0; position < length; position++) {
            if (!stopOperation) {
                model = new LogViewerModel(dataArray.getJSONObject(position), senderName, senderId);
                dataItems.put(model.getTime(), model);
            } else {
                return dataItems;
            }
        }
        Log.d("LogListLoaderTag", "Itemfound:" + dataItems.size());
        return dataItems;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            publishProgress(new ArrayList<LogViewerModel>(loadDataItems(null, null).values()));
            return true;
        } catch (Exception e) {

        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (listener != null) {
            listener.dataLoadComplete(aBoolean);
        }
    }

    @Override
    protected void onProgressUpdate(ArrayList<LogViewerModel>... values) {
        super.onProgressUpdate(values);
        if (listener != null) {
            Log.d("LogListLoaderTag", "Item loaded" + values[0].size());
            listener.dataLoadUpdate(values[0]);
        }
    }

    public void stopOperation() {
        stopOperation = true;
    }
}
