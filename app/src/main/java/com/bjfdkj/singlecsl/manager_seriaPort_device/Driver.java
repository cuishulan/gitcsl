package com.bjfdkj.singlecsl.manager_seriaPort_device;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by cuishu on 2018/6/25.
 * <p>
 * 驱动
 */

public class Driver {

    private static final String TAG = Device.class.getSimpleName();
    private String mDrivateName;
    private String mDeviceRoot;  //Root权限

    public Driver(String name, String root) {
        mDrivateName = name;
        mDeviceRoot = root;
    }
    public ArrayList<File> getDevices() {
        ArrayList<File> devices = new ArrayList<>();
        File dev = new File("/dev");

        if (!dev.exists()) {
            Log.i(TAG, "getDeevices" + dev.getAbsolutePath() + "不存在");
            return devices;
        }
        if (!dev.canRead()) {
            Log.i(TAG, "getDevices" + dev.getAbsolutePath() + "没有读取权限");
            return devices;
        }
        File[] files =dev.listFiles();
        int i;
        for (i=0;i<files.length;i++)
        {
            if (files[i].getAbsolutePath().startsWith(mDeviceRoot))
            {
                Log.d(TAG,"---"+files[i]);
                devices.add(files[i]);
            }
        }
        return  devices;
    }
  public  String  getName()
  {
      return  mDrivateName;
  }
}
