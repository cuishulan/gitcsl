package com.bjfdkj.singlecsl.manager_seriaPort_device;

import android.util.Log;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;

/**
 * Created by cuishu on 2018/6/25.
 * 串口访达  代码集成
 */
public class SerialPortFinder {
    private static final String TAG = SerialPortFinder.class.getSimpleName();

    private static final String DRIVERS_PATH = "/proc/tty/drivers";  //访达地址
    private static final String SERIAL_FIELD = "serial";
    public SerialPortFinder() {
        File file = new File(DRIVERS_PATH);
        boolean b = file.canRead();
        Log.i(TAG, "SerialPortFinder: file.canRead() = " + b);
    }
    //获取 devices
    private ArrayList<Driver> getDrivers() throws IOException {
        ArrayList<Driver> drivers = new ArrayList<>();
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader(DRIVERS_PATH));
        String readLine;
        while ((readLine = lineNumberReader.readLine()) != null) {
            String drivateName =
                    readLine.substring(0, 0x15).trim();
            String[] fields = readLine.split(" +");
            if ((fields.length >= 5) &&
                    (fields[fields.length - 1].equals(SERIAL_FIELD))) {
                drivers.add(new Driver(drivateName, fields[fields.length - 4]));
                Log.e(TAG, "找到了新的驱动程序" + drivateName + "没有" +
                        fields[fields.length - 4]);
            }
        }
        return drivers;
    }
    //    得到串口
    public ArrayList<Device> getDevices() {
        ArrayList<Device> devices = new ArrayList<>();
        try {
            ArrayList<Driver> drivers = getDrivers();
            for (Driver driver : drivers)
            {
                String driverName = driver.getName();
                ArrayList<File> driverDevices = driver.getDevices();
                for (File file : driverDevices) {
                    String devicesName = file.getName();
                    devices.add(new Device(devicesName, driverName, file));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return devices;
    }
}
