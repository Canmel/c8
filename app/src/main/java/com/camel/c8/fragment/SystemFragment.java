package com.camel.c8.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.camel.c8.R;
import com.camel.c8.system.SysMenusFragment;
import com.camel.c8.system.SysRolesFragment;
import com.camel.c8.system.SysUsersFragment;

public class SystemFragment extends Fragment {
    public RadioGroup group;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initSetting();
        initEvent();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SysUsersFragment sysUsersFragment = SysUsersFragment.getInstance();
        changeFragment(sysUsersFragment);
    }

    public void initEvent() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                for (int i = 0;i<group.getChildCount();i++){
                    RadioButton rb = (RadioButton)group.getChildAt(i);
                    if(rb.isChecked()){
                        setIndexSelected(i);
                        break;
                    }
                }
            }
        });
    }

    public void initSetting(){
        group = getActivity().findViewById(R.id.rg_main);
    }

    //通过index判断当前加载哪个界面
    public void setIndexSelected(int index)
    {
        switch (index)
        {
            case 0:
                changeFragment(SysUsersFragment.getInstance());
                break;
            case 1:
                changeFragment(SysRolesFragment.getInstance());
                break;
            case 2:
                changeFragment(SysMenusFragment.getInstance());
                break;
            default:
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_system, null);
        return view;
    }

    private void changeFragment(Fragment fragment)
    {
        final View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_system, null);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment , fragment);
        transaction.commit();
    }
}
