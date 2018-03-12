package com.dcl.javacv.mvpproject.base;

import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.StringRes;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dcl.javacv.mvpproject.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/3/12.
 */

public abstract class MyBaseActivity extends BaseActivity implements View.OnClickListener{

    /**
     * 保存当前activity对象，在OnCreate里面添加，记得在OnDestroy里面移除
     * 有什么用呢？
     * 比方说有一个需求，让你在任意位置弹出对话框，弹对话框又需要一个context对象，这个时候，
     * 你就只用传当前list的最上层的activity对象就可以了
     * 当然还有其他需求
     */
    public static List<BaseActivity> activities = new ArrayList<>();
    private LinearLayout toolbar;
    private TextView tvToolbarTitle;
    private TextView tvToolbarRight;
    private TextView tvBack;

    @Override
    public void init(Bundle savedInstanceState) {
        activities.add(this);
        //强制竖屏(不强制加)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        int layoutId = getLayoutId(savedInstanceState);
        View inflate = getLayoutInflater().inflate(R.layout.activity_base, toolbar, false);
        LinearLayout rootLinearLayout = inflate.findViewById(R.id.ll_layout_base_activity);
        //没有布局的时候传0
        if (0 == layoutId) {
            setContentView(rootLinearLayout);
        } else {
            View rootView = getLayoutInflater().inflate(layoutId, rootLinearLayout, true);
            setContentView(rootView);
        }
        stateBar();
        initView();
        initData();
        setOnClick(R.id.tv_back_base_activity);
    }

    /**
     * 设置点击事件.
     *
     * @param ids 被点击View的ID
     * @return {@link BaseActivity}
     */
    public BaseActivity setOnClick(@IdRes int... ids) {
        View view;
        for (int id : ids) {
            view = findViewById(id);
            if (null != view) {
                view.setOnClickListener(this);
            }
        }
        return this;
    }

    /**
     * 设置点击事件.
     *
     * @param views 被点击View
     * @return {@link BaseActivity}
     */
    public BaseActivity setOnClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
        return this;
    }

    /**
     * 获取当前布局对象
     *
     * @param savedInstanceState 这个是当前activity保存的数据，最常见的就是横竖屏切换的时候，
     *                           数据丢失问题
     * @return 当前布局的int值
     */
    protected abstract int getLayoutId(Bundle savedInstanceState);

    @Override
    protected void onDestroy() {
        activities.remove(this);
        super.onDestroy();
    }

    protected void initData() {
    }

    protected void initView() {
        toolbar = findViewById(R.id.toolbar_base_activity);
        tvToolbarTitle = findViewById(R.id.tv_title_base_activity);
        tvToolbarRight = findViewById(R.id.tv_right_base_activity);
    }

    /**
     * 设置状态栏背景颜色，不能改变状态栏内容的颜色
     */
    private void stateBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setNavigationBarTintEnabled(true);
        tintManager.setTintColor(Color.parseColor("#000000"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_back_base_activity:
                onBackPressedSupport();
                break;
            default:
                break;
        }
    }

    public MyBaseActivity setTitles(CharSequence title) {
        tvToolbarTitle.setText(title);
        return this;
    }

    /**
     * 初始化toolbar的内容
     * @param isShowToolbar 是否显示toolbar
     * @param isShowBack 是否显示左边的TextView
     * @param isShowMore 是否显示右边的TextView
     * @return 当前activity对象，可以连点
     */
    protected MyBaseActivity initToolbar(boolean isShowToolbar, boolean isShowBack,
                                       boolean isShowMore) {
//        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar) {
            if (isShowToolbar) {
                actionBar.show();
                tvBack = findViewById(R.id.tv_back_base_activity);
                TextView textView = findViewById(R.id.tv_right_base_activity);
                if (null != tvBack && null != textView) {
                    tvBack.setVisibility(isShowBack ? View.VISIBLE : View.INVISIBLE);
                    textView.setVisibility(isShowMore ? View.VISIBLE : View.INVISIBLE);
                }
            } else {
                actionBar.hide();
            }
        }
        return this;
    }

    public MyBaseActivity setToolbarBack(int colorId) {
        toolbar.setBackgroundColor(getResources().getColor(colorId));
        return this;
    }

    @SuppressWarnings("unused")
    public MyBaseActivity setMyTitle(String title) {
        tvToolbarTitle.setText(title);
        return this;
    }

    public MyBaseActivity setMyTitle(@StringRes int stringId) {
        tvToolbarTitle.setText(stringId);
        return this;
    }

    public void setMoreTitle(String moreTitle) {
        tvToolbarRight.setText(moreTitle);
    }

    public MyBaseActivity setMoreTitle(@StringRes int stringId) {
        tvToolbarRight.setText(stringId);
        return this;
    }

    /**
     * 设置左边内容.
     *
     * @param leftTitle 内容
     * @return {@link BaseActivity}
     */
    public MyBaseActivity setLeftTitle(String leftTitle) {
        if (tvBack != null) {
            tvBack.setOnClickListener(this);
            tvBack.setText(leftTitle);
        }
        return this;
    }

    /**
     * 设置左边内容.
     *
     * @param leftTitle 内容
     */
    public void setLeftTitle(@StringRes int leftTitle) {
        if (tvBack != null) {
            tvBack.setOnClickListener(this);
            tvBack.setText(leftTitle);
        }
    }

    @SuppressWarnings("unused")
    protected MyBaseActivity setMoreBackground(int resId) {
        tvToolbarRight.setBackgroundResource(resId);
        return this;
    }
}
