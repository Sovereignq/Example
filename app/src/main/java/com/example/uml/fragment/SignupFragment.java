package com.example.uml.fragment;

import android.widget.Button;
import android.widget.EditText;
import com.example.uml.R;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.firebase.RxEditText;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.util.Objects;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func2;

@EFragment(R.layout.fragment_signup)
public class SignupFragment extends BaseFragment {

    @ViewById(R.id.btnLogin)
    Button btnLogin;

    @AfterViews
    public void mainWork() {
        EditText inputEmail = Objects.requireNonNull(getActivity()).findViewById(R.id.inputEmail);
        EditText inputPassword = Objects.requireNonNull(getActivity()).findViewById(R.id.inputPassword);
        EditText inputConfirmPassword = Objects.requireNonNull(getActivity()).findViewById(R.id.inputConfirmPassword);
        Button buttonSignup = Objects.requireNonNull(getActivity()).findViewById(R.id.buttonSignup);
        buttonSignup.setEnabled(false);

        Observable<String> emailObservable = RxEditText.getTextWatcherObservable(inputEmail);
        Observable<String> passwordConfirmObservable = RxEditText.getTextWatcherObservable(inputConfirmPassword);

        Observable.combineLatest(emailObservable, passwordConfirmObservable, new Func2<String, String, Boolean>() {

            @Override
            public Boolean call(String s, String s2) {
                if(s.isEmpty() || s2.isEmpty())
                    return false;
                else
                    return true;
            }
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                buttonSignup.setEnabled(aBoolean);
            }
        });
    }

    @Click
    void btnLogin() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.LOGIN_FRAGMENT));
    }
}
