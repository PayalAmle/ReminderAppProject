package com.example.reminderapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registration_student extends AppCompatActivity {

    EditText name,emailid,pass,cpass;
    DatabaseReference dteid;


    Button register;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_student);
        firebaseAuth=FirebaseAuth.getInstance();
        dteid= FirebaseDatabase.getInstance().getReference("persons");

        name=findViewById(R.id.uname);
        emailid=findViewById(R.id.uemail);

        pass=findViewById(R.id.upass);

        cpass=findViewById(R.id.ucpass);
        progressDialog=new ProgressDialog(this);


        register=findViewById(R.id.btreg);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkDataEntered();
                addPerson();

            }
        });


    }

    boolean isEmail(EditText text){
        CharSequence emailid=text.getText().toString();
        return(!TextUtils.isEmpty(emailid) && Patterns.EMAIL_ADDRESS.matcher(emailid).matches());
    }
    boolean isEmpty(EditText text){
        CharSequence str=text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    void checkDataEntered(){
        if (isEmpty(name)){
            Toast t=Toast.makeText(this,"You must enter your name first to register!",Toast.LENGTH_SHORT);
        }
        else if(isEmail(emailid)==false){
            emailid.setError("Enter valid Email");

        }



        else if(isEmpty(pass)){
            pass.setError("Enter your password");
        }
        else if(isEmpty(cpass)) {
            cpass.setError("Re-type password");
        }

        else if (!(pass.getText().toString().equals(cpass.getText().toString()))) {
            cpass.setError("Password does not match");


        }
        else{
            progressDialog.setMessage("Please wait for a while!!....");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(emailid.getText().toString(),pass.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            progressDialog.dismiss();


                            if (task.isSuccessful()) {
                                Intent i=new Intent(registration_student.this,Entrypge.class);
                                startActivity(i);
                                Toast.makeText(registration_student.this,"Registered User",Toast.LENGTH_SHORT).show();



                            }

                            else {


                                Toast.makeText(registration_student.this,"Could not register,please try again",Toast.LENGTH_SHORT).show();
                            }



                        }
                    });


        }







        }
        private void addPerson(){

            if(!TextUtils.isEmpty(name.getText().toString())){
                String id=dteid.push().getKey();
                Person person=new Person(id,name.getText().toString(),emailid.getText().toString(),pass.getText().toString());
                dteid.child(id).setValue(person);
                Toast.makeText(this,"Welcome",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(this,"You should enter a name",Toast.LENGTH_SHORT).show();
            }
        }

}
