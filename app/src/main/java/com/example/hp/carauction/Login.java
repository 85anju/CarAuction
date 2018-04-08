package com.example.hp.carauction;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Login extends AppCompatActivity implements View.OnClickListener {


    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignUp;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth=FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser()!=null){

            //profile activity here
            finish();
            startActivity(new Intent(getApplicationContext(),ProfileActivity.class));


        }

        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        buttonSignIn=(Button)findViewById(R.id.buttonSignIn);
        textViewSignUp=(TextView)findViewById(R.id.textViewSignUp);
        progressDialog=new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewSignUp.setOnClickListener(this);

    }
    private  void  userlogin(){
        String  email=editTextEmail.getText().toString().trim();
        String  password=editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this,"enter a valid mail",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this,"enter a valid password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering user....");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        FirebaseUser user=firebaseAuth.getCurrentUser();

                        progressDialog.dismiss();
                        if(!task.isSuccessful()){
                            //start the profile activity


                            Toast.makeText(Login.this,"Probably U don't have an account/Check your internet connection", Toast.LENGTH_SHORT).show();

                        }
                        else{

                            try{

                                if(user.isEmailVerified()){
                                    Intent xxx=new Intent(Login.this,ProfileActivity.class);
                                    startActivity(xxx);

                                }else{

                                    Toast.makeText(Login.this,"Email is not verified ..check your email", Toast.LENGTH_SHORT).show();

                                    firebaseAuth.signOut();

                                }
                            }catch (NullPointerException e ){

                                Toast.makeText(Login.this,"oncomplete null poiner exception", Toast.LENGTH_SHORT).show();

                            }
                        }

                    }
                });

    }

    @Override
    public void onClick(View view) {


        if(view==buttonSignIn){
            userlogin();

            editTextEmail.setText("");
            editTextPassword.setText(null);

        }
        if( view==textViewSignUp){
            finish();
            startActivity(new Intent(this, Sign_up.class));

        }

    }
}