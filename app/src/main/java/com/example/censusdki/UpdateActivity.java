package com.example.censusdki;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class UpdateActivity extends AppCompatActivity {

    protected Cursor cursor;
    EditText input_name,input_nik, input_age, input_gender, input_phone, input_address, input_job;
    Button btn_save;
    DBHelper DB;
    private TextInputLayout textinputname;
    private TextInputLayout textinputage;
    private TextInputLayout textinputphone;
    private TextInputLayout textinputaddress;
    private TextInputLayout textinputjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        DB = new DBHelper(this);

        //validate Text input layout
        textinputname = findViewById(R.id.text_input_name);
        textinputage = findViewById(R.id.text_input_age);
        textinputphone = findViewById(R.id.text_input_phone);
        textinputaddress = findViewById(R.id.text_input_address);
        textinputjob = findViewById(R.id.text_input_job);
        btn_save = findViewById(R.id.btn_save);

        //update data
        input_name = findViewById(R.id.inputname);
        input_nik = findViewById(R.id.inputnik);
        input_age = findViewById(R.id.inputage);
        input_gender = findViewById(R.id.inputgender);
        input_phone = findViewById(R.id.inputphone);
        input_address = findViewById(R.id.inputaddress);
        input_job = findViewById(R.id.inputjob);

        SQLiteDatabase dbl = DB.getReadableDatabase();
        cursor = dbl.rawQuery("SELECT * FROM history where name = '" +
                getIntent().getStringExtra("name")+"'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0){
            cursor.moveToPosition(0);
            input_name.setText(cursor.getString(0).toString());
            input_nik.setText(cursor.getString(1).toString());
            input_age.setText(cursor.getString(2).toString());
            input_gender.setText(cursor.getString(3).toString());
            input_phone.setText(cursor.getString(4).toString());
            input_address.setText(cursor.getString(5).toString());
            input_job.setText(cursor.getString(6).toString());
        }

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase dbl = DB.getWritableDatabase();
                dbl.execSQL("update history set name = '" +
                        input_name.getText().toString() +"', nik = '" +
                        input_nik.getText().toString() + "', age =  '" +
                        input_age.getText().toString() + "', gender = '" +
                        input_gender.getText().toString() + "',phone = '" +
                        input_phone.getText().toString() + "', address = '" +
                        input_address.getText().toString() + "', job = '" +
                        input_job.getText().toString() + "' where name = '" +
                        getIntent().getStringExtra("name")+"'");
                Toast.makeText(UpdateActivity.this, "Data berhasil di update", Toast.LENGTH_SHORT).show();
                ShowData.ma.RefreshList();
                finish();
            }
        });
    }
}