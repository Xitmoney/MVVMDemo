package com.premierqlibrary.exception;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;

import retrofit2.HttpException;


/**
 * 异常处理
 */
public class ExceptionUtils {
    public static String exceptionHandler(Throwable e){
        String errorMsg = "未知错误";
        if(e instanceof HttpException){
            HttpException httpException = (HttpException) e;
            errorMsg = convertStatusCode(httpException);
        }
        else if (e instanceof ConnectException|| e instanceof UnknownHostException) {   //   连接错误
            errorMsg ="网络连接失败,请检查网络";
        }
        else if (e instanceof InterruptedIOException) {   //  连接超时
            errorMsg ="连接超时,请稍后再试";
        }
        else if (e instanceof JsonParseException || e instanceof JSONException|| e instanceof ParseException) {   //  解析错误
            errorMsg="解析服务器响应数据失败";
        }

        return errorMsg;
    }

    private static String convertStatusCode(HttpException httpException) {
        String msg;
        if (httpException.code() >= 500 && httpException.code() < 600) {
            msg = "服务器处理请求出错";
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            msg = "服务器无法处理请求";
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            msg = "请求被重定向到其他页面";
        } else {
            msg = httpException.message();
        }
        return msg;
    }
}
