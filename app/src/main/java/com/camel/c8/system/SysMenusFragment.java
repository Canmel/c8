package com.camel.c8.system;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bin.david.form.core.SmartTable;
import com.camel.c8.R;
import com.camel.c8.model.SysMenu;
import com.camel.c8.utils.JsonHelper;
import com.camel.c8.utils.OkHttpUtils;
import com.google.gson.JsonObject;
import com.yalantis.phoenix.PullToRefreshView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

    private OkHttpClient okHttpClient;

    private View view;

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
        view = inflater.inflate(R.layout.fragment_sys_menus, container, false);
        initSearch(view);
        List<SysMenu> list = new ArrayList<>();
        okHttpClient = new OkHttpClient();



        mPullToRefreshView = (PullToRefreshView) view.findViewById(R.id.pull_to_refresh);



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
        doResource();
        return view;
    }


    public void doResource() {
        resource = new ArrayList<>();
        OkHttpUtils.getInstance().get(getContext().getApplicationContext(), "http://192.168.100.3:8080/system/sysMenu", new HashMap<String, Object>(),
                (msg) -> {

                    try {
                        System.out.println("--------------------");
                        Map<String, Object> result = (Map<String, Object>) msg.obj;

                        JSONArray array = (JSONArray) result.get("list");
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject o = (JSONObject) array.get(i);
                            Map<String, Object> map = new HashMap<>();
                            map.put("name", o.get("name"));
                            map.put("url", o.get("url"));
                            resource.add(map);
                        }

                        SimpleAdapter simpleAdapter = new SimpleAdapter(this.getContext(), resource, android.R.layout.simple_list_item_activated_2, new String[]{"name", "url"}, new int[]{android.R.id.text1, android.R.id.text2});
                        listView = (ListView) view.findViewById(R.id.list_view);
                        listView.setAdapter(simpleAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                });
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
