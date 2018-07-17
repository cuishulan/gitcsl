package com.bjfdkj.singlecsl.activity.function;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;


import com.bjfdkj.singlecsl.MainActivity;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.activity.PersonalActivity;
import com.bjfdkj.singlecsl.adapter.Bookkeeper_El_Adapter;
import com.bjfdkj.singlecsl.adapter.Bookkeeper_El_Adapter_s;
import com.bjfdkj.singlecsl.adapter.Info_Rv_Adapter;
import com.bjfdkj.singlecsl.base.BaseActivity;
import com.bjfdkj.singlecsl.db.bean.BookkeeperInfo;
import com.bjfdkj.singlecsl.db.bean.UserInfo;
import com.bjfdkj.singlecsl.db.utlis.BookkeeperDaoUtils;
import com.bjfdkj.singlecsl.db.utlis.UserDaoUtils;
import com.bjfdkj.singlecsl.model.BookkepperModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/*
 档案管理  侧滑栏  数据
 */
public class BookkeeperActivity extends BaseActivity implements View.OnClickListener, View.OnTouchListener, DrawerLayout.DrawerListener {
    private static final String TAG = "BookkeeperActivity";
    @Bind(R.id.mElistview)
    ExpandableListView mElistview;
    @Bind(R.id.mRecyview)
    RecyclerView mRecyview;
    @Bind(R.id.addData)
    Button addData;
    @Bind(R.id.bookkeeper_Btnback)
    Button bookkeeperBtnback;
    @Bind(R.id.textView)
    Button textView;
    @Bind(R.id.right)
    RelativeLayout right;
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;
    private RelativeLayout main_left_drawer_layout;
    private List<BookkepperModel.BookkeeperInfo> list = new ArrayList<>();
    private Bookkeeper_El_Adapter mAdapter; //组对象
    private Bookkeeper_El_Adapter_s mAdapter2;//租对象2
    private Info_Rv_Adapter mRvAdapter;// 展示数据
    private UserDaoUtils userDaoUtils = new UserDaoUtils(BookkeeperActivity.this);
    private BookkeeperDaoUtils bookkeeperDaoUtils = new BookkeeperDaoUtils(this);
    private List<UserInfo> userInfos;
    MainActivity mainActivity;
    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_bookkeeper);
        ButterKnife.bind(this);
        initLayout();
        initEvent();
        initData();
        initAdapter();
        initClickListenr();
    }
    private void initLayout() {
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        //设置菜单内容之外其他区域的背景色
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        //左边菜单
        main_left_drawer_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
    }
    //设置开关监听
    private void initEvent() {
        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, null, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerbar);
    }
    //左边菜单开关事件
    public void openLeftLayout() {
        if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
            drawerLayout.closeDrawer(main_left_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_left_drawer_layout);
        }
    }
    private void initClickListenr() {
        main_left_drawer_layout.setClickable(true);
        mElistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                List<BookkepperModel.BokkeeperInfo2> list = BookkeeperActivity.this.list.get(position).getList();
                for (int i = 0; i < list.size(); i++) {
                    BookkepperModel.BokkeeperInfo2 info2 = list.get(i);
                    List<BookkepperModel.BookkeeperInfo3> list2 = info2.getList2();
                    for (int j = 0; j < list2.size(); i++) {
                        BookkepperModel.BookkeeperInfo3 info3 = list2.get(j);
                        String name3 = info3.getName3();
                    }
                }
            }
        });
        bookkeeperBtnback.setOnClickListener(this);
        addData.setOnClickListener(this);
        textView.setOnClickListener(this);
        right.setOnTouchListener(this);
        drawerLayout.setDrawerListener(this);
    }

    private void initAdapter() {
        mAdapter = new Bookkeeper_El_Adapter(list, this);
        mElistview.setAdapter(mAdapter);  //添加适配器
        mElistview.expandGroup(0);  //默认组织机构管理展开
        //ge
        mRecyview.setLayoutManager(new LinearLayoutManager(BookkeeperActivity.this));
        mRecyview.addItemDecoration(new DividerItemDecoration(BookkeeperActivity.this, DividerItemDecoration.VERTICAL));
    }

    /**列表数据  可以写一个接口
     */
    @SuppressLint("WrongConstant")
    private void initData() {
        //左边的 扩展列表
        for (int m = 0; m < 1; m++) {
            BookkepperModel.BookkeeperInfo info = new BookkepperModel.BookkeeperInfo();
            info.setName("组织机构管理");
            List<BookkepperModel.BokkeeperInfo2> list2 = new ArrayList<>();
            //展示多少个组织单位
            List<BookkeeperInfo> bookkeeperInfos = bookkeeperDaoUtils.queryAllDao();
            if (bookkeeperInfos != null)
                for (int j = 0; j < bookkeeperInfos.size(); j++) {
                    BookkepperModel.BokkeeperInfo2 info2 = new BookkepperModel.BokkeeperInfo2();
                    String zongbumen = bookkeeperInfos.get(j).getZongbumen();
                    info2.setName2(zongbumen);
                    List<BookkepperModel.BookkeeperInfo3> list3 = new ArrayList<>();
                    //设置组织单位的人名
                    //                List<UserInfo> userInfos = bookkeeperInfos.get(j).getUserInfo();
                    for (int i = 0; i < 8; i++) {
                        BookkepperModel.BookkeeperInfo3 info3 = new BookkepperModel.BookkeeperInfo3();
                        //                    String username = userInfos.get(i).getUsername();
                        info3.setName3("周周" + i);
                        list3.add(info3);
                    }
                    info2.setList2(list3);
                    list2.add(info2);
                }
            info.setList(list2);
            list.add(info);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                hand.sendEmptyMessage(200);
            }
        }).start();
    }

    @SuppressLint("HandlerLeak")
    Handler hand = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 200) {
                userInfos = userDaoUtils.queryAllDao();
                //展示 查询后到数据
                if (userInfos != null) {
                    //设置适配器
                    mRvAdapter = new Info_Rv_Adapter(userInfos, BookkeeperActivity.this);
                }
                mRecyview.setAdapter(mRvAdapter);

                mRvAdapter.setOnClickL(new Info_Rv_Adapter.OnClickL() {
                    @Override
                    public void startItem(View view, int postion) {
                        Intent intent = new Intent(BookkeeperActivity.this, PersonalActivity.class);
                        intent.putExtra("tv_name", userInfos.get(postion).getUsername());
                        startActivity(intent);
                    }
                });
            } else if (msg.what == 300) {
                //暂时没用
            } else if (msg.what == 400) {
                if (userInfos != null) {
                    //设置适配器
                    mRvAdapter = new Info_Rv_Adapter(userInfos, BookkeeperActivity.this);
                }
                mRecyview.setAdapter(mRvAdapter);
                mRvAdapter.setOnClickL(new Info_Rv_Adapter.OnClickL() {
                    @Override
                    public void startItem(View view, int postion) {
                        Intent intent = new Intent(BookkeeperActivity.this, PersonalActivity.class);
                        intent.putExtra("tv_name", userInfos.get(postion).getUsername());
                        startActivity(intent);
                    }
                });
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addData:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        List<UserInfo> userInfoInstert = new ArrayList<>();  //添加数据
                        for (int i = userInfos.size(); i < userInfos.size() + 10; i++) {
                            UserInfo userInfo = new UserInfo
                                    (null, "测试张" + (i + 1) + "号", "干部管理部" + (i + 1)
                                            + "号", "男" + (i + 1) + "号", "汉族" + (i + 1) +
                                            "号", "运维" + (i + 1) + "号", (long) (i + 1));
                            userInfoInstert.add(userInfo);
                        }
                        userDaoUtils.insertMultDao(userInfoInstert);
                        userInfos = userDaoUtils.queryAllDao();
                        Log.e(TAG, "zlll" + userDaoUtils.queryAllDao().size());
                        hand.sendEmptyMessage(400);
                    }
                }).start();
                break;
            case R.id.bookkeeper_Btnback:
                Intent intent = new Intent(BookkeeperActivity.this, MainActivity.class);
                startActivity(intent);
                break;
            case R.id.textView:
                openLeftLayout();
                break;
        }
    }

    private boolean isDrawer = false;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (isDrawer) {
            return main_left_drawer_layout.dispatchTouchEvent(event);
        } else {
            return false;
        }
    }
    @Override
    public void onDrawerSlide(View drawerView, float slideOffset) {
        isDrawer = true;
        //获取屏幕的宽高
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        //设置右面的布局位置 根据左面菜单的right作为右面布局的left 左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
        right.layout(main_left_drawer_layout.getRight(), 0, main_left_drawer_layout.getRight() + display.getWidth(), display.getHeight());
    }

    @Override
    public void onDrawerOpened(View drawerView) {
        drawerView.setClickable(true);
    }

    @Override
    public void onDrawerClosed(View drawerView) {
        isDrawer = false;
    }

    @Override
    public void onDrawerStateChanged(int newState) {
    }

    /**
     * 《当用户按下了"手机上的返回功能按键"的时候会回调这个方法》
     */
    @Override
    public void onBackPressed() {
        boolean drawerState = drawerLayout.isDrawerOpen(main_left_drawer_layout);
        if (drawerState) {
            drawerLayout.closeDrawers();
            return;
        }
        //也就是说，当按下返回功能键的时候，不是直接对Activity进行弹栈，而是先将菜单视图关闭
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }


}