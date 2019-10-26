package com.example.uml.fragment;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

@EFragment(R.layout.donate)
public class DonateFragment extends BaseFragment {
    @ViewById(R.id.pay)
    Button pay;
    @ViewById(R.id.cardNumber)
    EditText cardNumber;
    @ViewById(R.id.cvv)
    EditText cvv;
    @ViewById(R.id.limitOfWork)
    EditText limitOfWork;
    @ViewById(R.id.amount)
    EditText amount;


    @AfterViews
    void mainWork(){
        Observable<String> cardObservable = RxEditText.getTextWatcherObservable(cardNumber);
        Observable<String> cvvObservable = RxEditText.getTextWatcherObservable(cvv);
        Observable<String> limitObservable = RxEditText.getTextWatcherObservable(limitOfWork);
        Observable<String> amountObservable = RxEditText.getTextWatcherObservable(amount);
        Observable.combineLatest(cardObservable, cvvObservable, limitObservable,amountObservable, (s, s2, s3,s4) -> {
            if(s.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() )
                return false;
            else
                return true;
        }).subscribe(new Action1<Boolean>() {
            @Override
            public void call(Boolean aBoolean) {
                if (!aBoolean) {
                    pay.setEnabled(aBoolean);
                }
            }
        });
    }

    @Click
    void pay() {
        Toast.makeText(getActivity(),"THANKS!",Toast.LENGTH_SHORT).show();
          ((BaseActivity) Objects.requireNonNull(getActivity())).changeFragmentTo(new FragmentData(FragmentById.MAIN_FRAGMENT));
    }
}
