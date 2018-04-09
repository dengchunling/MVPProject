package com.dcl.javacv.serialport.contract;

import android.bluetooth.BluetoothDevice;

import com.dcl.javacv.serialport.base.BasePresenter;
import com.dcl.javacv.serialport.base.BaseView;
import com.dcl.javacv.serialport.ui.bluetooth.BlueMessageAdapter;

import java.util.List;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public interface BluetoothContract {
    interface IView extends BaseView {

    }

    interface Presenter extends BasePresenter<IView> {
        void searchBlueTooth();

        BlueMessageAdapter getAdapter();

        void showDevices(List<String> devices,List<BluetoothDevice> deviceList);
    }
}
