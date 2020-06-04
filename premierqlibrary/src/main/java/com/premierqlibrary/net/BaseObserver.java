package com.premierqlibrary.net;


import com.premierqlibrary.exception.ExceptionUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 对基础数据统一处理
 ** xiaodengwen
 *  2020/6/3
 * **/
public abstract class BaseObserver<T> implements Observer<BaseResponse<T>> {

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(BaseResponse<T> response) {
        if(response.getSuccess()==1)
            onSuccess(response.getResult());
        else
            onFailure(null,"0");
    }

    @Override
    public void onError(Throwable e) {
        onFailure(e, ExceptionUtils.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
    }

    /**
     * 请求成功
     *
     * @param entity 服务器返回的数据
     */
    public abstract  void onSuccess(T entity);

    public abstract void onFailure(Throwable e, String errorMsg);



}
