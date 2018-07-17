package com.bjfdkj.singlecsl.manager_seriaPort_device;

import android.util.Log;

import java.io.File;
import java.io.FileDescriptor;
import java.io.IOException;

/**
 * Created by cuishu on 2018/6/25.
 * 串口类  代码集成
 */

public class SerialPort {
    static {
        System.loadLibrary("SerialPort");
    }
    private static final String TAG = SerialPort.class.getSimpleName();   //
    /**
     * 文件设置最高权限  777  可读 写  执行
     * param   file 文件
     * return  权限修改是否成功
     */
    boolean chmod777(File file) {
        if (null == file || !file.exists()) {  //如果文件 不存在
            return false;
        }
        try {
            //得到ROOT 权限
            Process su = Runtime.getRuntime().exec("/system/bin/su");
            //修改 文本属性为 （）可读写  执行
            String cmd = "chmod 777" + file.getAbsolutePath() + "\n" + "exit\n";
            su.getOutputStream().write(cmd.getBytes());
            if (0 == su.waitFor() && file.canRead() && file.canWrite() && file.canExecute()) ;
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            //没有 root 权限
            Log.e("----", "没有 root 权限");
            e.printStackTrace();
        }
        return false;
    }
    //打开 串口
    protected native FileDescriptor open(String path, int baudRate, int flags);
    //关闭 串口
    protected native void close();
}
