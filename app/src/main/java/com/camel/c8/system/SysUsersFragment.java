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
public class SysUsersFragment extends Fragment {

    private static SysUsersFragment sysUsersFragment;
    //单例模式
    public static SysUsersFragment getInstance()
    {
        if(sysUsersFragment == null){
            sysUsersFragment = new SysUsersFragment();
        }
        return sysUsersFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sys_users, container, false);
        return view;
    }

}
