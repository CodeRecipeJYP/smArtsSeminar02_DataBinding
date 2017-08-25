package com.example.jaeyoung.smartsseminar02_databinding.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;

import com.example.jaeyoung.smartsseminar02_databinding.databinding.ActivityMainBinding;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxAdapterView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

/**
 * Created by jaeyoung on 25/08/2017.
 */

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";

    @NonNull
    public ObservableField<String> mTitle = new ObservableField<>("initial");
    @NonNull
    public ObservableField<Boolean> mProgressbarStatus = new ObservableField<>(false);
    @NonNull
    public ObservableField<Integer> mSpinnerVisibility = new ObservableField<>(View.VISIBLE);
    public ObservableField<List<String>> mCompanies = new ObservableField<>();

    @NonNull
    public ObservableField<Integer> mProductsSpinnerVisibility = new ObservableField<>(View.GONE);
    public ObservableField<List<String>> mProducts = new ObservableField<>();

    public MainViewModel() {
        Handler handler = new Handler();

        mProgressbarStatus.set(true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mProgressbarStatus.set(false);
                mTitle.set("데이터로딩완료");

                ArrayList<String> companies = new ArrayList<String>();
                companies.add("smArts");
                companies.add("samsung");

                mCompanies.set(companies);
            }
        }, 5000);
    }
    // 1. smarts를 고르면 전구, 에어컨
    // 2. samsung 냉장고, 로봇청소기
    // 3. 회사를 골랐을때 3초뒤에 위의 리스트가 뜨도록
    // 4. 기다리는동안 프로그레스 바가 돌도록

    public void attachViews(ActivityMainBinding binding) {
        Observable<Integer> observable =
                RxAdapterView.itemSelections(binding.spinnerCompanies);
        Observable<Object> clicks = RxView.clicks(binding.textView);

        observable.subscribe(
                (position) -> {
                    Log.d(TAG, "itemSelections called with: position = [" + position + "]");
                    mTitle.set("itemSelections called with: position = [" + position + "]");
                    if (position != -1) {
                        mProductsSpinnerVisibility.set(View.VISIBLE);
                    }
                }
        );
    }
}
