package com.iprismtech.delivery_boy.utils;

import org.json.JSONArray;

/**
 * Created by intel on 21-Nov-18.
 */

public class Variables {

    public static String profilePic64;

    public static JSONArray service_array_item_id = new JSONArray();
    public static String id = "";

    public void setID(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setJSONArray(JSONArray service_array_item_id) {
        this.service_array_item_id = service_array_item_id;
    }

    public JSONArray getJSONArray() {
        return service_array_item_id;
    }
}
