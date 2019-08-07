package com.example.uml.mvp.core;

import com.example.uml.fragment.core.BaseFragment;

public interface FragmentFeedback {

    void changeFragmentTo(FragmentData fragment);

    void initToolBar(BaseFragment baseFragment, ToolBarById toolBarById, String... label);
}
