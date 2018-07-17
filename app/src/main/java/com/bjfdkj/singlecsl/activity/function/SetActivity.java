package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;
import butterknife.Bind;
import butterknife.ButterKnife;


/*
系统设置   修改界面
 */
public class SetActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.set_Btnback)
    Button setBtnback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        initOnclick();
    }
    /**
     * 初始化点击事件
     */
    private void initOnclick() {
        setBtnback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_Btnback:
                Intent intent = new Intent(SetActivity.this, MainActivity.class);
                this.finish();
                startActivity(intent);
                break;
        }
    }
}
