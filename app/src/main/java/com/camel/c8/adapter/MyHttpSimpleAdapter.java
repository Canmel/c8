package com.camel.c8.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.camel.c8.service.ISelectList;

import java.util.List;
import java.util.Map;

public class MyHttpSimpleAdapter extends SimpleAdapter {

    public MyHttpSimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    public MyHttpSimpleAdapter(Context context, int resource, String[] from, int[] to, ISelectList selectList) {
        super(context, selectList.select(), resource, from, to);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public Object getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    @Override
    public void setDropDownViewResource(int resource) {
        super.setDropDownViewResource(resource);
    }

    @Override
    public void setDropDownViewTheme(Resources.Theme theme) {
        super.setDropDownViewTheme(theme);
    }

    @Override
    public Resources.Theme getDropDownViewTheme() {
        return super.getDropDownViewTheme();
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }

    @Override
    public ViewBinder getViewBinder() {
        return super.getViewBinder();
    }

    @Override
    public void setViewBinder(ViewBinder viewBinder) {
        super.setViewBinder(viewBinder);
    }

    @Override
    public void setViewImage(ImageView v, int value) {
        super.setViewImage(v, value);
    }

    @Override
    public void setViewImage(ImageView v, String value) {
        super.setViewImage(v, value);
    }

    @Override
    public void setViewText(TextView v, String text) {
        super.setViewText(v, text);
    }

    @Override
    public Filter getFilter() {
        return super.getFilter();
    }
}
