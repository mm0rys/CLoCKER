package com.example.iukong.clocker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.support.design.widget.BottomNavigationView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity{

    private DrawerLayout mDrawerLayout;
    private TextView mTextMessage;
    private ViewPager mViewPager;

    private LinearLayout mTabYingdi;
    private LinearLayout mTabQingdan;
    private LinearLayout mTabXiaozhen;

    private YingdiFragment mYingdi;
    private QingdanFragment mQingdan;
    private XiaozhenFragment mXiaozhen;

    private FragmentTransaction f;

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            f = getSupportFragmentManager().beginTransaction();
            mYingdi = new YingdiFragment();
            mQingdan = new QingdanFragment();
            mXiaozhen = new XiaozhenFragment();
            switch (item.getItemId()) {
                case R.id.navigation_yingdi:
                    f.replace(R.id.id_content,mYingdi);
                    f.commit();
                    return true;
                case R.id.navigation_qingdan:
                    f.replace(R.id.id_content,mQingdan);
                    f.commit();
                    return true;
                case R.id.navigation_town:
                    f.replace(R.id.id_content,mXiaozhen);
                    f.commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver = new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);

        //设置底部导航栏默认选择营地
        setDefaultFragment();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navgation_bottom);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //加载toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabYingdi = (LinearLayout) findViewById(R.id.tab_bottom_yingdi);
        mTabQingdan = (LinearLayout) findViewById(R.id.tab_bottom_qingdan);
        mTabXiaozhen = (LinearLayout) findViewById(R.id.tab_bottom_xiaozhen);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        //设定默认选择篝火
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        navView.setCheckedItem(R.id.nav_fire);
        //侧滑导航栏的业务处理
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                switch (item.getItemId()){
                    //添加navigation业务逻辑
                    case R.id.nav_fire:
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        break;
                    case R.id.nav_person:
                        startActivity(new Intent(MainActivity.this,Icon_Image.class));
                        break;
                default:
                }
                mDrawerLayout.closeDrawers();
                return true;
            }
        });
        //侧滑导航栏头像点击事件处理
        View navHeaderView = navView.getHeaderView(0);
        CircleImageView headerIcon = (CircleImageView) navHeaderView.findViewById(R.id.icon_image);
        headerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Icon_Image.class));
            }
        });





    }

    private void setDefaultFragment(){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        mYingdi = new YingdiFragment();
        transaction.replace(R.id.id_content,mYingdi);
        transaction.commit();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }
    //添加toolbar方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            //添加点击图标相应功能

            default:
        }
        return true;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }

    class NetworkChangeReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context,Intent intent){
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null && networkInfo.isAvailable()){
                Toast.makeText(context,"network is available",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(context,"network is unavailable",Toast.LENGTH_SHORT).show();
            }
        }
    }
}