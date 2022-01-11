package com.example.censusdki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupPage extends AppCompatActivity {

    EditText usernametxt, emailtxt, passwordtxt;
    Button  signinbtn;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        usernametxt = (EditText) findViewById(R.id.usernametxt);
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        passwordtxt = (EditText) findViewById(R.id.passwrodtxt);
        signinbtn = (Button) findViewById(R.id.signinbtn);
        DB = new DBHelper(this);

        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = usernametxt.getText().toString();
                String email = emailtxt.getText().toString();
                String pass = passwordtxt.getText().toString();

                if(user.equals("")||email.equals("")||pass.equals(""))
                    Toast.makeText(SignupPage.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuser = DB.checkusername(user);
                    if (checkuser==false){
                        Boolean insert = DB.insertData(user, email, pass);
                        if (insert==true){
                            Toast.makeText(SignupPage.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(),successregist.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupPage.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), SignupPage.class);
                            startActivity(intent);
                        }
                    }
                }
            }
        });
    }
}