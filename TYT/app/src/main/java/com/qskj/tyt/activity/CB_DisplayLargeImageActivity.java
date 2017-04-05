package com.qskj.tyt.activity;

import com.qskj.tyt.R;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 查看大图:大图可以缩放
 */
@EActivity(R.layout.cb_activity_display_large_image)
public class CB_DisplayLargeImageActivity extends BaseActivity {

    private static final String TAG = "CB_DisplayLargeImageActivity";

    @ViewById(R.id.photo_view)
    PhotoView photo_view;

    @Override
    public void onAfterViews() {
        photo_view.setImageResource(R.mipmap.warehouse_location);
        PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(photo_view);
        photoViewAttacher.update();
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
