package com.shieldpeoples.simplelogviewer.utils.network;

import org.json.JSONObject;

/**
 * Created by Shahanur on 1/13/2017.
 */

public class ResponseParser {
    public static String KEY_MESSAGE = "message", RESPONSE_CODE = "status_code";

    public static boolean isResponseOk(JSONObject response) {
        return true;
    }

    public static String getMessage(JSONObject response) {
        try {
            return (response.has(KEY_MESSAGE)) ? response.getString(KEY_MESSAGE) : "";
        } catch (Exception e) {
            return "";
        }
    }
}