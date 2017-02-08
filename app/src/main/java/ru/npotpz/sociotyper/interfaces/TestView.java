package ru.npotpz.sociotyper.interfaces;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.ArrayList;

/**
 * Created by Art on 07.02.2017.
 */

public interface TestView extends MvpView {

    void setReininType(String type, ArrayList<ArrayList> reininArray);
}
