package com.camel.c8.system;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.camel.c8.R;


public class SysRolesFragment extends Fragment {
    private static SysRolesFragment sysRolesFragment;

    //单例模式
    public static SysRolesFragment getInstance() {
        if (sysRolesFragment == null) {
            sysRolesFragment = new SysRolesFragment();
        }
        return sysRolesFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sys_roles, container, false);
        return view;
    }
}
