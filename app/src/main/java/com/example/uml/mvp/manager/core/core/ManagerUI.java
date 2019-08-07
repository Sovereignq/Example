package com.example.uml.mvp.manager.core.core;

import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.mvp.core.FragmentData;
import com.example.uml.mvp.core.ToolBarById;

public interface ManagerUI {

    void changeFragmentTo(FragmentData fragmentData);

    void initToolbar(BaseFragment baseFragment, ToolBarById toolBarById, String... text);

    boolean removeFragment();
}
