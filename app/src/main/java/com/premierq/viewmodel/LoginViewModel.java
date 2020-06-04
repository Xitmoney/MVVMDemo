package com.premierq.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.premierq.entity.UserInfo;
import com.premierqlibrary.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel {
    MutableLiveData<UserInfo> userLiveData=null;

    public MutableLiveData<UserInfo> getUserLiveData(){
        if(userLiveData==null)
            userLiveData=new MutableLiveData<>();
        return userLiveData;
    }
}
