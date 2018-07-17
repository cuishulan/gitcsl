package com.bjfdkj.singlecsl.utils;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 * Created by cuishu on 2018/6/21.
 * 检测Sd卡是否存在。
 */
public class SdCardExistUtil {

    File FILE_NAME;
    FileInputStream inputStream;
    //判断SD开 是否存在 ，如果没有Sd卡 ，ROM 被当成SD
    public static boolean isSdCardExist() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }
    //获取  SD卡 跟目录
    public static String getSdCardPath() {
        boolean exist = isSdCardExist();
        String sdpath = "";
        if (exist) {
            sdpath = Environment.getExternalStorageDirectory().getAbsolutePath();
        } else {
            sdpath = "没有获取到SD卡的根目录！";
        }
        return sdpath;  //得到返回路径
    }

    //获取默认文件路径
    public static String getDefaultFilePath() {
        String filepath = "";
        File file = new File(Environment.getExternalStorageDirectory(), "csl.txt");
        if (file.exists()) {  //如果文件存在
            filepath = file.getAbsolutePath(); //
        } else {
            filepath = "未找到sd默认文件存放路径";
        }
        return filepath;
    }

    //FileInputStream读取文件
    public void FileInputStreamDuqu() {
        //得到Sd卡目录
        //    String  sdCardRoot = Environment.getExternalStorageDirectory().getAbsolutePath();
        // 在sd卡 目录上新建文件
        try {
            File file = new File(Environment.getExternalStorageDirectory(), "csl.txt");
            FileInputStream is = new FileInputStream(file);
            byte[] b = new byte[inputStream.available()];
            is.read(b);
            String result = new String(b);
            System.out.println("读取成功：" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Read
    private String readSdUtil() {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File sdCardDir = Environment.getExternalStorageDirectory();
            try {
                //得到Sd卡对应存储目录
                FileInputStream fis = new FileInputStream(sdCardDir.getCanonicalPath() + FILE_NAME);
                //得到指定文件对应的输入流
                BufferedReader br = new BufferedReader(new InputStreamReader(fis));
                //将指定输入包装成 BufferedReader
                StringBuilder sb = new StringBuilder(" ");
                String line = null;
                while ((line = br.readLine()) != null) {  //循环读取   每一行
                    sb.append(line);
                }
               // Log.e("-----读取",sb.toString());
                return sb.toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
    //write
    private void writeSdUtil(String content) {
        try {
            //检测 是否插入Sd卡 ，应用程序具有访问权限
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //sd卡目录获取
                File sdCardDir = Environment.getExternalStorageDirectory();
                File targetFile = new File(sdCardDir.getCanonicalPath() + FILE_NAME);
                //指定文件创建
                RandomAccessFile raf = new RandomAccessFile(targetFile, "rw");
                raf.seek(targetFile.length());  //将文件 记录指针 移动到最后
                raf.write(content.getBytes());  //输出文件内容。
                raf.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //-------------自定义-----------

}
