package com.example.uml.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    @ViewById(R.id.inputEmail)
    EditText inputEmail;

    @ViewById(R.id.inputPassword)
    EditText inputPassword;

    @ViewById(R.id.btnSignup)
    Button btnSignup;

    @ViewById(R.id.btnLogin)
    Button buttonLogin;

    @AfterViews
    public void mainWork() {
        inputEmail = Objects.requireNonNull(getActivity()).findViewById(R.id.inputEmail);
        inputPassword = Objects.requireNonNull(getActivity()).findViewById(R.id.inputPassword);
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
    @Click
    void buttonLogin(){
        String [] array = new String[]{inputEmail.getText().toString(), inputPassword.getText().toString()};
        SQLiteDatabase db = SQLiteDatabase.openDatabase("storage/emulated/0/Download/testDatabase.sqlite", null, SQLiteDatabase.OPEN_READWRITE);
        Cursor cursor = db.rawQuery("SELECT * FROM "+User.TABLE_NAME+" WHERE "+User.COLUMN_EMAIL +"=?" + " AND "+ User.COLUMN_PASSWORD +" =?", array);
        //if (cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD))== passwordObservable.toString() && cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)) == emailObservable.toString() )
        if( cursor.getCount() == 1 /*&& cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD))== inputPassword.getText().toString() && cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)) == inputEmail.getText().toString()*/){
            System.out.println("You're logged in");
        }
        else{
            System.out.println("Invalid password or email!");
        }
    }
}
