package com.example.hp.carauction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Sign_up extends AppCompatActivity implements View.OnClickListener {
    private EditText xtext;
    private  CheckBox xcek;
    private Button login;
    //private EditText Name;
    private EditText pass;
    private EditText email_id;
    private ProgressDialog progressDialog;
    private Button    textViewSignin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        firebaseAuth = FirebaseAuth.getInstance();




        progressDialog = new ProgressDialog(this);

        textViewSignin=(Button) findViewById(R.id.textViewSignin);

        textViewSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aaa = new Intent(Sign_up.this, Login.class);
                startActivity(aaa);
            }
        });

        xtext = (EditText)findViewById(R.id.pass);
        xcek = (CheckBox) findViewById(R.id.show_hide_password);

        login = (Button)findViewById(R.id.login);
        //  Name = (EditText) findViewById(R.id.Name);
        email_id = (EditText)findViewById(R.id.email_id);
        pass = (EditText)findViewById(R.id.pass);

        xcek.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if (!ischecked) {
                    xtext.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    xtext.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        login.setOnClickListener(this);
    }
    private void registerUser(){
        String email = email_id.getText().toString().trim();
        String password =  pass.getText().toString().trim();
        //String name = Name.getText().toString().trim();

     /*   if(TextUtils.isEmpty(name)){
            Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
        }
       */
        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Please Enter Email ", Toast.LENGTH_SHORT).show();

            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please Enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        /*if(TextUtils.isEmpty(name)){
           Toast.makeText(this,"Please Enter Name", Toast.LENGTH_SHORT).show();
        }*/
        progressDialog.setMessage("Registering User....");
        progressDialog.show();
        // Toast.makeText(this,"Registering",Toast.LENGTH_SHORT).show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this,  new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            sendEmailVerification();
                            Toast.makeText(Sign_up.this,"Register Successfully",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();


                        }else{
                            Toast.makeText(Sign_up.this,"Could Not Register Try Again",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    private void sendEmailVerification() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Sign_up.this, "Check Your Mail For Verification", Toast.LENGTH_SHORT).show();
                        FirebaseAuth.getInstance().signOut();
                    }
                }
            });

        }
    }

    @Override
    public void onClick(View view) {
        if(view == login){
            registerUser();

        }
    }
}

