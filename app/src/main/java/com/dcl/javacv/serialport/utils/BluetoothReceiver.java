package com.dcl.javacv.serialport.utils;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dcl.javacv.serialport.contract.BluetoothContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public class BluetoothReceiver extends BroadcastReceiver {

    private final String lockName = "BOLUTEK";
    private List<String> devices;
    private List<BluetoothDevice> deviceList;
    private BluetoothContract.Presenter presenter;

    public BluetoothReceiver(BluetoothContract.Presenter presenter) {
        this.presenter = presenter;
        devices = new ArrayList<>();
        deviceList = new ArrayList<>();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (isLock(device)) {
                devices.add(device.getName());
            }
            deviceList.add(device);
        }
        presenter.showDevices(devices,deviceList);
    }

    private boolean isLock(BluetoothDevice device) {
        boolean isLockName = (device.getName()).equals(lockName);
        boolean isSingleDevice = devices.indexOf(device.getName()) == -1;
        return isLockName && isSingleDevice;
    }



}
