package com.example.pariksha.response;

import java.beans.ConstructorProperties;

public class Response<T> {
    private boolean isSuccess;
    private T body;
    private String message;

    public Response(T body, String message){
        this(true,body,message);
    }

    public static Response error(String message) {
        return new Response(false, (Object)null, message);
    }

    public boolean isSuccess() {
        return this.isSuccess;
    }

    public T getBody() {
        return this.body;
    }

    public String getMessage() {
        return this.message;
    }

    public void setSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public void setBody(T body) {
        this.body = body;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Response() {
        this.isSuccess = false;
    }

    @ConstructorProperties({"isSuccess", "body", "message"})
    public Response(boolean isSuccess, T body, String message) {
        this.isSuccess = false;
        this.isSuccess = isSuccess;
        this.body = body;
        this.message = message;
    }
}
