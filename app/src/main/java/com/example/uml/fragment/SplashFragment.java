package com.example.uml.fragment;

import com.example.uml.R;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.mvp.core.ToolBarById;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_splash)
public class SplashFragment extends BaseFragment {

    @AfterViews
    public void init() {
        initToolBar(ToolBarById.CLOSE);
    }
}
