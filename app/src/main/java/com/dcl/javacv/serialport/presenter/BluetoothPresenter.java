package com.dcl.javacv.serialport.presenter;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;

import com.dcl.javacv.serialport.MyApplication;
import com.dcl.javacv.serialport.base.BaseMvpPresenter;
import com.dcl.javacv.serialport.contract.BluetoothContract;
import com.dcl.javacv.serialport.model.DataHelper;
import com.dcl.javacv.serialport.ui.bluetooth.BlueMessageAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public class BluetoothPresenter extends BaseMvpPresenter<BluetoothContract.IView> implements BluetoothContract.Presenter {
    private DataHelper mDataHelper;
    private List<String> devices;
    private List<BluetoothDevice> deviceList;
    private BlueMessageAdapter mBlueMessageAdapter;

    @Inject
    public BluetoothPresenter() {
        mDataHelper = MyApplication.getAppComponent().getDataHelper();
        deviceList = new ArrayList<>();
        devices = new ArrayList<>();
    }

    @Override
    public void searchBlueTooth() {
        BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();
        if (!adapter.isEnabled()) {
            adapter.enable();
        }
        Intent enable = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        enable.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600); //3600为蓝牙设备可见时间 startActivity(enable);
    }

    @Override
    public BlueMessageAdapter getAdapter() {
        if (mBlueMessageAdapter == null) {
            mBlueMessageAdapter = new BlueMessageAdapter(devices);
        }
        return mBlueMessageAdapter;
    }

    @Override
    public void showDevices(List<String> devices,List<BluetoothDevice> deviceList) {
        this.devices = devices;
        this.deviceList = deviceList;
        mBlueMessageAdapter.notifyDataSetChanged();
    }
}
