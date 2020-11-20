package com.example.bhojana;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends AppCompatActivity {
    EditText Fullname, Username, Email, Password, CPassword;
    Button Register, Cancel;
    TextView Login;

    FirebaseAuth mAuth;
    FirebaseUser mUser;

    DatabaseReference databaseReference;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Fullname = findViewById(R.id.fullnametxt);
        Username = findViewById(R.id.username);
        Email = findViewById(R.id.emailtxt);
        Password = findViewById(R.id.passwordtxt);
        CPassword = findViewById(R.id.confirmpasswordtxt);
        Register = findViewById(R.id.register);
        Cancel = findViewById(R.id.cancel);
        Login = findViewById(R.id.logintxtview);

        mAuth = FirebaseAuth.getInstance();

        Register.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View view){
                        String uname = Username.getText().toString();
                        String fullname = Fullname.getText().toString();
                        String mail  = Email.getText().toString();
                        String pword = Password.getText().toString();
                        String confirmpword = CPassword.getText().toString();

                        if(TextUtils.isEmpty(uname) || TextUtils.isEmpty(mail) || TextUtils.isEmpty(pword) || TextUtils.isEmpty(confirmpword)){
                            Toast.makeText(getApplicationContext(),"Fill all required data",Toast.LENGTH_SHORT).show();

                        }else if (pword == confirmpword ){
                            Toast.makeText(getApplicationContext(),"Password are not matching",Toast.LENGTH_SHORT).show();

                        }else if(pword.length() < 6){
                            Toast.makeText(getApplicationContext(),"Password is too short",Toast.LENGTH_SHORT).show();

                        }else {
                            register(uname,fullname,mail,pword);
                        }

                    }
                }
        );

        Cancel.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View V){
                        Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                        startActivity(intent);
                    }
                }
        );

        Login.setOnClickListener(
                new View.OnClickListener(){
                    public void onClick(View v){
                        Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }

    public void register (final String Username, String Fullname, String mail, String password){
        mAuth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    mUser = mAuth.getCurrentUser();
                    String userid;
                    if (mUser != null) {
                        userid = mUser.getUid();


                        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(userid);
                        HashMap<String, String> hashMap = new HashMap<>();
                        hashMap.put("id", userid);
                        hashMap.put("Fullname", Fullname);
                        hashMap.put("Username", Username);
                        hashMap.put("Image", "default");

                        databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
                    } else{
                        Toast.makeText(getApplicationContext(),"check your email",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}