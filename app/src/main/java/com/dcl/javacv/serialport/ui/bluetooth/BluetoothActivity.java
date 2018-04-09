package com.dcl.javacv.serialport.ui.bluetooth;

import android.bluetooth.BluetoothDevice;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dcl.javacv.serialport.R;
import com.dcl.javacv.serialport.base.BaseMvpActivity;
import com.dcl.javacv.serialport.contract.BluetoothContract;
import com.dcl.javacv.serialport.utils.BluetoothReceiver;

import butterknife.BindView;
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
