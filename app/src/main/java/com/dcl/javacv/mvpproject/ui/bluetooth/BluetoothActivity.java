package com.dcl.javacv.mvpproject.ui.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dcl.javacv.mvpproject.R;
import com.dcl.javacv.mvpproject.base.BaseMvpActivity;
import com.dcl.javacv.mvpproject.contract.BluetoothContract;
import com.dcl.javacv.mvpproject.utils.BluetoothReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public class BluetoothActivity extends BaseMvpActivity<BluetoothContract.Presenter> implements BluetoothContract.IView {

    @BindView(R.id.recycler)
    RecyclerView recycler;

    private BluetoothReceiver receiver;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_bluetooth;
    }

    @Override
    protected void initData() {
        super.initData();
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(basePresenter.getAdapter());

        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        receiver = new BluetoothReceiver(basePresenter);
        registerReceiver(receiver, filter);

    }

    @OnClick(R.id.search)
    public void onViewClicked() {
        basePresenter.searchBlueTooth();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }


}
