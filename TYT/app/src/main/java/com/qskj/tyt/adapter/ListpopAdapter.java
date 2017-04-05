package com.qskj.tyt.adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;

import com.qskj.tyt.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 带自动输出提示和删除功能的 ListPopupWindow ArrayAdapter，根据源码改的Filter
 */
public class ListpopAdapter extends ArrayAdapter {

    private Context mContext;
    private List<String> mArrayList;
    private List<String> mObjects;
    private ArrayFilter mFilter;
    private final Object mLock = new Object();
    private SharedPreferences.Editor editor;
    private String key;

    public ListpopAdapter(Context context, int resource, int textViewResourceId, List objects, SharedPreferences.Editor editor, String key) {
        super(context, resource, textViewResourceId, objects);
        this.mContext = context;
        this.mArrayList = objects;
        this.editor = editor;
        this.key = key;
    }

    @Override
    public int getCount() {
        return this.mObjects.size();
    }

    @Override
    public Object getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.cb_item_listpop_with_delete, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tv_text.setText(mObjects.get(position));
        holder.img_delete.setImageResource(R.mipmap.ic_clear);
        holder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obj = mObjects.remove(position);
                mArrayList.remove(obj);
                Set<String> sets = new TreeSet<>();
                for (int i = 0; i < mArrayList.size(); i++) {
                    sets.add(mArrayList.get(i));
                }

                editor.putStringSet(key, sets).commit();
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    private class ViewHolder {
        private AppCompatTextView tv_text;
        private AppCompatImageView img_delete;

        private ViewHolder(View view) {
            tv_text = (AppCompatTextView) view.findViewById(R.id.tv_text);
            img_delete = (AppCompatImageView) view.findViewById(R.id.img_delete);
        }
    }

    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();

            if (prefix == null || prefix.length() == 0) {
                synchronized (mLock) {
                    ArrayList<String> list = new ArrayList<>(mArrayList);
                    results.values = list;
                    results.count = list.size();
                    return results;
                }
            } else {
                String prefixString = prefix.toString().toLowerCase();

                final int count = mArrayList.size();

                final ArrayList<String> newValues = new ArrayList<>(count);

                for (int i = 0; i < count; i++) {
                    final String value = mArrayList.get(i);
                    final String valueText = value.toLowerCase();

                    if (valueText.startsWith(prefixString)) {
                        newValues.add(value);
                    }
                }

                results.values = newValues;
                results.count = newValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mObjects = (List<String>) results.values;
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }

}
