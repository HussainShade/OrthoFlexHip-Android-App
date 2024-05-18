package com.example.orthoflexhip.api;

import org.json.JSONObject;

public interface ServiceInterface {

    void post(
            String path,
            JSONObject params,
            OnSuccessListener<JSONObject> completionSuccessHandler,
            OnErrorListener<String> completionErrorHandler
    );

    void patch(
            String path,
            JSONObject params,
            OnSuccessListener<JSONObject> completionSuccessHandler,
            OnErrorListener<String> completionErrorHandler
    );

    void delete(
            String path,
            OnSuccessListener<String> completionSuccessHandler,
            OnErrorListener<String> completionErrorHandler
    );


    void get(
            String path,
            OnSuccessListener<JSONObject> completionSuccessHandler,
            OnErrorListener<String> completionErrorHandler
    );

    interface OnSuccessListener<T> {
        void onSuccess(T response);
    }

    interface OnErrorListener<T> {
        void onError(T response);
    }
}

