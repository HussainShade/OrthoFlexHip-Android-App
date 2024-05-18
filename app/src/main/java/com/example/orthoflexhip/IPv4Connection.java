//package com.example.orthoflexhip;
//
//import android.content.Context;
//import android.content.SharedPreferences;
//
//public class IPv4Connection {
//    private static final String PREF_NAME = "MyPrefs";
//    private static final String CONNECTION_ID_KEY = "connectionId";
//    private static String baseUrl = "https://9f65-2409-408d-612-8a38-20cf-efb9-76a0-c72c.ngrok-free.app/orthoflex_hip/";
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    private String id="username";
//
//    public static String getBaseUrl() {
//        return baseUrl;
//    }
//
//    public static void setBaseUrl(String baseUrl) {
//        IPv4Connection.baseUrl = baseUrl;
//    }
//
//    private static String connectionId;
//
//    public static String getConnectionId(Context context) {
//        if (connectionId == null) {
//            SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//            connectionId = prefs.getString(CONNECTION_ID_KEY, null);
//        }
//        return connectionId;
//    }
//
//    public static void setConnectionId(Context context, String connectionId) {
//        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = prefs.edit();
//        editor.putString(CONNECTION_ID_KEY, connectionId);
//        editor.apply();
//
//        IPv4Connection.connectionId = connectionId;
//    }
//}
