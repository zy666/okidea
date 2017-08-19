package com.doublewillow.okidea.lib;

import java.io.File;
import java.util.HashMap;

/**
 * @author zhouyou
 * @version 1.0
 * @desc
 * @date 2017/7/21 15:36
 */

public class Params {
    public static final String METHOD_GET = "get";
    public static final String METHOD_POST = "post";
    private HashMap<String, Object> urlParams = new HashMap<>();
    private HashMap<String, File> fileParams = new HashMap<>();
    private HashMap<String, String> headers = new HashMap<>();

    private String api = "";
    private String baseUrl = "";
    private String method;
    private boolean mUseCache = false;

    public Params() {
        urlParams = new HashMap<String, Object>();
        fileParams = new HashMap<String, File>();
        headers = new HashMap<String, String>();
        setMethod(METHOD_POST);
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getUrl() {
        String s = baseUrl + api;
        if (!baseUrl.contains("renwohua.com")) {
            s = s.replace("/userapi/", ":8181/");
            s = s.replace("/payapi/", ":8182/");
            s = s.replace("/coreapi/", ":8185/");
        }
        return s;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void putHeaders(final String key, final int value) {
        this.putHeaders(key, String.valueOf(value));
    }

    public void putHeaders(final String key, final String value) {
        headers.put(key, value);
    }

    public void put(String key, Object value) {
        if (key != null) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, String value) {
        if (key != null) {
            urlParams.put(key, value);
        }
    }

    public void put(String key, int value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    public void put(String key, double value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    public void put(String key, float value) {
        if (key != null) {
            urlParams.put(key, String.valueOf(value));
        }
    }

    /**
     * @param key
     * @param file
     */
    public void put(String key, File file) {
        if (file != null && file.exists()) {
            fileParams.put(key, file);
        }
    }

    public boolean isMultipart() {
        return !fileParams.isEmpty();
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, Object> getUrlParams() {
        return urlParams;
    }

    public HashMap<String, File> getFileParams() {
        return fileParams;
    }

    @Override
    public String toString() {
        return urlParams.toString() + "       header=" + headers.toString();
    }

    public String getCacheKey() {
        String cacheKey = getUrl() + urlParams.toString();
        if (headers.containsKey("token")) {
            String token = headers.get("token");
            cacheKey += token;
        }

        if (headers.containsKey("Version")) {
            String version = headers.get("Version");
            cacheKey += version;
        }
        cacheKey = Md5CryptKit.crypt(cacheKey.getBytes());
        return cacheKey;
    }

    public void useCache(boolean cache) {
        mUseCache = cache;
    }

    public boolean isUseCache() {
        return mUseCache;
    }
}
