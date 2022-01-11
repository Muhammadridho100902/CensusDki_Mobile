package com.example.censusdki;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper db;
    TextView name, nik, age, gender, phone, address, job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        db = new DBHelper(this);
        name = findViewById(R.id.name);
        nik = findViewById(R.id.nik);
        age = findViewById(R.id.age);
        gender = findViewById(R.id.gender);
        phone = findViewById(R.id.phone);
        address = findViewById(R.id.address);
        job = findViewById(R.id.job);

        SQLiteDatabase dbl = db.getReadableDatabase();
        cursor = dbl.rawQuery("SELECT * FROM history where name = '" +
                getIntent().getStringExtra("name")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            name.setText(cursor.getString(0).toString());
            nik.setText(cursor.getString(1).toString());
            age.setText(cursor.getString(2).toString());
            gender.setText(cursor.getString(3).toString());
            phone.setText(cursor.getString(4).toString());
            address.setText(cursor.getString(5).toString());
            job.setText(cursor.getString(6).toString());
        }
    }
}