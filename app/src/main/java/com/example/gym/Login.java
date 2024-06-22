package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    EditText name1, contact1, password1;
    TextView tname, tcon, tpass;
    Button login;
    Switch remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        MyDatabase dbHelper = new MyDatabase(this);

        login = (Button) findViewById(R.id.login);

        remember = (Switch) findViewById(R.id.remember);

        name1 = findViewById(R.id.name);
        contact1 = findViewById(R.id.contact);
        password1 = findViewById(R.id.password);

        tname = (TextView) findViewById(R.id.tname);
        tcon = (TextView) findViewById(R.id.tcon);
        tpass = (TextView) findViewById(R.id.tpass);

        password1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String pass = s.toString().trim();
                if(pass.length() >=4)
                {
                    Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
                    Matcher matcher = pattern.matcher(pass);
                    boolean isPwdContainsSpeChar = matcher.find();
                    if(isPwdContainsSpeChar)
                    {
                        tpass.setText("Strong Password");
                        tpass.setTextColor(Color.parseColor("#34F33C"));
                    }
                    else
                    {
                        tpass.setText("Weak Password. Include at least one special character");
                    }
                }
                else
                {
                    tpass.setText("Enter minimum 4 characters");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        contact1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String contact2 = s.toString().trim();
                if(contact2.length() <= 10)
                {
                    tcon.setText("Enter 10 digits only");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        final SharedPreferences sharedPreferences = getSharedPreferences("Data", Context.MODE_PRIVATE);
        final String type1 = sharedPreferences.getString("con", "");
        if(type1.isEmpty())
        {
            Toast.makeText(Login.this, "Login again", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent intent = new Intent(Login.this, Menu.class);
            startActivity(intent);
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name1.getText().toString();
                String c = contact1.getText().toString();
                String p = password1.getText().toString();

                if(n.equals("") || c.equals("") || p.equals(""))
                {
                    Toast.makeText(Login.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean isLoggedin = dbHelper.checkUser(n, c);

                    if(isLoggedin == true)
                    {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Login.this, Menu.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(Login.this, "Login Failed. Try again", Toast.LENGTH_SHORT).show();
                    }

                    if(remember.isChecked())
                    {
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("contact",c);
                        editor.putString("password",p);
                        editor.commit();
                    }
                }

            }
        });

    }
}