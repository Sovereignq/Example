package com.example.uml.mvp.manager;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uml.R;
import com.example.uml.fragment.SplashFragment_;
import com.example.uml.mvp.core.FragmentData;
import com.example.uml.mvp.manager.core.BaseMainActivityManagerUI;

public class MainActivityManagerUI extends BaseMainActivityManagerUI {

    public MainActivityManagerUI(AppCompatActivity activity) {
        super(activity);
    }

    @Override
    protected int getIdFragmentsContainer() {
        return R.id.fragment_container;
    }

    @Override
    protected void initUI() {
    }

    @SuppressLint("CommitTransaction")
    @Override
    public void changeFragmentTo(FragmentData fragment) {
        switch (fragment.getFragmentById()) {
            case SPLASH_FRAGMENT: {
                addFragmentToContainer(SplashFragment_.builder().build(), false,
                        this.getActivity().getSupportFragmentManager().beginTransaction()
                                .setCustomAnimations(0, android.R.anim.fade_out));
                break;
            }
            
            case NEW_FRAGMENT:{
                // TODO: 15.07.2019 add fragment 
            }

            case NEW_NEW:
            {

            }
        }
    }

    private void removeAnimFragment(Fragment fragment, int anim) {
        this.getActivity().getSupportFragmentManager().
                beginTransaction().
                setCustomAnimations(anim, anim).
                remove(fragment).
                commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private String[] fragments = {
            //SplashFragment_.class.getSimpleName()
    };

    @Override
    public boolean removeFragment() {
        for (String s : fragments) {
            Fragment fragment = this.getActivity().getSupportFragmentManager().findFragmentByTag(s);
            if (fragment != null) {
//                if (fragment.getClass().getSimpleName().equals(SplashFragment_.class.getSimpleName())) {
//                    BaseFragment.changeColorBar(getActivity(), BaseFragment.ColorBar.WHITE_DARK);
//                }
//                removeAnimFragment(fragment, R.anim.anim_exit);
                return true;
            }
        }
        return false;
    }
}
