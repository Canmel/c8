package com.camel.c8.system;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.camel.c8.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SysMenusFragment extends Fragment {

    private static SysMenusFragment sysMenusFragment;

    //单例模式
    public static SysMenusFragment getInstance() {
        if (sysMenusFragment == null) {
            sysMenusFragment = new SysMenusFragment();
        }
        return sysMenusFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sys_menus, container, false);
        return view;
    }

}
