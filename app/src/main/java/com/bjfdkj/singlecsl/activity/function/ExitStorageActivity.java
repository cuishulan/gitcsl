package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;
import com.vondear.rxtools.view.RxToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*
* 出库
* */
public class ExitStorageActivity extends BaseActivity {

    @Bind(R.id.tv_exit_name) //搜索结果列表
            TextView tvExitName;
    @Bind(R.id.query_Btnback)//返回按钮
            Button   queryBtnback;
    @Bind(R.id.exit_storge_name)//搜索输入框
            EditText exitStorgeName;
    @Bind(R.id.querycommit)//提交
            Button   querycommit;
    @Bind(R.id.exit_storge_flag)
            CheckBox exitStorgeFlag;
    private boolean flag = false;
    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_exit_storage);
        ButterKnife.bind(this);
    }
    private void ExitStorageBlackBtn() {
        Intent intent = new Intent(ExitStorageActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @OnClick({R.id.query_Btnback, R.id.querycommit, R.id.exit_storge_flag})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query_Btnback:
                ExitStorageBlackBtn();
                break;
            case R.id.querycommit:
                result = exitStorgeName.getText().toString().trim();
                if (TextUtils.isEmpty(result)) {
                    exitStorgeFlag.setVisibility(View.INVISIBLE);
                } else {
                    exitStorgeFlag.setVisibility(View.VISIBLE);
                }
                tvExitName.setText(result);
                break;
            case R.id.exit_storge_flag:
                if (flag == false) {
                    flag = true;
                    RxToast.showToast(ExitStorageActivity.this, "选中状态", Toast.LENGTH_LONG);
                } else {
                    flag = false;
                    RxToast.showToast(ExitStorageActivity.this, "未选中状态", Toast.LENGTH_LONG);
                }
                break;
        }
    }
}
