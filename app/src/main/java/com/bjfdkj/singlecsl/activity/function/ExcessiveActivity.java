package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;


/*
    过渡
 */
public class ExcessiveActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excessive);
    }

    public void onClick(View view) {
        startActivity(new Intent(ExcessiveActivity.this, LoginActivity.class));
    }
    @Override
    //停止
    protected void onStop() {
        super.onStop();
        ExcessiveActivity.this.finish();

    }
}
