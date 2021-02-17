package com.example.shopping;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

public class MainActivity extends AppCompatActivity {
    TextView SignUp;
    private EditText email,pass;
    private Button btnLogin;
    FirebaseAuth mAuth;
    ProgressDialog mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgress = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();

        SignUp = findViewById(R.id.TextSignUp);
        email= findViewById(R.id.EditTxt_Email);
        pass = findViewById(R.id.EditTxt_password);
        btnLogin = findViewById(R.id.ButtonSignIn);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });
       btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               String mEmail = email.getText().toString().trim();
                String mPass = pass.getText().toString().trim();
               if (TextUtils.isEmpty(mEmail)){
                   email.setError("Required Field..");
                   return;
               }if (TextUtils.isEmpty(mPass)){
                   pass.setError("Required Field..");
                   return;
               }
               mProgress.setMessage("Processing...");
               mProgress.show();

               mAuth.signInWithEmailAndPassword(mEmail,mPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                   @Override
                   public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()){

                      startActivity(new Intent(MainActivity.this,HomeActivity.class));
                      Toast.makeText(MainActivity.this, "Successful", Toast.LENGTH_SHORT).show();
                      mProgress.dismiss();
                  }else {
                      Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                      mProgress.dismiss();
                  }
                   }
               });

           }
       });

    }
}
