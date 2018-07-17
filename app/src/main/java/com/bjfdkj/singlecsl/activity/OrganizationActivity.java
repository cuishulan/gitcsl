package com.bjfdkj.singlecsl.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;
import com.bjfdkj.singlecsl.db.bean.BookkeeperInfo;
import com.bjfdkj.singlecsl.db.utlis.BookkeeperDaoUtils;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*

维护管理-----》添加部门
 */
public class OrganizationActivity extends BaseActivity {

    @Bind(R.id.o_addUnit)
    EditText oAddUnit;
    @Bind(R.id.o_tncommit)
    Button   oTncommit;
    private BookkeeperDaoUtils bookkeeperDaoUtils = new BookkeeperDaoUtils(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organization);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.o_tncommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.o_tncommit:  //提交
                addUnit();
                break;
        }
    }

    /**
     * 给数据库添加单位
     */
    private void addUnit() {
        String trim = oAddUnit.getText().toString().trim();
        if (!TextUtils.isEmpty(trim)) {
            BookkeeperInfo bookkeeperInfo = new BookkeeperInfo();
            bookkeeperInfo.setZongbumen(trim);
            bookkeeperInfo.set_ID(null);
            bookkeeperInfo.setBookkeeperId((long) 1);
            bookkeeperDaoUtils.insertDao(bookkeeperInfo);
            Toast.makeText(this, "部门单位添加成功", Toast.LENGTH_SHORT).show();
        }
    }
}
