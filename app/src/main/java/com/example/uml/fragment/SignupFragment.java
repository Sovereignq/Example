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

@EFragment(R.layout.fragment_signup)
class SignupFragment extends BaseFragment {

    @ViewById(R.id.btnLogin)
    Button btnLogin;

    @ViewById(R.id.buttonSignup)
    Button buttonSignup;

    @AfterViews
    void mainWork() {
        EditText inputEmail = Objects.requireNonNull(getActivity()).findViewById(R.id.inputEmail);
        EditText inputPassword = Objects.requireNonNull(getActivity()).findViewById(R.id.inputPassword);
        EditText inputConfirmPassword = Objects.requireNonNull(getActivity()).findViewById(R.id.inputConfirmPassword);
        buttonSignup.setEnabled(false);

        Observable<String> emailObservable = RxEditText.getTextWatcherObservable(inputEmail);
        Observable<String> passwordConfirmObservable = RxEditText.getTextWatcherObservable(inputConfirmPassword);
        Observable<String> passwordObservable = RxEditText.getTextWatcherObservable(inputPassword);

        Observable.combineLatest(emailObservable, passwordObservable, passwordConfirmObservable, (s, s2, s3) -> !s.isEmpty() && !s2.isEmpty() && !s3.isEmpty() && s2.equals(s3)).subscribe(aBoolean -> buttonSignup.setEnabled(aBoolean));
    }

    @Click
    void btnLogin() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.LOGIN_FRAGMENT));
    }
}
