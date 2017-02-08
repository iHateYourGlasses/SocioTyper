package ru.npotpz.sociotyper.views;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.ArrayList;

import ru.npotpz.sociotyper.DBHelper;
import ru.npotpz.sociotyper.R;
import ru.npotpz.sociotyper.interfaces.TestPresenter;
import ru.npotpz.sociotyper.interfaces.TestView;
import ru.npotpz.sociotyper.models.ReininAdapter;
import ru.npotpz.sociotyper.models.ReininTypeModel;
import ru.npotpz.sociotyper.presenters.TestPresenterImpl;

public class MainActivity extends MvpActivity<TestView, TestPresenter> implements TestView {

    @NonNull
    @Override
    public TestPresenter createPresenter() {
        return new TestPresenterImpl(getApplicationContext());
    }

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getPresenter().fillReininList("", getApplicationContext());
        fillSpinner();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setReininType(String type, ArrayList<ArrayList> reininArray) {

        ArrayList<ReininTypeModel> arrayOfTypeSwitches = new ArrayList<ReininTypeModel>();
        ReininAdapter adapter = new ReininAdapter(MainActivity.this, arrayOfTypeSwitches);

        ListView switchesList = (ListView) findViewById(R.id.lvSwitches);
        switchesList.setAdapter(adapter);

        for (ArrayList<String> curData: reininArray) {
            boolean switchActive = true;
            if(type == "" || type == "Не выбрано"){
                switchActive = false;
            }
            boolean isFirstTrue;
            int firstOrSecond =  Integer.parseInt(curData.get(2));
            if(firstOrSecond == 1) {
                isFirstTrue = false;
            }else isFirstTrue = true;

            ReininTypeModel curIterationData = new ReininTypeModel(curData.get(0), curData.get(1), switchActive, isFirstTrue);
            adapter.add(curIterationData);

        }
    }

    public void fillSpinner(){
        DBHelper db = new DBHelper(this);
        Cursor res = db.getTypes();
        ArrayList<String> typesArray = new ArrayList();
        typesArray.add("Не выбрано");
        while (res.moveToNext()) {
            String type = res.getString(0);
            typesArray.add(typesArray.size(), type);
        }
        ArrayAdapter<String> typesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, typesArray);
        final Spinner spinnerView = (Spinner)findViewById(R.id.spTypeSwitcher);
        spinnerView.setAdapter(typesAdapter);

        spinnerView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {{
                String selectedItem = spinnerView.getSelectedItem().toString();

                getPresenter().fillReininList(selectedItem, getApplicationContext());
            }}

            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });
    }

}