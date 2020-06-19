package com.premierq.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.premierq.BR;
import com.premierq.R;
import com.premierq.databinding.FragmentBlankBinding;
import com.premierq.viewmodel.BlankFragmentVM;
import com.premierqlibrary.base.BaseFragment;

public class BlankFragment extends BaseFragment<FragmentBlankBinding, BlankFragmentVM> {


    @Override
    public void initData() {

    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_blank;
    }

    @Override
    public int initVariableId() {
        return BR.blankfragmentVM;
    }

    public BlankFragmentVM initViewModel(){
        BlankFragmentVM blankFragmentVM=new ViewModelProvider(this).get(BlankFragmentVM.class);
        return blankFragmentVM;
    }

    @Override
    public void processLogic() {

    }
}