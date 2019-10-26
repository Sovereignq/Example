package com.example.uml.mvp.manager;

import android.annotation.SuppressLint;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import com.example.uml.R;
import com.example.uml.fragment.DonateFragment;
import com.example.uml.fragment.DonateFragment_;
import com.example.uml.fragment.ListOfAnimals_;
import com.example.uml.fragment.ListOfNecessities_;
import com.example.uml.fragment.LoginFragment_;
import com.example.uml.fragment.LongDescriptionAnimal_;
import com.example.uml.fragment.MainMenuFragment;
import com.example.uml.fragment.MainMenuFragment_;
import com.example.uml.fragment.SignupFragment_;
import com.example.uml.mvp.core.FragmentData;
import com.example.uml.mvp.manager.core.BaseMainActivityManagerUI;
import java.util.ArrayList;

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
            case LOGIN_FRAGMENT: {
                removeFragment();
                addFragmentToContainer(LoginFragment_.builder().build(), false,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
                break;
            }
            case SIGNUP_FRAGMENT: {
                removeFragment();
                addFragmentToContainer(SignupFragment_.builder().build(), false,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
                break;
            }
            case LIST_FRAGMENT: {
                removeFragment();
                addFragmentToContainer(ListOfAnimals_.builder().build(), true,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
                break;
            }
            case LIST_NECESSITIES: {
                removeFragment();
                addFragmentToContainer(ListOfNecessities_.builder().build(), true,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
                break;
            }
            case LONG_DESCRIPTION_FRAGMENT:
            {
                removeFragment();
                addFragmentToContainer(LongDescriptionAnimal_.builder().build(), true,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
                break;
            }
            case MAIN_FRAGMENT:{
                removeFragment();
                addFragmentToContainer(MainMenuFragment_.builder().build(), true,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
            }
            case DONATE_FRAGMENT:{
                removeFragment();
                addFragmentToContainer(DonateFragment_.builder().build(), false,
                        this.getActivity().
                                getSupportFragmentManager().
                                beginTransaction());
            }
        }
    }

    private void removeNotAnimFragment(Fragment fragment) {
        this.getActivity().getSupportFragmentManager().
                beginTransaction().
                remove(fragment).
                commitAllowingStateLoss();
        getActivity().getSupportFragmentManager().popBackStack();
    }

    private static ArrayList<String> fragments = new ArrayList<>();

    @Override
    public boolean removeFragment() {
        for (String s : fragments) {
            Fragment fragment = this.getActivity().getSupportFragmentManager().findFragmentByTag(s);
            if (fragment != null) {
                removeNotAnimFragment(fragment);
                return true;
            }
        }
        return false;
    }
}
