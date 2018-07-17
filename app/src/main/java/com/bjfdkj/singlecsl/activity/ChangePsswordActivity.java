package com.bjfdkj.singlecsl.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bjfdkj.singlecsl.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 *
 * 修改密码
 */
public class ChangePsswordActivity extends AppCompatActivity {
    @Bind(R.id.activity_change_pwd_back)
    Button activityChangePwdBack;
    @Bind(R.id.input_storage_text)
    TextView inputStorageText;
    @Bind(R.id.top_manage_setPwd)
    LinearLayout topManageSetPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pssword);
        ButterKnife.bind(this);
    }

    //修改密码
    @OnClick({R.id.activity_change_pwd_back, R.id.input_storage_text, R.id.top_manage_setPwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.activity_change_pwd_back:
                break;
            case R.id.input_storage_text:
                break;
            case R.id.top_manage_setPwd:
                break;
        }
    }
}
