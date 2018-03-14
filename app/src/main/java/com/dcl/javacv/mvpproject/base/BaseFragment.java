package com.dcl.javacv.mvpproject.base;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dcl.javacv.mvpproject.utils.ToastUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Administrator on 2018/3/12.
 */

public abstract class BaseFragment extends SupportFragment implements BaseView, View.OnClickListener {

    protected boolean isInit;
    private View rootView;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int layoutRes = layoutRes();
        if (0 != layoutRes) {
            return inflater.inflate(layoutRes, null);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rootView = view;
        if (unbinder == null)
            unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        isInit = true;
        init();
    }

    protected <T extends View> T findViewById(@IdRes int id) {
        return rootView.findViewById(id);
    }

    /**
     * 设置点击事件.
     *
     * @param ids 被点击View的ID
     * @return {@link BaseFragment}
     */
    public BaseFragment setOnClick(@IdRes int... ids) {
        for (int id : ids) {
            rootView.findViewById(id).setOnClickListener(this);
        }
        return this;
    }

    /**
     * 设置点击事件.
     *
     * @param views 被点击View的ID
     * @return {@link BaseFragment}
     */
    public BaseFragment setOnClick(View... views) {
        for (View view : views) {
            view.setOnClickListener(this);
        }
        return this;
    }

    protected abstract void init();

    @Override
    public void onDestroy() {
        rootView = null;
        super.onDestroy();
    }

    protected abstract int layoutRes();

    @Override
    public void showTipMsg(String msg) {
        ToastUtils.showTipMsg(msg);
    }

    @Override
    public void showTipMsg(int msg) {
        ToastUtils.showTipMsg(msg);
    }

    @Override
    public void showLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
    /*if (activity instanceof BaseMvpActivity) {
      activity.showLoading();
    }*/
    }

    @Override
    public void hideLoading() {
        BaseActivity activity = (BaseActivity) getActivity();
    /*if (activity instanceof BaseMvpActivity) {
      activity.hideLoading();
    }*/
    }

    @Override
    public void invalidToken() {
        BaseActivity activity = (BaseActivity) getActivity();
    /*if (activity instanceof BaseMvpActivity) {
      activity.invalidToken();
    }*/
    }

    @Override
    public void onClick(View view) {

    }

    /**
     * Finish当前页面，最好实现onBackPressedSupport()，这个方法会有一个退栈操作，
     * 开源框架实现的，我们不用管
     */
    @Override
    public void myFinish() {
        onBackPressedSupport();
    }
}
