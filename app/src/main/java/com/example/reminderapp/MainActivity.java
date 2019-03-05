package com.example.reminderapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private Button btn1;
    private EditText email,pass;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    RelativeLayout r1,r2;
    Handler handler = new Handler();
    Runnable runnable=new Runnable() {
        @Override
        public void run() {
            r1.setVisibility(View.VISIBLE);
            r2.setVisibility(View.VISIBLE);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        r1=(RelativeLayout)findViewById(R.id.relly1);
        r2=(RelativeLayout)findViewById(R.id.relly2);
        handler.postDelayed(runnable,2000);
        email=(EditText)findViewById(R.id.uemet1);
        pass=(EditText)findViewById(R.id.upassi2);
        progressDialog=new ProgressDialog(this);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(this,Entrypge.class));
        }
        btn1=findViewById(R.id.btln);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }
    boolean isEmail(EditText text){
        CharSequence email1=text.getText().toString();
        return(!TextUtils.isEmpty(email1) && Patterns.EMAIL_ADDRESS.matcher(email1).matches());
    }
    boolean isEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    private void userLogin(){
        if(isEmail(email)==false){
            email.setError("Enter valid Email");
        }
        if(isEmpty(pass)){
            pass.setError("Enter your password");
        }
        progressDialog.setMessage("Your wait will be worth it.....");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email.getText().toString().trim(),pass.getText().toString().trim())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            finish();
                            startActivity(new Intent(getApplicationContext(),Entrypge.class));
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Wrong EmailID or Password!",Toast.LENGTH_LONG).show();

                        }

                    }
                });

    }

    public void sendReg(View view){
        Intent intent=new Intent(this,registration_student.class);
        startActivity(intent);

    }

}
