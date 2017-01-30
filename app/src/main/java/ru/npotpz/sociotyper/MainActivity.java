package ru.npotpz.sociotyper;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import ru.npotpz.sociotyper.models.ReininAdapter;
import ru.npotpz.sociotyper.models.ReininTypeModel;

public class MainActivity extends AppCompatActivity {


     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fillReininList();
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

    public void fillReininList(){
        DBHelper db = new DBHelper(this);

        ArrayList<ReininTypeModel> arrayOfTypeSwitches = new ArrayList<ReininTypeModel>();
        ReininAdapter adapter = new ReininAdapter(MainActivity.this, arrayOfTypeSwitches);

        ListView switchesList = (ListView) findViewById(R.id.lvSwitches);
        switchesList.setAdapter(adapter);

        Cursor res = db.getReininData();

        ArrayList<ArrayList> reininArray = new ArrayList();

        while (res.moveToNext()){
            int reininPair = res.getInt(2);
            int curArraySize = reininArray.size();
            String reininName = res.getString(1);

            ArrayList<String> curArray = new ArrayList<String>();
            curArray.clear();
            if(reininPair != curArraySize) {
                curArray = reininArray.get(reininPair);
            }else{
                reininArray.add(reininPair, curArray );
            }
            curArray.add(curArray.size(), reininName);

            reininArray.set(reininPair, curArray );

        }

        for (ArrayList<String> curData: reininArray) {
            ReininTypeModel curIterationData = new ReininTypeModel(curData.get(0), curData.get(1), false, false);
            adapter.add(curIterationData);

        }
       /* ReininTypeModel StatDin = new ReininTypeModel("Статика", "Динамика", true, true);
        adapter.add(StatDin);
        ReininTypeModel PrRes = new ReininTypeModel("Процесс", "Результат",true, false);
        adapter.add(PrRes);
        ReininTypeModel KvestDekl = new ReininTypeModel("Квестимность", "Деклатимность", false, true);
        adapter.add(KvestDekl);*/
    }
}
