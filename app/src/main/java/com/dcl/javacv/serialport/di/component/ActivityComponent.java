package com.dcl.javacv.serialport.di.component;

import com.dcl.javacv.serialport.ui.MainActivity;
import com.dcl.javacv.serialport.di.module.ActivityModule;
import com.dcl.javacv.serialport.di.scope.ActivityScope;
import com.dcl.javacv.serialport.ui.bluetooth.BluetoothActivity;
import com.dcl.javacv.serialport.ui.login.LoginActivity;
import com.dcl.javacv.serialport.ui.serial.SerialPortActivity;

import dagger.Component;

/**
 * Created by dengchunlin on 2018/3/12.
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    void inject(LoginActivity loginActivity);
    void inject(BluetoothActivity bluetoothActivity);
    void inject(SerialPortActivity serialPortActivity);
}
