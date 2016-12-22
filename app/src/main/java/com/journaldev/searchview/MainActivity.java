package com.journaldev.searchview;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.journaldev.searchview.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //Explicit
    private ActivityMainBinding activityMainBinding;
    private ListAdapter adapter;

    private List<String> arrayList= new ArrayList<>();

    private String[] wordStrings, detailStrings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        try {

            SynWord synWord = new SynWord(MainActivity.this);
            synWord.execute();

            String s = synWord.get();
            Log.d("wordV1", "JSON ==> " + s);

            JSONArray jsonArray = new JSONArray(s);

            wordStrings = new String[jsonArray.length()];
            detailStrings = new String[jsonArray.length()];

            for (int i=0;i<jsonArray.length();i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                wordStrings[i] = jsonObject.getString("Word");
                detailStrings[i] = jsonObject.getString("Detail");

                arrayList.add(wordStrings[i]);

            }   // for


        } catch (Exception e) {
            e.printStackTrace();
        }


        adapter= new ListAdapter(arrayList);
        activityMainBinding.listView.setAdapter(adapter);

        activityMainBinding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Detailword.class);

                intent.putExtra("Word", wordStrings[i]);
                intent.putExtra("Detail", detailStrings[i]);

                startActivity(intent);

            }
        });

        activityMainBinding.search.setActivated(true);
        activityMainBinding.search.setQueryHint("Type your keyword here");
        activityMainBinding.search.onActionViewExpanded();
        activityMainBinding.search.setIconified(false);
        activityMainBinding.search.clearFocus();

        activityMainBinding.search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);

                return false;
            }
        });
    }
}
