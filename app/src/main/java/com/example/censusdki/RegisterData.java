package com.example.censusdki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class RegisterData extends AppCompatActivity {

    protected Cursor cursor;
    DBHelper DB;
    Button btn_save;
    EditText input_nik, input_name, input_age, input_gender, input_phone, input_address, input_job;
    private TextInputLayout textinputname;
    private TextInputLayout textinputage;
    private TextInputLayout textinputphone;
    private TextInputLayout textinputaddress;
    private TextInputLayout textinputjob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_data);
        DB = new DBHelper(this);

        //validate Text input layout
        textinputname = findViewById(R.id.text_input_name);
        textinputage = findViewById(R.id.text_input_age);
        textinputphone = findViewById(R.id.text_input_phone);
        textinputaddress = findViewById(R.id.text_input_address);
        textinputjob = findViewById(R.id.text_input_job);
        btn_save = findViewById(R.id.btn_save);

        //insert data
        input_nik = findViewById(R.id.inputnik);
        input_name = findViewById(R.id.inputname);
        input_age = findViewById(R.id.inputage);
        input_gender = findViewById(R.id.inputgender);
        input_phone = findViewById(R.id.inputphone);
        input_address = findViewById(R.id.inputaddress);
        input_job = findViewById(R.id.inputjob);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = input_name.getText().toString();
                String nik = input_nik.getText().toString();
                String age = input_age.getText().toString();
                String gender = input_gender.getText().toString();
                String phone = input_phone.getText().toString();
                String address = input_address.getText().toString();
                String job = input_job.getText().toString();

                if (name.isEmpty()||nik.isEmpty()||age.isEmpty()||gender.isEmpty()||phone.isEmpty()||address.isEmpty())
                    Toast.makeText(RegisterData.this, "Please Enter all the field", Toast.LENGTH_SHORT).show();
                else{
                    Boolean insert =DB.insertDataForm(name, nik, age, gender, phone, address, job);
                    if (insert==true){
                        Toast.makeText(RegisterData.this, "Data berhasil di submit", Toast.LENGTH_SHORT).show();
                        ShowData.ma.RefreshList();
                        finish();
//                        Intent intent = new Intent(getApplicationContext(), Home.class);
//                        startActivity(intent);
                    } else
                        Toast.makeText(RegisterData.this, "please check all the field", Toast.LENGTH_SHORT).show();
                        return;
                }
            }
        });
    }

    private Boolean validateName(){
        String nameInput = textinputname.getEditText().getText().toString();

        if (nameInput.isEmpty()){
            textinputname.setError("Field Cant't be Empty");
            return false;
        } else {
            textinputname.setError(null);
            return true;
        }
    }

    private Boolean validateAge() {
        String ageInput = textinputage.getEditText().getText().toString();

        if (ageInput.isEmpty()) {
            textinputage.setError("Field can't be empty");
            return false;
        } else {
            textinputage.setError(null);
            return true;
        }
    }

    private Boolean validatePhone() {
        String numberInput = textinputphone.getEditText().getText().toString();

        if (numberInput.isEmpty()) {
            textinputphone.setError("Field can't be empty");
            return false;
        } else {
            textinputphone.setError(null);
            return true;
        }
    }

    private Boolean validateAddress() {
        String addressInput = textinputaddress.getEditText().getText().toString();

        if (addressInput.isEmpty()) {
            textinputaddress.setError("Field can't be empty");
            return false;
        } else {
            textinputaddress.setError(null);
            Toast.makeText(this, "Data has been submit", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Home.class);
            startActivity(intent);
            return true;
        }
    }

    private Boolean validateJob(){
        String jobInput = textinputjob.getEditText().getText().toString();

        if (jobInput.isEmpty()){
            textinputjob.setError("Field can't be empty");
            return false;
        } else {
            textinputjob.setError(null);
            return true;
        }
    }


    public void savebtn(View v){
        if (!validateName()| !validateAge()| !validatePhone()| !validateAddress()| !validateJob()){
            return;
        }
    }
}