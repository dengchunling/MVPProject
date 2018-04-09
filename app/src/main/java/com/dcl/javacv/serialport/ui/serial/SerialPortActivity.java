package com.dcl.javacv.serialport.ui.serial;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.dcl.javacv.serialport.R;
import com.dcl.javacv.serialport.base.BaseMvpActivity;
import com.dcl.javacv.serialport.contract.SerialPortContract;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by dengchunlin on 2018/4/8.
 */

public class SerialPortActivity extends BaseMvpActivity<SerialPortContract.Presenter> implements SerialPortContract.IView {

    @BindView(R.id.et_prot)
    EditText etProt;
    @BindView(R.id.et_num)
    EditText etNum;
    @BindView(R.id.text_receive)
    TextView textReceive;

    protected SerialPort mSerialPort;
    protected InputStream mInputStream;
    protected OutputStream mOutputStream;

    private TextView text;
    private String prot = "ttyS0";//串口号（具体的根据自己的串口号来配置）
    private int baudrate = 9600;//波特率（可自行设定）
    private static int i = 0;
    private StringBuilder sb;

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 1) {
                text.setText(text.getText().toString().trim()+sb.toString());
            }
        }
    };
    private Thread receiveThread;
    private Thread sendThread;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayoutId(Bundle savedInstanceState) {
        return R.layout.activity_serial_port;
    }

    @Override
    protected void initData() {
        super.initData();
        sb = new StringBuilder();

    }

    /**
     * 串口的关闭
     */
    public void closeSerialPort() {
        if (mSerialPort != null) {
            mSerialPort.close();
        }
        if (mInputStream != null) {
            try {
                mInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (mOutputStream != null) {
            try {
                mOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.btn_set)
    public void onBtnSetClicked() {
        prot = TextUtils.isEmpty(etProt.getText().toString().trim()) ? "ttyS0"
                : etProt.getText().toString().trim();
        baudrate = Integer.parseInt(TextUtils.isEmpty(etNum.getText()
                .toString().trim()) ? "9600" : etNum.getText()
                .toString().trim());
    }

    @OnClick(R.id.btn_open)
    public void onBtnOpenClicked() {
        // 配置并打开串口
        try {
            mSerialPort = new SerialPort(new File("/dev/" + prot), baudrate,
                    0);
            mInputStream = mSerialPort.getInputStream();
            mOutputStream = mSerialPort.getOutputStream();
            receiveThread();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("test", "打开失败");
            e.printStackTrace();
        }
    }

    @OnClick(R.id.btn_send)
    public void onBtnSendClicked() {
        // 发送串口信息
        sendThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        i++;
                        mOutputStream.write(("1").getBytes());
                        Log.i("test", "发送成功:1" + i);
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        Log.i("test", "发送失败");
                        e.printStackTrace();
                    }
                }
            }
        };
        sendThread.start();
    }

    @OnClick(R.id.btn_close)
    public void onBtnCloseClicked() {
        closeSerialPort();
    }

    private void receiveThread() {
        // 接收串口信息
        receiveThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    int size;
                    try {
                        byte[] buffer = new byte[1024];
                        if (mInputStream == null)
                            return;
                        size = mInputStream.read(buffer);
                        if (size > 0) {
                            String recinfo = new String(buffer, 0,
                                    size);
                            Log.i("test", "接收到串口信息:" + recinfo);
                            sb.append(recinfo).append(",");
                            handler.sendEmptyMessage(1);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        receiveThread.start();
    }
}
