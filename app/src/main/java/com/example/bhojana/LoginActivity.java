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

public class LoginActivity extends AppCompatActivity {
    EditText Emailtxt, Passwordtxt;
    Button Login;
    TextView ForgetPassword;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Emailtxt = findViewById(R.id.login_email);
        Passwordtxt = findViewById(R.id.login_password);
        Login = findViewById(R.id.loginbtn);
        ForgetPassword = findViewById(R.id.forgetpassword);

        mAuth = FirebaseAuth.getInstance();

        Login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String mail = Emailtxt.getText().toString();
                        String pword = Passwordtxt.getText().toString();

                        if(TextUtils.isEmpty(mail) || TextUtils.isEmpty(pword)){
                            Toast.makeText(getApplicationContext(), "Fill all required data", Toast.LENGTH_SHORT).show();
                        }else {
                            mAuth.signInWithEmailAndPassword(mail,pword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                        intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }else {
                                        Toast.makeText(getApplicationContext(),"login fail",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }
                }
        );

        ForgetPassword.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                    }
                }
        );

    }
}