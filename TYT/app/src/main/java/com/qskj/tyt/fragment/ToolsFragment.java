package com.qskj.tyt.fragment;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qskj.tyt.AppDelegate;
import com.qskj.tyt.R;
import com.qskj.tyt.activity.CB_WebActivity_;
import com.umeng.analytics.MobclickAgent;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

/**
 * 工具
 */
@EFragment(R.layout.recyclerview)
public class ToolsFragment extends BaseFragment {

    private static final String TAG = "ToolsFragment";

    private MyToolsFragmentAdapter mAdapter;

    @ViewById(R.id.recyclerview)
    RecyclerView mRecyclerView;

    @Override
    public void onAfterViews() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .marginResId(R.dimen.divider_margin_left, R.dimen.divider_margin_right) // 设置分割线距离两端距离
//                .color(Color.RED)  // 设置分割线颜色
//                .sizeResId(R.dimen.divider) // 设置分割线粗细
//                .drawable(R.drawable.abc_list_pressed_holo_light) // 设置分割线背景
                .build());

        List<ItemEntity> list = new ArrayList<>();
        list.add(new ItemEntity(R.string.tools_check_exchange_rate, R.mipmap.ic_check_exchange_rate));
        list.add(new ItemEntity(R.string.tools_check_hs_code, R.mipmap.ic_check_hs_code));
        list.add(new ItemEntity(R.string.tools_declare_factor, R.mipmap.ic_declare_factor));
        list.add(new ItemEntity(R.string.tools_check_tax_refund_rate, R.mipmap.ic_check_tax_refund_rate));
        list.add(new ItemEntity(R.string.tools_check_logistics, R.mipmap.ic_check_logistics));

        if (mAdapter == null)
            mAdapter = new MyToolsFragmentAdapter(list);
        mRecyclerView.setAdapter(mAdapter);

    }

    private class MyToolsFragmentAdapter extends RecyclerView.Adapter<MyToolsFragmentAdapter.ViewHolder> {

        private List<ItemEntity> list;

        public MyToolsFragmentAdapter(List<ItemEntity> list) {
            this.list = list;
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_tools_tv_with_icon, viewGroup, false);
            return new ViewHolder(view);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            public AppCompatTextView tv_text;
            public AppCompatImageView img_icon;

            public ViewHolder(View itemView) {
                super(itemView);
                tv_text = (AppCompatTextView) itemView.findViewById(R.id.tv_text);
                img_icon = (AppCompatImageView) itemView.findViewById(R.id.img_icon);

                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ItemEntity itemEntity = list.get(getLayoutPosition());
                        int textRes = itemEntity.getTextRes();
                        switch (textRes) {
                            case R.string.tools_check_exchange_rate: // 汇率查询
                                CB_WebActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.tools_check_exchange_rate))
                                        .extra(AppDelegate.URL, "http://www.boc.cn/sourcedb/whpj/").start();
                                break;
                            case R.string.tools_check_hs_code: // HS编码查询
                                CB_WebActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.tools_check_hs_code))
                                        .extra(AppDelegate.URL, "http://www.haiguan.info/onlinesearch/gateway/ProductInfo.aspx").start();
                                break;
                            case R.string.tools_declare_factor: // 申报要素
                                CB_WebActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.tools_declare_factor))
                                        .extra(AppDelegate.URL, "http://www.hscode.net/IntegrateQueries/QueryYS/?q1=36269090&q2=#").start();
                                break;
                            case R.string.tools_check_tax_refund_rate: // 退税率查询
                                CB_WebActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.tools_check_tax_refund_rate))
                                        .extra(AppDelegate.URL, "http://www.taxrefund.com.cn/").start();
                                break;
                            case R.string.tools_check_logistics: // 物流查询
                                CB_WebActivity_.intent(getActivity()).extra(AppDelegate.TOOLBAR_TITLE, getStrings(R.string.tools_check_logistics))
                                        .extra(AppDelegate.URL, "http://www.likecha.com/tools/shipDynamics.html").start();
                                break;
                        }

                    }
                });
            }
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, int position) {
            viewHolder.tv_text.setText(list.get(position).getTextRes());
            viewHolder.img_icon.setBackgroundResource(list.get(position).getImgRes());
        }

    }

    private class ItemEntity {

        public ItemEntity(int textRes, int imgRes) {
            this.textRes = textRes;
            this.imgRes = imgRes;
        }

        private int textRes;
        private int imgRes;

        public int getImgRes() {
            return imgRes;
        }

        public int getTextRes() {
            return textRes;
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart(TAG);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd(TAG);
    }

}
