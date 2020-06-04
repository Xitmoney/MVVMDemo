package com.premierqlibrary.net;

/**
* 基础数据定义BaseResponse
 * xiaodengwen
 * 2020/6/3
* */
public class BaseResponse<T> {
  /*  private int code;
    private String message;
    private T Entity;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getEntity() {
        return Entity;
    }

    public void setEntity(T entity) {
        Entity = entity;
    }*/


    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    private int success;

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    T result;
}
