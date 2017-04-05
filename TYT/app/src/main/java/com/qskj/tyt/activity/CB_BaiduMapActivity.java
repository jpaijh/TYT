package com.qskj.tyt.activity;

import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.model.LatLng;
import com.qskj.tyt.R;
import com.umeng.analytics.MobclickAgent;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

/**
 * 查看地图-界面
 */
@EActivity(R.layout.cb_activity_baidu_map)
public class CB_BaiduMapActivity extends BaseActivity {

    private static final String TAG = "CB_BaiduMapActivity";

    private BaiduMap mBaiduMap;

    @ViewById(R.id.toolbar)
    Toolbar mToolbar;

    @ViewById(R.id.tv_title)
    AppCompatTextView tv_title;

    @ViewById(R.id.bmapView)
    MapView mMapView;

    @Override
    public void onAfterViews() {
        initToolbar();
        initBaiduMap();
    }

    private void initToolbar() {
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        tv_title.setText("查看地图");
    }

    /**
     * 初始化百度地图
     */
    private void initBaiduMap() {
        // 设置是否显示缩放控件
        mMapView.showZoomControls(false);
        mBaiduMap = mMapView.getMap();
        // 设定地图中心点坐标:宁波天翔货柜有限公司(云台山路)
        LatLng point = new LatLng(29.882673, 121.894326);
        // 以动画方式更新地图状态，动画耗时默认 300 ms ,地图支持的最大最小级别分别为[3-21]
        mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newLatLngZoom(point, 18));
        // 定义Maker坐标点：宁波天翔货柜有限公司(云台山路)
        ArrayList<BitmapDescriptor> giflist = new ArrayList<>();
        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker));
        giflist.add(BitmapDescriptorFactory.fromResource(R.mipmap.ic_marker_light));
        // 通过marker的icons设置一组图片，再通过period设置多少帧刷新一次图片资源
        MarkerOptions mo = new MarkerOptions().position(point).icons(giflist).zIndex(0).period(20);
        // 设置生长动画/掉下动画
        // mo.animateType(MarkerOptions.MarkerAnimateType.drop);
        // 在地图上添加Marker，并显示
        mBaiduMap.addOverlay(mo);
    }

    /**
     * 放大地图 级别
     */
    @Click(R.id.iv_max)
    void max() {
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomIn();
        mBaiduMap.animateMapStatus(mapStatusUpdate);
    }

    /**
     * 缩小地图 级别
     */
    @Click(R.id.iv_min)
    void min() {
        MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.zoomOut();
        mBaiduMap.animateMapStatus(mapStatusUpdate);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理
        mMapView.onResume();
        MobclickAgent.onPageStart(TAG);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理
        mMapView.onPause();
        MobclickAgent.onPageEnd(TAG);
        MobclickAgent.onPause(this);
    }

    /**
     * 切换地图类型：这里做卫星地图和2D平面地图的切换
     */
    private void changeMapType() {
        if (mBaiduMap.getMapType() == BaiduMap.MAP_TYPE_NORMAL) {
            // 卫星地图
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
        } else {
            // 普通地图 (默认)
            mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_change, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_change: // 切换 2D平面图 和 卫星图
                changeMapType();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理
        mMapView.onDestroy();
    }

}
