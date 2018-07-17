package com.bjfdkj.singlecsl.listener;

import java.io.File;

/**
 * Created by cuishu on 2018/6/25.
 * 打开串口监听  听众
 *
 * CPP文件
 */

public interface OnOpenSerialPortListener {

    void   onSuccess(File device);  //文件
    void  onFail(File device, Status status);  //状态

    enum  Status
    {
        NO_READ_WRITE_PERMISSION,
        OPEN_FAIL
    }
}
