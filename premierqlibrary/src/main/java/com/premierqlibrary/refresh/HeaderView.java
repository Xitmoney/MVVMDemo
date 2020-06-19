package com.premierqlibrary.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.premierqlibrary.R;
import com.scwang.smart.refresh.layout.api.RefreshHeader;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

import java.util.Date;

/**
 * Created by xiaodengwen.
 * E-mail: leoan0923@163.com
 * Date: 2020/06/11
 * Desc:自定义刷新头部
 */
public class HeaderView extends LinearLayout implements RefreshHeader {

    Context context;
    TextView mHeaderText,mHeaderDate;//标题文本


    public HeaderView(Context context) {
        super(context);
        this.context=context;
        initView(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView(context);
    }

    public HeaderView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        initView(context);
    }

    private void initView(Context context) {
        setGravity(Gravity.CENTER);
        //View.inflate(context, R.layout.headr_view, this);
        View view=LayoutInflater.from(context).inflate(R.layout.headr_view,this);
        mHeaderText=view.findViewById(R.id.srl_classics_title);
        mHeaderDate=view.findViewById(R.id.srl_classics_update);
    }


    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        if (success) {
            mHeaderText.setText("成功");
            mHeaderDate.setText(String.valueOf(new Date()));
        } else {
            mHeaderText.setText("mTextFailed");
        }
        return 500;//延迟500毫秒之后再弹回
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mHeaderText.setText("下拉开始刷新");
                break;
            case Refreshing:
                mHeaderText.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                mHeaderText.setText("释放立即刷新");
                break;
        }
    }


    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }
}
