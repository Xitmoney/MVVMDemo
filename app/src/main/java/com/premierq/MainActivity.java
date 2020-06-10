package com.premierq;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        BottomNavigationView bottomNav = findViewById(R.id.nav_view);

        /*整合关联控件*/
        NavigationUI.setupWithNavController(bottomNav,navController);


        NavOptions.Builder options=new NavOptions.Builder();
        /*第二个参数true防止点击导航栏某个模块时，在栈堆生成多次实例*/
        //options.setPopUpTo(R.id.blankFragment,true);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                String title=item.getTitle().toString();
                switch (item.getItemId()) {
                    case R.id.blankFragment:
                        options.setPopUpTo(R.id.blankFragment,true);
                        navController.navigate(R.id.blankFragment,null,options.build());
                        Log.e("nav","首页");
                        break;
                    case R.id.blankFragment2:
                        options.setPopUpTo(R.id.blankFragment2,true);
                        navController.navigate(R.id.blankFragment2,null,options.build());
                        Log.e("nav","仪表盘");
                        break;
                    case R.id.blankFragment3:
                        navController.navigate(R.id.blankFragment3);
                        Log.e("nav","通知");
                        break;
                    case R.id.blankFragment4:
                        navController.navigate(R.id.blankFragment4);
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
        return Navigation.findNavController(this,R.id.nav_view).navigateUp();
    }
}