package com.premierq.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.premierq.BR;

public class UserInfo extends BaseObservable {
    public String sName;

    public void setsName(String sName) {
        this.sName = sName;
        notifyPropertyChanged(BR.sName);
    }

    @Bindable
    public String getsName() {
        return sName;
    }
}
