package com.example.censusdki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class successregist extends AppCompatActivity {
    Button successbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_successregist);

        successbtn = (Button) findViewById(R.id.successbtn);
        successbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),onboarding.class);
                startActivity(intent);
            }
        });
    }
}