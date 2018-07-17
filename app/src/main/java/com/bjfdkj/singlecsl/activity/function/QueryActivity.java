package com.bjfdkj.singlecsl.activity.function;

import android.content.Intent;
import android.os.Bundle;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.activity.PersonalActivity;
import com.bjfdkj.singlecsl.adapter.Info_Rv_Adapter;
import com.bjfdkj.singlecsl.db.bean.UserInfo;
import com.bjfdkj.singlecsl.db.utlis.UserDaoUtils;
import com.bjfdkj.singlecsl.utils.TUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/*
查询  QueryActivity
只需要查询  想要的个人信息  然后 查看 档案文件  ，不做任何操作
 */
public class QueryActivity extends AppCompatActivity {
    @Bind(R.id.query_Btnback)
    Button queryBtnback;
    @Bind(R.id.query_select_text)
    TextView querySelectText;
    @Bind(R.id.top_manage)
    LinearLayout topManage;
    @Bind(R.id.querySelecteditText)
    TextInputEditText querySelecteditText;
    @Bind(R.id.querycommit)
    Button querycommit;
    UserDaoUtils userDaoUtils = new UserDaoUtils(this);
    @Bind(R.id.query_list)
    RecyclerView queryList;
    private Info_Rv_Adapter mRvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_query);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.query_Btnback, R.id.query_select_text, R.id.top_manage, R.id.querySelecteditText, R.id.querycommit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.query_Btnback:
                QueryBlackBtn();
                break;
            case R.id.top_manage:
                break;
            case R.id.querySelecteditText:
                break;
            case R.id.querycommit:
                //输入完成
                SystemoEditViewCommit();
                break;
        }
    }

    //搜索输入完成
    private void SystemoEditViewCommit() {
        //获取文本输入
        String queryEditText = querySelecteditText.getText().toString().trim();
        if (TextUtils.isEmpty(queryEditText)) {
            if (TUtils.isShow) {
                TUtils.showShort(this, "您输入为空");
            }
            return;
        }
        final List<UserInfo> userInfos = userDaoUtils.queryDaoByQueryBuilder(queryEditText);
        queryList.setLayoutManager(new LinearLayoutManager(QueryActivity.this));
        queryList.addItemDecoration(new DividerItemDecoration(QueryActivity.this, DividerItemDecoration.VERTICAL));
        //展示 查询后到数据
        if (userInfos != null) {
            //设置适配器
            mRvAdapter = new Info_Rv_Adapter(userInfos, QueryActivity.this);
        }
        queryList.setAdapter(mRvAdapter);
        mRvAdapter.setOnClickL(new Info_Rv_Adapter.OnClickL() {
            @Override
            public void startItem(View view, int postion) {
                Intent intent = new Intent(QueryActivity.this, PersonalActivity.class);
                intent.putExtra("tv_name", userInfos.get(postion).getUsername());
                startActivity(intent);
            }
        });
    }

    //返回上一步
    private void QueryBlackBtn() {
        Intent intent = new Intent(QueryActivity.this, MainActivity.class);
        startActivity(intent);
    }


    //开始
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

