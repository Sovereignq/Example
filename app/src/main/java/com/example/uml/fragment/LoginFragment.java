package com.example.uml.fragment;

import android.widget.Button;
import android.widget.EditText;
import com.example.uml.R;
import com.example.uml.activity.MainActivity;
import com.example.uml.activity.core.BaseActivity;
import com.example.uml.firebase.RxEditText;
import com.example.uml.firebase.User;
import com.example.uml.fragment.core.BaseFragment;
import com.example.uml.mvp.core.FragmentById;
import com.example.uml.mvp.core.FragmentData;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import java.util.Objects;
import rx.Observable;

@EFragment(R.layout.fragment_login)
public class LoginFragment extends BaseFragment {

    @ViewById(R.id.btnSignup)
    Button btnSignup;

    @AfterViews
    public void mainWork() {
        EditText inputEmail = Objects.requireNonNull(getActivity()).findViewById(R.id.inputEmail);
        EditText inputPassword = Objects.requireNonNull(getActivity()).findViewById(R.id.inputPassword);
        Button buttonLogin = Objects.requireNonNull(getActivity()).findViewById(R.id.buttonLogin);
        buttonLogin.setEnabled(false);

        Observable<String> emailObservable = RxEditText.getTextWatcherObservable(inputEmail);
        Observable<String> passwordObservable = RxEditText.getTextWatcherObservable(inputPassword);

        Observable.combineLatest(emailObservable, passwordObservable, (s, s2) -> !s.isEmpty() && !s2.isEmpty()).subscribe(buttonLogin::setEnabled);
        //MainActivity ma = new MainActivity();
        //ma.isStoragePermissionGranted();
        User user = new User();
        User u1 =  user.getUser(1);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+u1.getId() + "  " + u1.getname());
    }

    @Click
    void btnSignup() {
        ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.SIGNUP_FRAGMENT));
    }
}
