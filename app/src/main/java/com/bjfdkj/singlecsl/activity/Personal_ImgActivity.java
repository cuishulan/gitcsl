package com.bjfdkj.singlecsl.activity;

import android.os.Bundle;
import android.widget.TextView;
import com.bjfdkj.singlecsl.R;
import com.bjfdkj.singlecsl.base.BaseActivity;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;



import butterknife.Bind;
import butterknife.ButterKnife;


/*
* 修改  个人详细资料
*
* */
public class Personal_ImgActivity extends BaseActivity {
    @Bind(R.id.imageView)
    SubsamplingScaleImageView imageView;
    @Bind(R.id.tv_name)
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persional_img);
        ButterKnife.bind(this);
        init();
        initSubImageview();
    }

    private void init() {
        //  设置返回来的资料名称
        tvName.setText(getIntent().getStringExtra("tv_name"));

    }

    private void initSubImageview() {

        imageView.setImage(ImageSource.resource(R.mipmap.ziliao1));

    }
}
