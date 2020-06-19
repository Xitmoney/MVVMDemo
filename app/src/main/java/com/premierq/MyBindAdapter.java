package com.premierq;

import android.widget.TextView;

import androidx.databinding.BindingAdapter;

/**
 * Created by xiaodengwen.
 * E-mail: leoan0923@163.com
 * Date: 2020/06/19
 * Desc:
 */
public class MyBindAdapter {

    final static int count =100;

    @BindingAdapter(value = {"changeTextBackground"},requireAll = false)
    public static void changeTextBackgroundColor(TextView textView,int floatNumber){
        if(count<floatNumber)
            setColor(textView,R.color.red_one);
        else
            setColor(textView,R.color.green);
    }

    public static void setColor(TextView textView,int colorId){
        textView.setBackgroundColor(textView.getContext().getResources().getColor(colorId));
    }
}
