package com.premierq.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.premierq.MainActivity;
import com.premierq.common.RepositoryHelper;
import com.premierq.databinding.LoginActivityBinding;
import com.premierq.entity.PhoneEntityTest;
import com.premierq.entity.UserInfo;
import com.premierqlibrary.base.BaseViewModel;
import com.premierqlibrary.common.Utils;
import com.premierqlibrary.net.BaseObserver;
import com.premierqlibrary.net.RxSchedulersHelp;

public class LoginViewModel extends BaseViewModel {
    MutableLiveData<UserInfo> userLiveData=null;
    MutableLiveData<PhoneEntityTest> phoneLiveData=null;

    /**
     * 双向绑定，获取EditText控制
    * */
    public MutableLiveData<String> pass=new MutableLiveData<>();


    public LoginViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<UserInfo> getUserLiveData(){
        if(userLiveData==null)
            userLiveData=new MutableLiveData<>();
        return userLiveData;
    }

    public MutableLiveData<PhoneEntityTest> getPhoneLiveData(){
        if(phoneLiveData==null)
            phoneLiveData=new MutableLiveData<>();
        return phoneLiveData;
    }

    public void getPhoneData(){
        RepositoryHelper.getPhoneApiService()
                .getBaseResponsePhone("phone.get","13800138000","10003","b59bc3ef6191eb9f747dd4e83c99f2a4","json")
                .compose(RxSchedulersHelp.observableIO2Main(Utils.getContext()))
                .subscribe(new BaseObserver<PhoneEntityTest>(){

                    @Override
                    public void onSuccess(PhoneEntityTest entity) {
                        getPhoneLiveData().setValue(entity);
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }


    public void onClickF(View view){
        Log.e("qqq",pass.getValue());
        pass.setValue("123");
        getApplication().startActivity(new Intent(getApplication(), MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}
