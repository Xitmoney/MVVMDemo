package com.premierq.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModelProvider;

import com.premierq.BR;
import com.premierq.R;
import com.premierq.databinding.LoginActivityBinding;
import com.premierq.entity.PhoneEntityTest;
import com.premierq.viewmodel.LoginViewModel;
import com.premierqlibrary.base.BaseActivity;

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
    public void processLogic() {

        PhoneEntityTest info=new PhoneEntityTest();
        info.setArea("132");
        viewModel.getPhoneLiveData().setValue(info);
        viewModel.getPhoneData();

        viewModel.getRandLiveData().setValue(0);
        viewModel.randChangeTextBackground();
    }


    @Override
    public LoginViewModel initViewModel() {
        Log.e("aaa","initViewModel");
        LoginViewModel loginViewModel=new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(LoginViewModel.class);
        return loginViewModel;
    }



}
