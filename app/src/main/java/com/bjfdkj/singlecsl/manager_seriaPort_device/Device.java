package com.bjfdkj.singlecsl.manager_seriaPort_device;

import java.io.File;
import java.io.Serializable;

/**
 * Created by cuishu on 2018/6/25.  串口
 * 设备  Device   封装字段
 */

public class Device implements Serializable {

    private static final String TAG = Device.class.getSimpleName();
    private String name;  //名称
    private String root;  //权限
    private File file;  //文件

    public Device(String name, String root, File file) {
        this.name = name;
        this.root = root;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }


}
