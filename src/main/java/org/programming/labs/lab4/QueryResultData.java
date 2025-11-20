package org.programming.labs.lab4;

import java.util.List;
import java.util.Map;

public class QueryResultData {
    private final List<String> headers;
    private final List<Map<String, String>> data;

    public QueryResultData(List<String> headers, List<Map<String, String>> data) {
        this.headers = headers;
        this.data = data;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<Map<String, String>> getData() {
        return data;
    }
}
