package com.premierq.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProvider;

import com.premierq.BR;
import com.premierq.R;
import com.premierq.common.RetrofitHelper;
import com.premierq.databinding.LoginActivityBinding;
import com.premierq.entity.PhoneEntityTest;
import com.premierq.entity.UserInfo;
import com.premierq.viewmodel.LoginViewModel;
import com.premierqlibrary.base.BaseActivity;
import com.premierqlibrary.net.BaseObserver;
import com.premierqlibrary.net.RxSchedulersHelp;

public class LoginActivity extends BaseActivity<LoginActivityBinding,LoginViewModel> {
    @Override
    public int initContentView(Bundle savedInstanceState) {
        Log.e("aaa","initContentView");
        return R.layout.login_activity;
    }

    @Override
    public int initVariableId() {
        Log.e("aaa","initVariableId");
        return BR.login;
    }


    @Override
    public void initViewObservable() {
        RetrofitHelper.getPhoneApiService()
                .getBaseResponsePhone("phone.get","13800138000","10003","b59bc3ef6191eb9f747dd4e83c99f2a4","json")
                .compose(RxSchedulersHelp.observableIO2Main(this))
                .subscribe(new BaseObserver<PhoneEntityTest>(){

                    @Override
                    public void onSuccess(PhoneEntityTest entity) {

                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {

                    }
                });


       /* UserInfo info=new UserInfo();
        info.setsName("哈哈哈");
        viewModel.getUserLiveData().setValue(info);*/
    }


    @Override
    public LoginViewModel initViewModel() {
        Log.e("aaa","initViewModel");
        LoginViewModel loginViewModel=new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(LoginViewModel.class);
        return loginViewModel;
    }

}
