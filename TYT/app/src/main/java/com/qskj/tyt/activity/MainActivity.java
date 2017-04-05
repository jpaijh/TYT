package com.qskj.tyt.activity;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.MyApplication;
import com.qskj.tyt.MyApplication_;
import com.qskj.tyt.R;
import com.qskj.tyt.adapter.TabHostAdapter;
import com.qskj.tyt.fragment.HomeFragment_;
import com.qskj.tyt.fragment.MineFragment_;
import com.qskj.tyt.fragment.T_OrderFormFragment_;
import com.qskj.tyt.fragment.ToolsFragment_;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * 天易通-主界面
 */
@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    private int platFormIntKey;
    private long mPressedTime = 0;
    private MenuItem action_search;
    private int[] rbIds_T = {R.id.rb_home, R.id.rb_orderForm, R.id.rb_tools, R.id.rb_mine};
    private int[] rbIds_CB = {R.id.rb_home, R.id.rb_tools, R.id.rb_mine};

    @ViewById(R.id.viewpager)
    ViewPager mViewpager;

    @ViewById(R.id.radio_group)
    RadioGroup mRadioGroup;

    @ViewById(R.id.rb_orderForm)
    RadioButton rb_orderForm;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    private void initUmeng() {
        /**
         * 设置是否增量更新：默认False
         */
        UmengUpdateAgent.setDeltaUpdate(true);
        /**
         * 静默下载更新
         */
//        UmengUpdateAgent.silentUpdate(this);
        /**
         * 设置所有网络状态都进行检查更新，默认是WIFE
         */
        UmengUpdateAgent.setUpdateOnlyWifi(false);
        /**
         *  友盟：检查版本更新
         */
        UmengUpdateAgent.update(this);
        /**
         * 自定义更新提示：这里是为了防止退出登录后，再次登陆会弹出 “当前已是最新版本”
         */
        UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                switch (updateStatus) {
                    case UpdateStatus.Yes: // has update
                        UmengUpdateAgent.showUpdateDialog(MainActivity.this, updateInfo);
                        break;
                    case UpdateStatus.No: // has no update
//                        ToastUtil.showToast(MainActivity.this, "当前已是最新版本");
                        break;
                    case UpdateStatus.NoneWifi: // none wifi
                        // 注意：我已经在MainActivity中设置，更新时忽略只在WIFE下更新，所以这个状态暂时没用
//                        ToastUtil.showToast(MainActivity.this, "没有wifi连接，只在wifi下更新");
                        break;
                    case UpdateStatus.Timeout: // time out
//                        ToastUtil.showToast(MainActivity.this, "请求超时，请检查网络");
                        break;
                }
            }
        });

    }

    @Override
    public void onAfterViews() {
        MyApplication_.getInstance().exit(); // 退出Loading和Login界面
        MyApplication_.getInstance().addActivity(this);
        initUmeng();
        initToolbar();
        platFormIntKey = MyApplication_.getInstance().getPlatFormIntKey();
        initViewpager();
    }

    private void initViewpager() {
        ArrayList<Fragment> fragmentList = new ArrayList<>();

        if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
            showView(rb_orderForm);
            fragmentList.add(new HomeFragment_());
            fragmentList.add(new T_OrderFormFragment_());
            fragmentList.add(new ToolsFragment_());
            fragmentList.add(new MineFragment_());
            mViewpager.setOffscreenPageLimit(3);
        } else if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
            fragmentList.add(new HomeFragment_());
            fragmentList.add(new ToolsFragment_());
            fragmentList.add(new MineFragment_());
            mViewpager.setOffscreenPageLimit(2);
        }

        mViewpager.setAdapter(new TabHostAdapter(getSupportFragmentManager(), fragmentList));
        mViewpager.setOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
                    mRadioGroup.check(rbIds_T[position]);
                } else if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
                    mRadioGroup.check(rbIds_CB[position]);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home: // 首页
                        if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
                            hideView(mToolbar);
                            hideActionSearch();
                        } else if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
                            showView(mToolbar);
                            tv_title.setText(R.string.home);
                        }
                        mViewpager.setCurrentItem(0);
                        break;
                    case R.id.rb_orderForm: // 订单
                        showView(mToolbar);
                        tv_title.setText(R.string.orderform);
                        if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
                            mViewpager.setCurrentItem(1);
                            showActionSearch();
                        }
                        break;
                    case R.id.rb_tools: // 工具
                        showView(mToolbar);
                        tv_title.setText(R.string.tools);
                        if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
                            mViewpager.setCurrentItem(2);
                            hideActionSearch();
                        } else if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
                            mViewpager.setCurrentItem(1);
                        }
                        break;
                    case R.id.rb_mine: // 我的
                        showView(mToolbar);
                        tv_title.setText(MyApplication_.getInstance().getPlatFormString());
                        if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
                            mViewpager.setCurrentItem(3);
                            hideActionSearch();
                        } else if (platFormIntKey == AppDelegate.PLATFORM_CROSS_BORDER) {
                            mViewpager.setCurrentItem(2);
                        }
                        break;
                }
            }
        });

        mRadioGroup.check(R.id.rb_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (platFormIntKey == AppDelegate.PLATFORM_TRADING) {
            getMenuInflater().inflate(R.menu.menu_search, menu);
            action_search = menu.findItem(R.id.action_search);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search: // 进入订单搜索界面
                T_SearchOrderFormActivity_.intent(this).start();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showActionSearch() {
        if (action_search != null)
            action_search.setVisible(true);
    }

    private void hideActionSearch() {
        if (action_search != null)
            action_search.setVisible(false);
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    /**
     * 双击返回键退出
     */
    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();//获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) { // 比较两次按键时间差（2秒 之内双击就退出应用）
            Toast.makeText(this, "再按一次退出" + getStrings(R.string.app_name), Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else { //退出程序
            finish();
            MobclickAgent.onKillProcess(this);
            System.exit(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

}