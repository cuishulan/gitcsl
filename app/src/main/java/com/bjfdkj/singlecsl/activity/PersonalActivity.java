package com.bjfdkj.singlecsl.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 个人详细资料
 */
public class PersonalActivity extends BaseActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.tv_name)
    TextView tvName;
    @Bind(R.id.listview)
    ListView listview;
    private ArrayAdapter<String> adapter;
    private List<String> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_personal);
        ButterKnife.bind(this);
        init();
        initAdapter();
    }
    private void init() {
        //设置返回来的名字
        tvName.setText(getIntent().getStringExtra("tv_name"));
    }
    /**
     * 设置适配器
     */
    private void initAdapter() {
        list = new ArrayList<>();
        list.add("一.履历材料");
        list.add("二.自传材料");
        list.add("三.鉴定、考核、考察材料");
        list.add("四.学历、学位、职称等材料");
        list.add("五.政审材料");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        //        adapter.add("二.自传材料");
        //        adapter.add("三.鉴定、考核、考察材料");
        //        adapter.add("四.学历、学位、职称等材料");
        //        adapter.add("五.政审材料");
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(PersonalActivity.this, Personal_ImgActivity.class);
        intent.putExtra("tv_name", list.get(position) + "");
        startActivity(intent);
        overridePendingTransition(R.anim.bt_in, R.anim.bt_out);

        //适配  储存
    }
}
