package com.groupware.employwise.model;

import java.time.Instant;

public class Response {

    private String timestamp;
    private String message;
    private Object data;

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now().toString();
    }

    public Response() {
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}
