package com.journaldev.searchview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Detailword extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailword);

        TextView wordTextView = (TextView) findViewById(R.id.textView5);
        wordTextView.setText(getIntent().getStringExtra("Word"));

        TextView detailTextView = (TextView) findViewById(R.id.textView6);
        detailTextView.setText(getIntent().getStringExtra("Detail"));


    }   // Main Method

    public void clickBack(View view) {
        finish();
    }


}   // Main Class
