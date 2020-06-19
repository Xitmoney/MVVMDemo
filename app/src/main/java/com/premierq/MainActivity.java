package com.premierq;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.premierq.BR;
import com.premierq.R;
import com.premierq.databinding.ActivityMainBinding;
import com.premierq.viewmodel.MainActivityVM;
import com.premierqlibrary.base.BaseActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainActivityVM> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_main;
    }

    @Override
    public int initVariableId() {
        return BR.mainActivityVM;
    }


    @Override
    public MainActivityVM initViewModel() {
        return new ViewModelProvider(this).get(MainActivityVM.class);
    }

    @Override
    public void initData() {
 /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);*/

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        /*整合关联控件*/
        NavigationUI.setupWithNavController(binding.navView,navController);

        Bundle bundle=new Bundle();
        bundle.putString("name","xiaowensn");

        NavOptions.Builder options=new NavOptions.Builder();
        /*第二个参数true防止点击导航栏某个模块时，在栈堆生成多次实例*/
        //options.setPopUpTo(R.id.blankFragment,true);
        binding.navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title=item.getTitle().toString();
                switch (item.getItemId()) {
                    case R.id.blankFragment:
                        options.setPopUpTo(R.id.blankFragment,true);
                        navController.navigate(R.id.blankFragment,bundle,options.build());
                        Log.e("nav","首页");
                        break;
                    case R.id.blankFragmentTwo:
                        options.setPopUpTo(R.id.blankFragmentTwo,true);
                        navController.navigate(R.id.blankFragmentTwo,null,options.build());
                        Log.e("nav","仪表盘");
                        break;
                    case R.id.blankFragmentThree:
                        navController.navigate(R.id.blankFragmentThree);
                        Log.e("nav","通知");
                        break;
                    case R.id.blankFragmentFour:
                        navController.navigate(R.id.blankFragmentFour);
                        Log.e("nav","我的");
                        break;
                }
                return true;
            }
        });

    }


    /**
     * defaultNavHost 若为true，需要的Activity中重写onSupportNavigateUp方法。
     * 因为默认情况下返回键是不会回退fragment页面的
     * */
    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_view).navigateUp();
    }
}