package com.example.hp.carauction;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyBid extends AppCompatActivity {


    private EditText Editgiveid;
    private EditText Editgivebid;
    DatabaseReference databaseproduct;
    long myprobid;
    long tempbid;
    private FirebaseAuth firebaseAuth;
    private Button BidAdd;
    private TextView textpname;
    private TextView textpcomp;


    int flag;
    private int exist;


    String tempid;
    String tempcomp;
    String tempname;
    String  tempb1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bid);

        Editgivebid = (EditText) findViewById(R.id.Editgivebid);
        Editgiveid = (EditText) findViewById(R.id.Editgiveid);
        textpname = (TextView) findViewById(R.id.textpname);
        textpcomp = (TextView) findViewById(R.id.textpcomp);


        BidAdd = (Button) findViewById(R.id.BidAdd);
        BidAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addbid();

            }
        });
    }


    public void addbid() {

        databaseproduct = FirebaseDatabase.getInstance().getReference("product");


        firebaseAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = firebaseAuth.getCurrentUser();

        String curbidder = user.getEmail();
        firebaseAuth = FirebaseAuth.getInstance();

        final String myproid = Editgiveid.getText().toString().trim();

        String xxx = Editgivebid.getText().toString().trim();

        if (TextUtils.isEmpty(myproid) || TextUtils.isEmpty(xxx)) {
            Toast.makeText(this, "ALL fields are compulsory ", Toast.LENGTH_SHORT).show();

            return;
        }


        try {
            myprobid = Long.parseLong(xxx);

        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());

        }


        databaseproduct.addValueEventListener(new ValueEventListener() {


            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                exist = 0;


                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {

                    tempid = productSnapshot.getValue(Product.class).getProductid().toString();
                    if (myproid.equals(tempid)) {

                        exist = 1;
                        tempname = productSnapshot.getValue(Product.class).getProductname();
                        tempbid = productSnapshot.getValue(Product.class).getBid1();
                        tempcomp = productSnapshot.getValue(Product.class).getProcompany();
                        flag = productSnapshot.getValue(Product.class).getSoldout();
                        tempb1=productSnapshot.getValue(Product.class).getB1();

                        // Toast.makeText(MyBid.this, "checked myproid and tempid", Toast.LENGTH_SHORT).show();
                        break;

                    }

                }


                textpname.setText(tempname);
                textpcomp.setText(tempcomp);


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        if (flag == 0) {

            if (exist == 1)
            {
                if (myprobid >=tempbid) {


                    databaseproduct = FirebaseDatabase.getInstance().getReference("product").child(myproid).child("bid1");
                    databaseproduct.setValue(myprobid);


                    databaseproduct = FirebaseDatabase.getInstance().getReference("product").child(myproid).child("b1");
                    databaseproduct.setValue(curbidder);

                    AlertDialog.Builder builder = new AlertDialog.Builder(MyBid.this);
                    builder.setMessage("BID Updated as "
                            +myprobid+"by "+curbidder )
                            .setNegativeButton("OK", null);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Updated ");
                    alert.show();

                  //  Toast.makeText(this, "Bid updated ", Toast.LENGTH_LONG).show();

                } else if (myprobid < tempbid) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(MyBid.this);
                    builder.setMessage("Your bid is less than the previos bid ")
                            .setNegativeButton("OK", null);

                    AlertDialog alert = builder.create();
                    alert.setTitle("Bid more.... ");
                    alert.show();

                    //Toast.makeText(this, "Your bid is less than previous bid", Toast.LENGTH_LONG).show();
                }

            }
            else
                Toast.makeText(this, "Press Once Again::::::::::", Toast.LENGTH_LONG).show();


        }
        else {

            AlertDialog.Builder builder = new AlertDialog.Builder(MyBid.this);
            builder.setMessage("Product has been sold out to "+tempb1)
                    .setNegativeButton("OK", null);

            AlertDialog alert = builder.create();
            alert.setTitle("YOU CANT BID ");
            alert.show();

            // Toast.makeText(this, "Product has been sold out...u cant update", Toast.LENGTH_LONG).show();

        }

    }
}







