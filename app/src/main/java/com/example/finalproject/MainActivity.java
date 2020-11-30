package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    TextView SignIn1;
    Button SignIn2 ;
    ImageView image2;
    EditText userE , userP;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        image2 = findViewById(R.id.image2);
        SignIn2 = findViewById(R.id.SingIn);
        SignIn1 = findViewById(R.id.swipeRight);

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SignUp.class);
                startActivity(i);
            }
        });

        userE = findViewById(R.id.Email);
        userP = findViewById(R.id.Password);

        SignIn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin(v);

            }
        });

    }
    public void userLogin(View v){
        String Email = userE.getText().toString().trim();
        String Password = userP.getText().toString().trim();

        if(Email.isEmpty()){
            userE.setError("Email is required !");
            userE.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            userE.setError("Please enter a valid email");
            userE.requestFocus();
            return;
        }
        if(Password.isEmpty()){
            userP.setError("Password is required ");
            userP.requestFocus();
            return;
        }
        if(Password.length()<6){
            userP.setError("Min password length is 6 characters !");
            userP.requestFocus();
            return;
        }
            mAuth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        //redirect to user profile

                        Intent i = new Intent(MainActivity.this, Dashboard.class);
//                        cardView1.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(),"تم التسجيل بنجاح",Toast.LENGTH_LONG).show();
                        startActivity(i);
                    } else {
                        Toast.makeText(MainActivity.this, "خطأ في تسجيل الدخول ", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

