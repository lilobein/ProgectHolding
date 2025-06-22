package com.data;

public class QueryResultWrapper {
    private static QueryResultWrapper instance;
    private Object data;

    private QueryResultWrapper() {}

    public static synchronized QueryResultWrapper getInstance() {
        if (instance == null) {
            instance = new QueryResultWrapper();
        }
        return instance;
    }

    public void wrap(Object data) {
        this.data = data;
    }

    public Object unwrap() {
        return data;
    }
}