package com.shieldpeoples.simplelogviewer.mvp.log;

import com.shieldpeoples.simplelogviewer.model.log.LogViewerModel;

import java.util.ArrayList;

/**
 * Created by Shahanur on 2/1/2017.
 */

public interface LogViewer {
    public interface model {
        public void dataLoadUpdate(ArrayList<LogViewerModel> dataItems);

        public void dataLoadComplete(boolean isSuccess);
    }

    public interface view {
        public void resetView(ArrayList<LogViewerModel> dataItems);

        public void addToView(ArrayList<LogViewerModel> dataItems);

        public void loadStarted();

        public void loadFinish(boolean isSuccess);
    }

    public interface presenter {
        public void loadData(String searchText, String filterBy);

        public void stopOperation();
    }
}
