package com.example.uml.activity;

import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;
import com.example.uml.mvp.manager.MainActivityManagerUI;
import com.example.uml.mvp.manager.core.core.ManagerUI;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @AfterViews
    public void init() {
        changeFragmentTo(new FragmentData(FragmentById.SPLASH_FRAGMENT));
    }
    
    @Override
    protected ManagerUI getManagerUIToInit() {
        return new MainActivityManagerUI(this);
    }
}
