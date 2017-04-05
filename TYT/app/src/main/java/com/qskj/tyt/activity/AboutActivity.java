package com.qskj.tyt.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.qskj.tyt.R;
import com.qskj.tyt.utils.DeviceUtil;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * 关于界面
 */
@EActivity(R.layout.activity_about)
public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.tv_versionName)
    AppCompatTextView tv_versionName;

    @Override
    public void onAfterViews() {
        initToolbar();
        tv_versionName.setText(DeviceUtil.getVersionName(this));
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText(R.string.title_activity_about);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

}
