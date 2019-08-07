package com.example.uml.fragment.core;

import androidx.fragment.app.Fragment;
import android.graphics.Color;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.Toolbar;
import com.example.uml.R;
import com.example.uml.mvp.core.FragmentData;
import com.example.uml.mvp.core.FragmentFeedback;
import com.example.uml.mvp.core.ToolBarById;
import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

@EFragment
public abstract class BaseFragment extends Fragment {

    @ViewById(R.id.toolbar_container)
    public RelativeLayout toolbarContainer;
    @ViewById(R.id.toolbar)
    public Toolbar toolbar;

    @AfterInject
    public void initBaseFragment() {
        cleanFullScreen();
    }

    protected void initToolBar(ToolBarById toolBarById, String... text) {
        if (this.getFragmentFeedback() != null) {
            this.getFragmentFeedback().initToolBar(this, toolBarById, text);
        }
    }

    protected void setFullScreen() {
        if (getActivity() != null)
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public enum ColorBar {
        BLUE(Color.parseColor("#1B74E3")),
        WHITE_DARK(Color.parseColor("#F1F3F6")),
        BLACK(Color.parseColor("#000000")),
        WHITE(Color.WHITE);

        private final int color;

        private ColorBar(int color) {
            this.color = color;
        }
    }

    protected void cleanFullScreen() {
        if (getActivity() != null)
            getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    public RelativeLayout getToolbarContainer() {
        return toolbarContainer;
    }

    protected FragmentFeedback getFragmentFeedback() {
        return (FragmentFeedback) this.getActivity();
    }

    protected void changeFragmentTo(FragmentData fragment) {
        if (this.getFragmentFeedback() != null) {
            this.getFragmentFeedback().changeFragmentTo(fragment);
        }
    }
}
