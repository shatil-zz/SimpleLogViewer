package com.shieldpeoples.simplelogviewer.utils.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Shahanur on 1/25/2017.
 * You should set senderName, encryptionKey and senderId before you addNewLog
 * Please make sure you don't set any preferences named cUsTom_PrEf_NaMe_834
 */

public class LogViewerUtils {
    public final static int STATUS_DEFAULT_LOG = 0, STATUS_FAILURE_LOG = 1, STATUS_SUCCESS_LOG = 2;

    public final static String KEY_TAG = "tag_name", KEY_DESCRIPTION = "tag_description", KEY_ENCRYPTION_KEY = "encryption_key",
            KEY_SENDER_NAME = "sender_name", KEY_SENDER_ID = "sender_id", KEY_SYSTEM_TIME = "system_time",
            KEY_STATUS = "status", KEY_LOG_LIST = "log_list", KEY_PREFERENCE_NAME = "cUsTom_PrEf_NaMe_834";


    private static JSONObject getLogViewerObject(String tagName, String logDescription, int status) throws JSONException {
        JSONObject object = new JSONObject();
        object.put(KEY_TAG, tagName);
        object.put(KEY_DESCRIPTION, logDescription);
        object.put(KEY_STATUS, status);
        object.put(KEY_SYSTEM_TIME, System.currentTimeMillis());
        return object;
    }

    public static boolean addNewLog(Context context, String tagName, String logDescription, int status) {
        try {
            JSONArray logList = getLogList(context);
            logList.put(getLogViewerObject(tagName, logDescription, status));
            return updateLogList(context, logList);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static JSONArray getLogList(Context context) {
        try {
            return getLogInfo(context).getJSONArray(KEY_LOG_LIST);
        } catch (Exception e) {
            return new JSONArray();
        }
    }

    public static String getSenderName(Context context) {
        try {
            return getLogInfo(context).getString(KEY_SENDER_NAME);
        } catch (Exception e) {
            return "Anonymous";
        }
    }

    public static String getEncryptionKey(Context context) {
        try {
            return getLogInfo(context).getString(KEY_ENCRYPTION_KEY);
        } catch (Exception e) {
            return "0";
        }
    }

    public static long getSenderId(Context context) {
        try {
            return getLogInfo(context).getLong(KEY_SENDER_ID);
        } catch (Exception e) {
            return 0;
        }
    }

    public static boolean updateRequiredInfo(Context context, String senderName, long senderId, String encryptionKey) {
        try {
            JSONObject logInfo = getLogInfo(context);
            logInfo.put(KEY_SENDER_ID, senderId);
            logInfo.put(KEY_ENCRYPTION_KEY, encryptionKey);
            logInfo.put(KEY_SENDER_NAME, senderName);
            return updateLogInfo(context, logInfo);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean clearLogList(Context context) {
        try {
            return updateLogList(context, new JSONArray());
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean updateLogList(Context context, JSONArray logList) throws JSONException {
        return updateLogInfo(context, getLogInfo(context).put(KEY_LOG_LIST, logList));
    }

    private static JSONObject getLogInfo(Context context) throws JSONException {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new JSONObject(preferences.getString(KEY_PREFERENCE_NAME, new JSONObject().toString()));
    }

    private static boolean updateLogInfo(Context context, JSONObject logInfo) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_PREFERENCE_NAME, logInfo.toString());
        return editor.commit();
    }
}
