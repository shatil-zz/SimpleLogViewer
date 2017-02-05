package com.shieldpeoples.simplelogviewer.model.log;

import com.shieldpeoples.simplelogviewer.R;
import com.shieldpeoples.simplelogviewer.utils.log.LogViewerUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shahanur on 1/25/2017.
 */

public class LogViewerModel {

    String tagName, senderName, logDescription;
    long senderId, systemTime;
    int status;

    public LogViewerModel(JSONObject logObject, String senderName, long senderId) {
        try {
            tagName = (logObject.has(LogViewerUtils.KEY_TAG)) ? logObject.getString(LogViewerUtils.KEY_TAG) : "";
            this.senderName = senderName;
            logDescription = (logObject.has(LogViewerUtils.KEY_DESCRIPTION)) ? logObject.getString(LogViewerUtils.KEY_DESCRIPTION) : "";
            status = (logObject.has(LogViewerUtils.KEY_STATUS)) ? logObject.getInt(LogViewerUtils.KEY_STATUS) : LogViewerUtils.STATUS_DEFAULT_LOG;
            this.senderId = senderId;
            systemTime = (logObject.has(LogViewerUtils.KEY_SYSTEM_TIME)) ? logObject.getLong(LogViewerUtils.KEY_SYSTEM_TIME) : 0;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getTagName() {
        return tagName;
    }

    public String getDescription() {
        return logDescription;
    }

    public String getSenderName() {
        return senderName;
    }

    public long getSenderId() {
        return senderId;
    }

    public long getTime() {
        return systemTime;
    }

    public String getReadableStatus() {
        switch (status) {
            case LogViewerUtils.STATUS_FAILURE_LOG:
                return "<font color='#AA0000'>failed</font>";
            case LogViewerUtils.STATUS_SUCCESS_LOG:
                return "<font color='#00AA00'>success</font>";
            default:
                return "<font color='#0000AA'>debug</font>";
        }
    }

    public int getColorOfStatus() {
        switch (status) {
            case LogViewerUtils.STATUS_FAILURE_LOG:
                return R.color.color_failed;
            case LogViewerUtils.STATUS_SUCCESS_LOG:
                return R.color.color_successful;
            default:
                return R.color.color_neutral;
        }
    }

}
