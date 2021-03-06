/*
 * Copyright (C) 2015 The SlimRoms Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.slim.slimfilemanager.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsProvider {

    public static final String KEY_ENABLE_ROOT = "enable_root";
    public static final String THEME = "app_theme";
    public static final String SORT_MODE = "sort_mode";

    public static SharedPreferences get(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static SharedPreferences.Editor put(Context context) {
        return get(context).edit();
    }

    public static String getString(Context context, String key, String defValue) {
        return get(context).getString(key, defValue);
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return get(context).getBoolean(key, defValue);
    }

    public static int getInt(Context context, String key, int defValue) {
        int i;
        try {
            i = get(context).getInt(key, defValue);
        } catch (Exception e) {
            i = Integer.parseInt(get(context).getString(key, Integer.toString(defValue)));
        }
        return i;
    }

    public static void putInt(Context context, String key, int value) {
        put(context).putInt(key, value).commit();
    }

    public static ArrayList<String> getListString(Context context,
                                                  String key, ArrayList<String> def) {
        ArrayList<String> array = new ArrayList<>(
                Arrays.asList(TextUtils.split(get(context).getString(key, ""), "‚‗‚")));
        if (array.isEmpty()) {
            array.addAll(def);
        }
        return array;
    }

    public static void putListString(Context context, String key, ArrayList<String> stringList) {
        String[] myStringList = stringList.toArray(new String[stringList.size()]);
        put(context).putString(key, TextUtils.join("‚‗‚", myStringList)).apply();
    }
}