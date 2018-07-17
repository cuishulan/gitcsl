package com.bjfdkj.singlecsl.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;
import com.bjfdkj.singlecsl.db.bean.UserInfo;
import com.bjfdkj.singlecsl.db.utlis.UserDaoUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*

维护管理-----》添加档案人员
 */
public class Personnel_FileActivity extends BaseActivity {


    @Bind(R.id.pf_name) //姓名
            EditText pfName;
    @Bind(R.id.pf_sex) //性别
            EditText pfSex;
    @Bind(R.id.pf_duty)//职务名称
            EditText pfDuty;
    @Bind(R.id.pf_unit)//单位名称
            EditText pfUnit;
    @Bind(R.id.pf_nation)// 民族名称
            EditText pfNation;
    @Bind(R.id.input_storage_Btnback)
            Button   inputStorageBtnback;
    @Bind(R.id.pf_btnReset) //重新输入
            Button   pfBtnReset;
    @Bind(R.id.pf_btncommit)//重新提交
            Button   pfBtncommit;

    private UserDaoUtils userDaoUtils = new UserDaoUtils(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personnel_file);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.pf_btnReset, R.id.pf_btncommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pf_btnReset:
                pfName.setText("");
                pfSex.setText("");
                pfDuty.setText("");
                pfUnit.setText("");
                pfNation.setText("");
                break;
            case R.id.pf_btncommit:
                String name = pfName.getText().toString().trim();
                String sex = pfSex.getText().toString().trim();
                String duty = pfDuty.getText().toString().trim();
                String unit = pfUnit.getText().toString().trim();
                String nation = pfNation.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(sex) && !TextUtils.isEmpty(duty) && !TextUtils.isEmpty(unit) && !TextUtils.isEmpty(nation)) {
                    UserInfo userInfo = new UserInfo();
                    userInfo.set_ID(null);
                    userInfo.setUsername(name);
                    userInfo.setUsersex(sex);
                    userInfo.setUserduty(duty);
                    userInfo.setUserunit(unit);
                    userInfo.setUsernation(nation);
                    userInfo.setUserId((long) 1);
                    userDaoUtils.insertDao(userInfo);
                    Toast.makeText(this, name + "已经添加到档案人员管理", Toast.LENGTH_SHORT).show();
                } else {
                    return;
                }
                break;
        }
    }
}
