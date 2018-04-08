package com.example.hp.carauction;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
public class MyProducts extends AppCompatActivity {


    DatabaseReference databaseproduct;
    FirebaseAuth firebaseAuth;
    private TextView sellname;
    private TextView sellcomp;
    private  TextView sellcurbid;

    String myname;
    String myproductid;
    String mycomp;
    String mybider;
    long bid;
    public  static  int anybid;

    Button buttonStop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_products);

        Intent ii=getIntent();

        sellcomp=(TextView)findViewById(R.id.sellcomp);
        sellname=(TextView)findViewById(R.id.sellname);
        sellcurbid=(TextView)findViewById(R.id.sellcurbid);

        databaseproduct= FirebaseDatabase.getInstance().getReference("product");
        firebaseAuth=FirebaseAuth.getInstance();
        FirebaseUser user= firebaseAuth.getCurrentUser();

        final String seller=user.getEmail();

        databaseproduct.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for (final DataSnapshot productSnapshot: dataSnapshot.getChildren()   )   {

                    String allseller=productSnapshot.getValue(Product.class).getSellerid();

                    if(seller.equals(allseller)){



                        myproductid=productSnapshot.getValue(Product.class).getProductid();
                        myname=productSnapshot.getValue(Product.class).getProductname();
                        mycomp= productSnapshot.getValue(Product.class).getProcompany();

                        long bid=productSnapshot.getValue(Product.class).getBid1();
                          anybid=productSnapshot.getValue(Product.class).getBiddone();

                        String xcvb=Long.toString(bid);


                        sellname.setText(myname);
                        sellcomp.setText(mycomp);
                        sellcurbid.setText(xcvb);



                        buttonStop = (Button) findViewById(R.id.buttonStop);
                        buttonStop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                mybider=productSnapshot.getValue(Product.class).getB1();
                                //Toast.makeText(MyProducts.this,"Your Product has been sold out to", Toast.LENGTH_SHORT).show();
                                //Toast.makeText(MyProducts.this,mybider, Toast.LENGTH_SHORT).show();



                                databaseproduct = FirebaseDatabase.getInstance().getReference("product").child(myproductid).child("soldout");
                                databaseproduct.setValue(1);
                                //Toast.makeText(MyProducts.this, "BIDDING DONE--SOLD OUT ", Toast.LENGTH_LONG).show();
                               /* if(anybid==0){
                                    mybider="NO ONE BIDDED";
                                }*/

                                AlertDialog.Builder builder = new AlertDialog.Builder(MyProducts.this);
                                builder.setMessage("Bid Stoped" +
                                        "  Your car "  + myname+" has been sold out to  "+
                                            mybider)
                                        .setNegativeButton("ok", null);

                                AlertDialog alert = builder.create();
                                alert.setTitle("DONE");
                                alert.show();






                            }
                        });
                    }



                }


            }





            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}

