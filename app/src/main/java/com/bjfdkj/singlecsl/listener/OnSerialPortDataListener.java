package com.bjfdkj.singlecsl.listener;

/**
 * Created by cuishu on 2018/6/25.
 * 串口消息监听
 */

public interface OnSerialPortDataListener {

    //接收数据
    void onDataReceived(byte[] bytes);

    //发送数据
    void  onDataSent(byte[] bytes);
}
