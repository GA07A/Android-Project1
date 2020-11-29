package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    EditText regName , regEmail , regPhone , regPass;
    Button regBtn ;
    TextView login;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;

    FirebaseUser firebaseUser;
    String userID;
    String name , password ,email,phoneNo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        regName = findViewById(R.id.NameReg);
        regEmail = findViewById(R.id.EmailReg);
        regPass = findViewById(R.id.PasswordReg);
        regPhone = findViewById(R.id.PhoneReg);
        regBtn = findViewById(R.id.newReg);
        login = findViewById(R.id.swipeLeft);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUp.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = regName.getText().toString();
                email = regEmail.getText().toString();
                password = regPass.getText().toString();
                phoneNo = regPhone.getText().toString();

                if (name.isEmpty()) {
                    regName.setError("Full Name is required!");
                    regName.requestFocus();
                    return;
                }
                if (phoneNo.isEmpty()) {
                    regPhone.setError("Birthday is required");
                    regPhone.requestFocus();
                    return;
                }
                if (email.isEmpty()) {
                    regEmail.setError("Email is required");
                    regEmail.requestFocus();
                    return;
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    regEmail.setError("Please provide valid email");
                    regEmail.requestFocus();
                    return;

                }
                if (password.isEmpty()) {
                    regPass.setError("Password is required");
                    regPass.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    regPass.setError("Min password length should be 6 characters!");
                    regPass.requestFocus();
                    return;


                }
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            sendUserData();
                            Toast.makeText(SignUp.this, "تم التسجيل بنجاح", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(SignUp.this, MainActivity.class);
                            startActivity(i);
                            finish();


                        }

                        else {
                            Toast.makeText(SignUp.this, "خطأ في التسجيل", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });
}



    public void sendUserData(){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
         DatabaseReference myRef = firebaseDatabase.getReference(mAuth.getUid());
        userHelperClass UserHelperClass = new userHelperClass(name,email,phoneNo,password);
        myRef.setValue(UserHelperClass);
    }







}
