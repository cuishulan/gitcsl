package com.bjfdkj.singlecsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.activity.function.LoginActivity;
import com.bjfdkj.singlecsl.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
* 指纹验证
* */
public class FingerprintActivity extends BaseActivity {
    @Bind(R.id.fingerprint_Btnback)
    Button fingerprintBtnback;
    @Bind(R.id.top_manage)
    LinearLayout topManage;
    @Bind(R.id.fingerprint_guide)
    ImageView fingerprintGuide;
    @Bind(R.id.fingerprint_guide_tip)
    TextView fingerprintGuideTip;
    @Bind(R.id.fingerprint_guide_tip1)
    TextView fingerprintGuideTip1;
    @Bind(R.id.fingerprint_recognition_start)
    Button fingerprintRecognitionStart;
    @Bind(R.id.fingerprint_recognition_cancel)
    Button fingerprintRecognitionCancel;
    @Bind(R.id.fingerprint_recognition_sys_unlock)
    Button fingerprintRecognitionSysUnlock;
    @Bind(R.id.fingerprint_recognition_sys_setting)
    Button fingerprintRecognitionSysSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.fingerprint_Btnback, R.id.top_manage, R.id.fingerprint_guide, R.id.fingerprint_guide_tip, R.id.fingerprint_guide_tip1, R.id.fingerprint_recognition_start, R.id.fingerprint_recognition_cancel, R.id.fingerprint_recognition_sys_unlock, R.id.fingerprint_recognition_sys_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fingerprint_Btnback:
                fingerprint_Btnback();
                break;
            case R.id.top_manage:
                break;
            case R.id.fingerprint_guide:
                break;
            case R.id.fingerprint_guide_tip:
                break;
            case R.id.fingerprint_guide_tip1:
                break;
            case R.id.fingerprint_recognition_start:
                break;
            case R.id.fingerprint_recognition_cancel:
                break;
            case R.id.fingerprint_recognition_sys_unlock:
                break;
            case R.id.fingerprint_recognition_sys_setting:
                break;
        }
    }
    //指纹返回
        public  void  fingerprint_Btnback()
    {
        Intent  intent=new Intent(FingerprintActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
