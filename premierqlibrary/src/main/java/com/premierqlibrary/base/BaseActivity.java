package com.premierqlibrary.base;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProvider;

import com.premierqlibrary.bus.Messenger;
import com.trello.rxlifecycle3.components.support.RxAppCompatActivity;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by xiaodengwen.
 * E-mail: leoan0923@163.com
 * Date: 2020/6/10
 * Desc:基础GIT
 */
public abstract class BaseActivity<V extends ViewDataBinding, VM extends BaseViewModel> extends RxAppCompatActivity {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //私有的初始化Databinding和ViewModel方法
        Log.e("aaa","onCreate");
        initViewDataBinding(savedInstanceState);
        initData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel);
        if (viewModel != null) {
            viewModel.removeRxBus();
        }
        if(binding != null){
            binding.unbind();
        }
    }

    /**
     * 在viewModel没有的时候，默认实例化一个
     * */
    public void createViewModel() {
        if (viewModel == null) {
            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                //如果没有指定泛型参数，则默认使用BaseViewModel
                modelClass = BaseViewModel.class;
            }
            viewModel = (VM)  new ViewModelProvider(this,new ViewModelProvider.NewInstanceFactory()).get(modelClass);
        }
    }

    /**
     * 注入绑定
     */
    private void initViewDataBinding(Bundle savedInstanceState) {
        //DataBindingUtil类需要在project的build中配置 dataBinding {enabled true }, 同步后会自动关联android.databinding包
        binding = DataBindingUtil.setContentView(this, initContentView(savedInstanceState));
        viewModelId = initVariableId();
        viewModel = initViewModel();
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);

        //关联ViewModel
        binding.setVariable(viewModelId, viewModel);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
    }

    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(Bundle savedInstanceState);


    /**
     * 初始化ViewModel
     *
     * @return 继承BaseViewModel的ViewModel
     */
    public VM initViewModel() {
        return null;
    }

    /**
     * 初始化ViewModel的id
     *
     * @return BR的id
     */
    public abstract int initVariableId();



    /**
     * 处理逻辑业务
     */
    public abstract void initData();



}
