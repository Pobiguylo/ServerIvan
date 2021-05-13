package com.example.serverivan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SuperActivity extends AppCompatActivity {
TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super);
        this.t= findViewById(R.id.textView);
        Intent intent = new Intent();
        String s =intent.getStringExtra("1");
        t.setText(s);
    }
}
