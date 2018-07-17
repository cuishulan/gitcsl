package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.activity.FingerprintActivity;
import com.bjfdkj.singlecsl.activity.UserRegistrationActivity;
import com.bjfdkj.singlecsl.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
  登录login
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.mName)
    EditText mName;
    @Bind(R.id.mPassword)
    EditText mPassword;
    @Bind(R.id.mLogin) // 账号密码登录
            Button mLogin;
    @Bind(R.id.mFingerprint) //指纹登录
            Button mFingerprint;
    @Bind(R.id.mUserregistration) //用户第一次登记
            Button mUserregistration;

    //相机
    @Bind(R.id.mCamera)
    Button mCamera;


    @Bind(R.id.Login_index)
    TextView LoginIndex;

    Button MyVideoCamera;

    //录像
   /* @Bind(R.id.MyVideoCamera)
    Button MyVideoCamera;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);  //禁止  EditView   调用键盘
        initClickListenr();
        MyVideoCamera=findViewById(R.id.MyVideoCamera);
        MyVideoCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CamearVideoActivity.class);
                startActivity(intent);
                Log.e("----------------", "跳转录像");
            }
        });


    }

    private void initClickListenr() {
        mLogin.setOnClickListener(this);
        mFingerprint.setOnClickListener(this);
        mUserregistration.setOnClickListener(this);
        //   MyVideoCamera.setOnClickListener(this);
    }

    @OnClick({R.id.mCamera, R.id.MyVideoCamera})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLogin:
                login();
                break;
            case R.id.mFingerprint:
                fingerprint();
                break;
            case R.id.mUserregistration:
                userregistration();
                break;
            case R.id.mCamera:
                //开始拍照
                Intent intents = new Intent(LoginActivity.this, CameraActivity.class);
                startActivity(intents);
                break;
            /*   case R.id.MyVideoCamera:
           //开始拍照
                Intent intent = new Intent(LoginActivity.this, CamearVideoActivity.class);
                startActivity(intent);
                Log.e("----------------", "跳转录像");
                break;*/
        }
    }

    private void userregistration() {
        Intent intent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
        startActivity(intent);
    }

    private void fingerprint() {
        Intent intent = new Intent(LoginActivity.this, FingerprintActivity.class);
        startActivity(intent);
    }


    /**
     * 登录档案系统
     */
    private void login() {
        String name = mName.getText().toString();
        String password = mPassword.getText().toString();
        if (name == null && name.equals("")) {
            return;
        } else {
            if (name.equals("123") && password.equals("123")) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                this.finish();
            }
        }
    }

    //
    @Override
    protected void onStop() {
        super.onStop();
    }
//
//    @OnClick()
//    public void onViewClicked() {
//        Intent intent = new Intent(LoginActivity.this, CamearVideoActivity.class);
//        startActivity(intent);
//
//    }
}
