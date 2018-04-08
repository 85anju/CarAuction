package com.example.hp.carauction;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private ImageButton buttonLogout;


    ImageButton ButtonSell;
    ImageButton ButtonBid;

    ImageButton buttonMyp;

    //Button Bstartbid;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        firebaseAuth=FirebaseAuth.getInstance();


       /* Bstartbid=(Button)findViewById(R.id.Bstartbid);
        Bstartbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aaa = new Intent(ProfileActivity.this, MyBid.class);
                startActivity(aaa);
            }
        });*/


      /*  ButtonBid=(Button)findViewById(R.id.Bstartbid);
        Bstartbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aaa = new Intent(ProfileActivity.this, BidActivity.class);
                startActivity(aaa);
            }
        });*/




        if( firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this,Login.class));
        }

        FirebaseUser user= firebaseAuth.getCurrentUser();

        // textViewUserEmail=(TextView)findViewById(R.id.textViewUserEmail);
        //textViewUserEmail.setText("--"+user.getEmail());


        ButtonSell=(ImageButton) findViewById(R.id.ButtonSell);
        ButtonBid=(ImageButton) findViewById(R.id.Buttonbid);

        buttonLogout =(ImageButton)findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(this);
    }


    public  void  send(View view ){

        Intent ss=new Intent(this,SellActivity.class);
        startActivity(ss);

    }

    public  void  sendmsg( View view ){
        Intent bb=new Intent(this,BidActivity.class);
        startActivity(bb);

    }



    public  void  myp(View view){
        Intent ii=new Intent(this,MyProducts.class);
        startActivity(ii);
    }

    @Override
    public void onClick(View view) {


        if (view == buttonLogout) {

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, Login.class));
        }

                /*if(view==ButtonBid){

                    startActivity(new Intent(this, BidActivity.class));


                }*/


    }

}







