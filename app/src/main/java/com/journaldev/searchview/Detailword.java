package com.journaldev.searchview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class Detailword extends AppCompatActivity {

    //Explicit
    private String wordString;
    private TextView wordTextView, detailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailword);

        //Bind Widget
        wordTextView = (TextView) findViewById(R.id.textView5);
        detailTextView = (TextView) findViewById(R.id.textView6);

        wordString = getIntent().getStringExtra("Word");
        Log.d("2janV3", "Text receive ==> " + wordString);

        try {

            SynDetail synDetail = new SynDetail(Detailword.this, wordString);
            synDetail.execute();
            String strJSON = synDetail.get();
            Log.d("2janV3", "JSON ==> " + strJSON);

            JSONArray jsonArray = new JSONArray(strJSON);
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            String strDetail = jsonObject.getString("Detail");

            wordTextView.setText(wordString);
            detailTextView.setText(strDetail);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }   // Main Method

    public void clickBack(View view) {
        finish();
    }


}   // Main Class
