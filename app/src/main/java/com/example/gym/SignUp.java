package com.example.gym;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {
    EditText name1, contact1, password1, age1, height1, weight1, gender1;
    TextView tname, tcon, tpass;
    Button signup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        MyDatabase dbHelper = new MyDatabase(this);

        signup = (Button) findViewById(R.id.signup);

        name1 = findViewById(R.id.name);
        contact1 = findViewById(R.id.contact);
        password1 = findViewById(R.id.password);
        age1 = findViewById(R.id.age);
        height1 = findViewById(R.id.height);
        weight1 = findViewById(R.id.weight);
        gender1 = findViewById(R.id.gender);

        tname = (TextView) findViewById(R.id.tname);
        tcon = (TextView) findViewById(R.id.tcon);
        tpass = (TextView) findViewById(R.id.tpass);
        //String a = age1.getText().toString();
        //String h = height1.getText().toString();
        //String w = weight1.getText().toString();

        //UserData userData = UserData.getInstance();
        //userData.setAge(a);
        //userData.setHeight(h);
        //userData.setWeight(w);


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

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String n = name1.getText().toString();
                String c = contact1.getText().toString();
                String p = password1.getText().toString();
                String a = age1.getText().toString();
                String h = height1.getText().toString();
                String w = weight1.getText().toString();
                String g = gender1.getText().toString();
                UserData userData = UserData.getInstance();
                userData.setAge(a);
                userData.setHeight(h);
                userData.setWeight(w);

                if(n.equals("") || c.equals("") || p.equals("") || a.equals("") || h.equals("") || w.equals("") || g.equals(""))
                {
                    Toast.makeText(SignUp.this, "Enter all fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean success =  dbHelper.addUser(n, c, p, a, h, w, g);

                    if(success == true)
                    {
                        Toast.makeText(SignUp.this, "User created successfully. PLease Login again", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(SignUp.this, Login.class);

                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(SignUp.this, "User created successfully. PLease Login again", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}