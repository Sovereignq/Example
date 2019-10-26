package com.example.uml.fragment;

import android.widget.Button;

import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.Objects;

@EFragment(R.layout.main_fragment)
public class MainMenuFragment extends BaseFragment {
    @ViewById(R.id.button1)
    Button button1;

    @ViewById(R.id.button2)
    Button button2;

    @Click
    void button1(){
      ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.LIST_FRAGMENT));
    }

    @Click
    void button2(){
       ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.LIST_NECESSITIES));
    }

}
