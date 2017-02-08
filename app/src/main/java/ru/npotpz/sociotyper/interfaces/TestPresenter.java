package ru.npotpz.sociotyper.interfaces;

import android.content.Context;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;

/**
 * Created by Art on 07.02.2017.
 */

public interface TestPresenter extends MvpPresenter<TestView> {
   void fillReininList(String type, Context context);
}
