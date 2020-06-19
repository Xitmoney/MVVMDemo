package com.premierqlibrary.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.premierqlibrary.bus.Messenger;
import com.trello.rxlifecycle3.components.support.RxFragment;


/**
 * Created by xiaodengwen.
 * E-mail: leoan0923@163.com
 * Date: 2020/06/15
 * Desc:
 */
public abstract class BaseFragment<V extends ViewDataBinding, VM extends BaseViewModel> extends RxFragment {

    protected V binding;
    protected VM viewModel;
    private int viewModelId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, initContentView(inflater, container, savedInstanceState), container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除Messenger注册
        Messenger.getDefault().unregister(viewModel);
        if (viewModel != null) {
            viewModel.removeRxBus();
        }
        if (binding != null) {
            binding.unbind();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //私有的初始化Databinding和ViewModel方法
        initViewDataBinding();
        //页面数据初始化方法
        processLogic();
        initData();
        //注册RxBus
        viewModel.registerRxBus();
    }


    /**
     * 注入绑定
     */
    private void initViewDataBinding() {
        viewModelId = initVariableId();
        viewModel = initViewModel();
        binding.setVariable(viewModelId, viewModel);
        //支持LiveData绑定xml，数据改变，UI自动会更新
        binding.setLifecycleOwner(this);
        //让ViewModel拥有View的生命周期感应
        getLifecycle().addObserver(viewModel);
    }


    public abstract void initData();



    /**
     * 初始化根布局
     *
     * @return 布局layout的id
     */
    public abstract int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);


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
    public abstract void processLogic();

}
