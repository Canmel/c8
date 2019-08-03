package com.camel.c8.system;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.content.res.TypedArrayUtils;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.bin.david.form.core.SmartTable;
import com.bin.david.form.data.style.FontStyle;
import com.camel.c8.R;
import com.camel.c8.model.SysMenu;
import com.yalantis.phoenix.PullToRefreshView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import scut.carson_ho.searchview.ICallBack;
import scut.carson_ho.searchview.SearchView;
import scut.carson_ho.searchview.bCallBack;

/**
 * A simple {@link Fragment} subclass.
 */
public class SysMenusFragment extends Fragment {

    private static SysMenusFragment sysMenusFragment;

    private SmartTable table;

    private List<SysMenu> menus;

    private List<Map<String, Object>> resource;

    private ListView listView;

    private PullToRefreshView mPullToRefreshView;

    private SearchView searchView;

    //单例模式
    public static SysMenusFragment getInstance() {
        if (sysMenusFragment == null) {
            sysMenusFragment = new SysMenusFragment();
        }
        return sysMenusFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sys_menus, container, false);
        initSearch(view);
        List<SysMenu> list = new ArrayList<>();
        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getContext(), getResource(), android.R.layout.simple_expandable_list_item_2, new String[]{"name", "url"}, new int[]{android.R.id.text1, android.R.id.text2});

        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);
        listView = (ListView) view.findViewById(R.id.list_view);

        listView.setAdapter(simpleAdapter);
        mPullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPullToRefreshView.setRefreshing(false);
                    }
                }, 2000);
            }
        });
        return view;
    }


    public List<Map<String, Object>> getResource() {
        resource = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Map<String, Object> map = new HashMap<>();

            map.put("name", "标题" + i);
            map.put("url", "地址" + i);
            resource.add(map);
        }
        return resource;
    }

    public void initSearch(final View view) {
        // 3. 绑定组件
        searchView = (SearchView) view.findViewById(R.id.search_view);

        // 4. 设置点击键盘上的搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                Toast.makeText(getContext(), "asdasdadd", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
