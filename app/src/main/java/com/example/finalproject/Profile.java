package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    EditText Pname,Pemail,phoneP,PassP;
    FirebaseUser user;
    DatabaseReference reference;
    private String userID;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth mAuth;
    Button btn , btn2 ,btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        Pname =findViewById(R.id.Name1);
        Pemail =findViewById(R.id.Email1);
        phoneP =findViewById(R.id.Phone1);
        PassP =findViewById(R.id.Password1);
        btn = findViewById(R.id.update);
        btn2 = findViewById(R.id.logout);
        btn3 = findViewById(R.id.delete);

        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();


// final
        reference = firebaseDatabase.getReference(mAuth.getUid());


        Pemail.setText(user.getEmail());
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(Profile.this);
                dialog.setTitle("هل أنت متأكد؟");
                dialog.setMessage("سوف يتم حذف الحساب نهائيًا");
                dialog.setPositiveButton("حذف", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        user.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(),"تم حذف الحساب بنجاح",
                                            Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(getApplicationContext(),SignUp.class);
                                    startActivity(i);
                                }else {
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                    }
                });
                dialog.setNegativeButton("إلغاء", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                    }
                });

                AlertDialog alertDialog= dialog.create();
                alertDialog.show();
            }
        });


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userHelperClass UserHelperClass = snapshot.getValue(userHelperClass.class);
                Pname.setText(UserHelperClass.getName());
                Pemail.setText(UserHelperClass.getEmail());
                phoneP.setText(UserHelperClass.getPhoneNo());
                PassP.setText(UserHelperClass.getPassword());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Error",Toast.LENGTH_LONG).show();
            }
        });


        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, SignUp.class));
            }


        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = Pname.getText().toString();
                String email = Pemail.getText().toString();
                String phone = phoneP.getText().toString();
                String password = PassP.getText().toString();


                user = FirebaseAuth.getInstance().getCurrentUser();
                user.updatePassword(password).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "تم تغيير الرقم السري بنجاح", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(getApplicationContext(), "فشل في تغيير الرقم السري", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                user.updateEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"تم تغيير الايميل بنجاح",Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            Toast.makeText(getApplicationContext(),"حدث خطأ في تغيير الايميل",Toast.LENGTH_LONG).show();
                        }

                    }
                });

                userHelperClass UserHelperClass = new userHelperClass( name , email , phone , password);
                reference.setValue(UserHelperClass);
                finish();


            }
        });



    }

}
