package com.yonyou.ceshi.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yonyou.ceshi.BaseActivity;
import com.yonyou.ceshi.R;

public class MyFragementShowAndHideActivity extends BaseActivity implements View.OnClickListener {

    private Button button1;
    private Button button2;
    // fragment对象的标记
    public static final String fragmentTag1 = "fragment1";
    public static final String fragmentTag2 = "fragment2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_fragement_show_and_hide);
        initView();
        myOnclick();
    }
    private void initView() {
        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        firstShow();
    }

    private void myOnclick() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//      用对应的标记在内存中找对应的Fragment对象
        Fragment blankFragment1 =  fragmentManager.findFragmentByTag(fragmentTag1);
        Fragment  blankFragment2 =  fragmentManager.findFragmentByTag(fragmentTag2);

        switch (v.getId()) {
            case R.id.button1:
                fragmentTransaction.show(blankFragment1);
                fragmentTransaction.hide(blankFragment2);
                fragmentTransaction.commit();
                break;

            case R.id.button2:
                fragmentTransaction.hide(blankFragment1);
                fragmentTransaction.show(blankFragment2);
                fragmentTransaction.commit();
                break;
        }
    }

    /**
     * 初始化时显示的fragment
     */
    private void firstShow(){
//      实例化碎片对象
        BlankFragment1 blankFragment1 = new BlankFragment1();
        FragmentManager fragmentManager1 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
//      添加Fragment的时候，要传入标记
        fragmentTransaction1.add(R.id.llContent,blankFragment1,fragmentTag1).commit();

//      放对象
        BlankFragment2 blankFragment2 = new BlankFragment2();
        FragmentManager fragmentManager2 = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction2 =fragmentManager2.beginTransaction();
//      添加Fragment的时候，要传入标记
        fragmentTransaction2.add(R.id.llContent,blankFragment2,fragmentTag2).commit();
        fragmentTransaction2.hide(blankFragment2);
    }

}