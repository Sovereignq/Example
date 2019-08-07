package com.example.uml.mvp.manager.core;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import com.example.uml.R;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.mvp.core.ToolBarById;
import com.example.uml.mvp.manager.core.core.BaseManagerUI;

public abstract class BaseMainActivityManagerUI extends BaseManagerUI {

    public BaseMainActivityManagerUI(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    public void initToolbar(BaseFragment baseFragment, ToolBarById toolBarById, String... text) {
        View inflate = null;
        switch (toolBarById) {
            case CLOSE: {
                inflate = getActivity().getLayoutInflater().inflate(R.layout.toolbar_close, null);
                inflate.findViewById(R.id.close).setOnClickListener(view ->
                        getActivity().onBackPressed());
                break;
            }
        }
        updateToolbar(baseFragment, inflate);
    }

    private void updateToolbar(BaseFragment baseFragment, View inflate) {
        baseFragment.getToolbarContainer().removeAllViews();
        baseFragment.getToolbarContainer().addView(inflate);
    }
}
