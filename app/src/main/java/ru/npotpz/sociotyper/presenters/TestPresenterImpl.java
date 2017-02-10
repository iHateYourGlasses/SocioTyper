package ru.npotpz.sociotyper.presenters;

import android.content.Context;
import android.database.Cursor;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;

import ru.npotpz.sociotyper.views.DBHelper;
import ru.npotpz.sociotyper.interfaces.TestPresenter;
import ru.npotpz.sociotyper.interfaces.TestView;

/**
 * Created by Art on 07.02.2017.
 */

public class TestPresenterImpl extends MvpBasePresenter<TestView> implements TestPresenter {
    public TestPresenterImpl(Context context) {}

    @Override

    public void fillReininList(String type, Context context){
        DBHelper db = new DBHelper(context);

        Cursor res = db.getReininData(type);

        ArrayList<ArrayList> reininArray = new ArrayList();

        while (res.moveToNext()){
            int reininPair = res.getInt(1);
            int curArraySize = reininArray.size();
            String reininName = res.getString(0);

            ArrayList curArray = new ArrayList();
            curArray.clear();
            if(reininPair != curArraySize) {
                curArray = reininArray.get(reininPair);
            }else{
                reininArray.add(reininPair, curArray );
            }
            curArray.add(curArray.size(), reininName);
            if(curArray.size() > 1){
                int isTrueType = res.getInt(2);
                curArray.add(curArray.size(), Integer.toString(isTrueType));
            }

            reininArray.set(reininPair, curArray );

        }
        getView().setReininType(type, reininArray);
    }
}
