package com.example.orthoflexhip.api;

import org.json.JSONObject;

public class APIController implements ServiceInterface {
    private final ServiceInterface service;

    public APIController(ServiceInterface serviceInjection) {
        this.service = serviceInjection;
    }

    @Override
    public void post(String path, JSONObject params, OnSuccessListener<JSONObject> completionSuccessHandler, OnErrorListener<String> completionErrorHandler) {
        service.post(path, params, completionSuccessHandler, completionErrorHandler);

    }

    @Override
    public void patch(String path, JSONObject params, OnSuccessListener<JSONObject> completionSuccessHandler, OnErrorListener<String> completionErrorHandler) {
        service.patch(path, params, completionSuccessHandler, completionErrorHandler);

    }

    @Override
    public void delete(String path, OnSuccessListener<String> completionSuccessHandler, OnErrorListener<String> completionErrorHandler) {
        service.delete(path, completionSuccessHandler, completionErrorHandler);

    }

    @Override
    public void get(String path, OnSuccessListener<JSONObject> completionSuccessHandler, OnErrorListener<String> completionErrorHandler) {
        service.get(path, completionSuccessHandler, completionErrorHandler);

    }


    public interface CompletionSuccessHandler {
        void onSuccess(JSONObject response);
    }

    public interface CompletionErrorHandler {
        void onError(String response);
}
}
