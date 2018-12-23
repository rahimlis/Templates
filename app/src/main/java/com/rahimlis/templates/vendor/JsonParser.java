package com.rahimlis.templates.vendor;

import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.rahimlis.templates.models.Model;

/**
 * Created by rahim on 23/08/17.
 */

public class JsonParser {

    @Nullable
    public static <T extends Model> T parse(String json, Class<T> className) {
        if (json == null)
            return null;

        Gson gson = new Gson();
        return gson.fromJson(json, className);
    }
}
