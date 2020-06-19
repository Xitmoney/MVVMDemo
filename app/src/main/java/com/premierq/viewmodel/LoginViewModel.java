package com.premierq.viewmodel;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.premierq.MainActivity;
import com.premierq.common.RepositoryHelper;
import com.premierq.entity.PhoneEntityTest;
import com.premierq.entity.UserInfo;
import com.premierqlibrary.base.BaseViewModel;
import com.premierqlibrary.common.ContextUtils;
import com.premierqlibrary.net.BaseObserver;
import com.premierqlibrary.net.RxSchedulersHelp;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class LoginViewModel extends BaseViewModel {
    MutableLiveData<UserInfo> userLiveData=null;
    MutableLiveData<PhoneEntityTest> phoneLiveData=null;
    MutableLiveData<Integer> randLiveData=null;

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

    public MutableLiveData<Integer> getRandLiveData(){
        if(randLiveData==null)
            randLiveData=new MutableLiveData<>();
        return randLiveData;
    }

    public void getPhoneData(){
        RepositoryHelper.getPhoneApiService()
                .getBaseResponsePhone("phone.get","13800138000","10003","b59bc3ef6191eb9f747dd4e83c99f2a4","json")
                .compose(RxSchedulersHelp.observableIO2Main(ContextUtils.getContext()))
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


    Timer timer=null;
    TimerTask task=null;
    public void randChangeTextBackground(){
        if(timer==null)
            timer=new Timer();
        task=new TimerTask() {
            @Override
            public void run() {
                int rand=new Random().nextInt(200);
                Log.e("rand",String.valueOf(rand));
                randLiveData.postValue(rand);
            }
        };
        timer.schedule(task,1000,2000);
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
