package com.mojtaba_shafaei.android.bottomdialogmenuexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView version = findViewById(R.id.version);
        version.setText(String.format(getString(R.string.version), BuildConfig.VERSION_NAME));
    }
}
