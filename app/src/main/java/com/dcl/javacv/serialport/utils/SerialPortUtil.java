package com.dcl.javacv.serialport.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android_serialport_api.SerialPort;

/**
 * Created by dengchunlin on 2018/4/8.
 * 串口工具类
 */

public class SerialPortUtil {

    private static SerialPort mSerialPort;
    private static OutputStream mOutputStream;
    private static InputStream mInputStream;

    /**
     * 开串口，私有构造方法完成初始化工作
     */
    public SerialPortUtil() {
        if (mSerialPort == null) {
            String path = "dev/ttys7";
            int baudrate = 9600;
            try {
                mSerialPort = new SerialPort(new File(path), baudrate, 0);
                mOutputStream = mSerialPort.getOutputStream();
                mInputStream = mSerialPort.getInputStream();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发送数据给串口
     */
    public static void sendData(byte[] writeBytes) {
        try {
            if (mOutputStream != null)
                mOutputStream.write(writeBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 接受串口返回数据
     */
    public static String readData() {
        String readDatas = null;
        for (int i = 0; i < 10; i++) {
            try {
                if (mInputStream != null) {
                    byte[] buffer = new byte[7];
                    //这一句会阻塞主线程，假如没有数据返回，程序就会直接崩掉，需要在子线程执行
                    int size = mInputStream.read(buffer);
                    if (size > 0) {
                        //证明有返回数据
                        //处理数据
                        readDatas = SerialDataUtil.ByteArrToHex(buffer);
                        break;//停止循环
                    } else {
                        Thread.sleep(2000);
                    }
                    if (i == 9) {
                        break;//停止循环
                    }
                }
            } catch (Exception e) {

            }
        }
        return readDatas;
    }


}
