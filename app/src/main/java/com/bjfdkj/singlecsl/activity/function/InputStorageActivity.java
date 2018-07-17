package com.bjfdkj.singlecsl.activity.function;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

//入库  Input_Storage

public class InputStorageActivity extends BaseActivity {

    @Bind(R.id.input_storage_Btnback)
    Button inputStorageBtnback;  //返回
    @Bind(R.id.input_storage_btnReset)
    Button inputStorageBtnReset; //重置
    @Bind(R.id.input_storage_btncommit)
    Button inputStorageBtncommit; //  完成提交
    @Bind(R.id.input_storge_file) //上传文件
            Button inputstorgeFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_input_storage);
        ButterKnife.bind(this);
    }

    //初始化 点击按钮  控件  id  名称      返回 上一步  重新设置  提交  文件。
    @OnClick({R.id.input_storage_Btnback, R.id.input_storage_btnReset, R.id.input_storage_btncommit, R.id.input_storge_file})
    public void onViewClicked(View view) {
        //开始根据  id 选择
        switch (view.getId()) {
            case R.id.input_storage_Btnback:
                Intent intent = new Intent(InputStorageActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.input_storage_btnReset:
                //清空所有 EditText    重新传入照片
                break;
            case R.id.input_storage_btncommit:
                //完成入库 信息填写  提交
                //判断填写信息。  提交成功  开柜操作。
                Toast.makeText(InputStorageActivity.this, "提交入库信息 通过！，进行下一步开柜存入实体档案",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.input_storge_file:
                showCustomDialog();
                break;
        }
    }
    //上传文件Dialog
    private void showCustomDialog() {
        //获取自定义AlertDialog布局文件的view
        View change_name = getLayoutInflater()
                .inflate(R.layout.dialog_input_storge_file, null);
//        TextView tv_name_dialog = (TextView) change_name.findViewById(R.id.tv_name_dialog);
        //由于EditText要在内部类中对其进行操作，所以要加上final
//        final EditText et_name_dialog = (EditText) change_name.findViewById(R.id.et_name_dialog);

        //设置AlertDialog中TextView和EditText显示Activity中TextView的内容
//        tv_name_dialog.setText(old_name.getText().toString());
//        et_name_dialog.setText(old_name.getText().toString());
        new AlertDialog.Builder(InputStorageActivity.this)
                .setTitle("上传文件资料")
                .setView(change_name)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //将Activity中的textview显示AlertDialog中EditText中的内容
                        //并且用Toast显示一下
//                        old_name.setText(et_name_dialog.getText().toString());
                        Toast.makeText(InputStorageActivity.this, "设置成功！", Toast.LENGTH_SHORT).show();
                    }
                })
                //由于“取消”的button我们没有设置点击效果，直接设为null就 可以了
                .setNegativeButton("取消", null)  //获取
                .create()
                .show();
    }
    //-----------------------------------------
    public void setInputStorageBtnback() {
        final Handler handler = new Handler() {
            @Override
            public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
                return super.sendMessageAtTime(msg, uptimeMillis);
                //开始效验
            }
        };
//开启  线程
        Thread thread = new Thread() {
            @Override
            public int countStackFrames() {
                return super.countStackFrames();
            }

            @Override
            public void run() {
                super.run();
            }
        };
    }
}
