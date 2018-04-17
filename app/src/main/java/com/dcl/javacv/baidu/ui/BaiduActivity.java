package com.dcl.javacv.baidu.ui;

import android.Manifest;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.dcl.javacv.baidu.R;
import com.dcl.javacv.baidu.base.BaseMvpActivity;
import com.dcl.javacv.baidu.contract.BaiduContract;
import com.dcl.javacv.baidu.presenter.BaiduPresenter;
import com.dcl.javacv.baidu.utils.MyLocationListener;
import com.dcl.javacv.baidu.utils.RxBus;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by dengchunlin on 2018/4/16.
 */

public class BaiduActivity extends BaseMvpActivity<BaiduPresenter> implements BaiduContract.IView {

    @BindView(R.id.show_location)
    TextView showLocation;

    public LocationClient mLocationClient = null;
    private MyLocationListener myListener = new MyLocationListener();
    private RxPermissions rxPermissions;
    private LocationClientOption mOption;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initData() {
        super.initData();
        //声明LocationClient类
        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.setLocOption(getDefaultLocationClientOption());
        //注册监听函数
        mLocationClient.registerLocationListener(myListener);

        rxPermissions = new RxPermissions(this);
        requestPermissions();
        RxBus.getInstance().toObservable(BDLocation.class)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(location -> {
                    if (location != null) {
                        String addr = location.getAddrStr();    //获取详细地址信息
                        String country = location.getCountry();    //获取国家
                        String province = location.getProvince();    //获取省份
                        String city = location.getCity();    //获取城市
                        String district = location.getDistrict();    //获取区县
                        String street = location.getStreet();    //获取街道信息
                        String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
                        String temp = country + province + city + district + street + addr + "\n" + locationDescribe;
                        if (!TextUtils.isEmpty(temp))
                            showLocation.setText(temp);
                    }
                });

    }

    private void requestPermissions() {
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE)
                .subscribe(aBoolean -> {
                    if (aBoolean) {
                        mLocationClient.start();
                    } else {
                        showTipMsg("用户禁用定位！");
                        // Denied permission with ask never again
                        // Need to go to the settings
                    }
                });


    }

    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(5000);//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
            mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.setOpenGps(true);//可选，默认false，设置是否开启Gps定位
            mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return mOption;
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_baidu;
    }

    @OnClick(R.id.get_location)
    public void onViewClicked() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
    }
}
