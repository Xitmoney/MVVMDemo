package com.premierqlibrary.base;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import com.trello.rxlifecycle2.LifecycleProvider;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BaseViewModel extends ViewModel implements IBaseViewModel {

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.e("aaa","onCleared");
        //ViewModel销毁时会执行，同时取消所有异步任务
        /*if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }*/
    }


    @Override
    public void onAny(LifecycleOwner owner, Lifecycle.Event event) {

    }

    @Override
    public void onCreate() {
        Log.e("aaa","onCreate");
    }

    @Override
    public void onDestroy() {
        Log.e("aaa","onDestroy");
    }

    @Override
    public void onStart() {
        Log.e("aaa","onStart");
    }

    @Override
    public void onStop() {
        Log.e("aaa","onStop");
    }

    @Override
    public void onResume() {
        Log.e("aaa","onResume");
    }

    @Override
    public void onPause() {
        Log.e("aaa","onPause");
    }

    @Override
    public void registerRxBus() {

    }

    @Override
    public void removeRxBus() {

    }
}
