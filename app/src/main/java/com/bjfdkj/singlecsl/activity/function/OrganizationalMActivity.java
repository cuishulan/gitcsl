package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.activity.OrganizationActivity;
import com.bjfdkj.singlecsl.activity.Personnel_FileActivity;
import com.bjfdkj.singlecsl.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
 * 管理维护
 */
public class OrganizationalMActivity extends BaseActivity {

    @Bind(R.id.om_tv)
    TextView omTv;
    @Bind(R.id.query_Btnback)
    Button queryBtnback;
    @Bind(R.id.om_tv2)
    TextView omTv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizational_m);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.query_Btnback, R.id.om_tv, R.id.om_tv2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query_Btnback:
                Intent intent = new Intent(OrganizationalMActivity.this, MainActivity.class);
                this.finish();
                startActivity(intent);
                break;
            case R.id.om_tv:
                startActivity(new Intent(OrganizationalMActivity.this, OrganizationActivity.class));
                break;
            case R.id.om_tv2:
                startActivity(new Intent(OrganizationalMActivity.this, Personnel_FileActivity.class));
                break;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onRestart() {
        super.onRestart();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }



}