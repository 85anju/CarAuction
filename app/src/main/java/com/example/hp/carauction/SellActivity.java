package com.example.hp.carauction;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


public class SellActivity extends AppCompatActivity {

    private EditText TextcarComp;
    private EditText Textcarname;
    private EditText TextAvg;
    private EditText Textprice;
    private EditText Textbuydate;

    private EditText Texttypeuser;
    // private  EditText Textbid;
    private Button buttonAdd;

    //private TextView TextMymailinsell;

     public String vikreta;
    public static int limit;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseproduct;

    Intent ss = getIntent();
    //private  Long boli;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);


        Textcarname = (EditText) findViewById(R.id.Textcarname);
        TextcarComp = (EditText) findViewById(R.id.TextcarComp);
        TextAvg = (EditText) findViewById(R.id.TextAvg);
        Textbuydate = (EditText) findViewById(R.id.Textbuydate);
        Texttypeuser = (EditText) findViewById(R.id.Texttypeuser);

        Textprice = (EditText) findViewById(R.id.Textprice);
        // Textbid=(EditText)findViewById(R.id.Textbid);
        buttonAdd = (Button) findViewById(R.id.buttonAdd);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        vikreta = user.getEmail();


        // TextMymailinsell=(TextView)findViewById(R.id.TextMymailinsell);
        // TextMymailinsell.setText("--"+user.getEmail());


        databaseproduct = FirebaseDatabase.getInstance().getReference("product");

        buttonAdd.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                addproduct();
            }
        });




        databaseproduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot productSnapshot : dataSnapshot.getChildren()) {


                    if(productSnapshot.getValue(Product.class).getSoldout()==0 && vikreta.equals(productSnapshot.getValue(Product.class).getSellerid()))

                        limit=1;
                     else
                        limit=0;


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    /*private void showUpdateDialog(String productid,String productname){

        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(this);
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView=inflater.inflate(R.layout.activity_update,null);

        dialogBuilder.setView(dialogView);
        final  EditText Editupdating=(EditText)dialogView.findViewById(R.id.Editupdating);
        final Button ButtonUpdate=(Button)dialogView.findViewById(R.id.ButtonUpdate);

        dialogBuilder.setTitle("Adding Your  bid"+productid);

    }*/


    private  void  addproduct(){
        String comp=TextcarComp.getText().toString().trim();
        String  name = Textcarname.getText().toString().trim();
        String  dist=TextAvg.getText().toString().trim();
        String  kharedi=Textbuydate.getText().toString().trim();
        String haat=Texttypeuser.getText().toString().trim();

        String qqqq= Textprice.getText().toString().trim();



        if(TextUtils.isEmpty(comp)|| TextUtils.isEmpty(name)||TextUtils.isEmpty(dist)||TextUtils.isEmpty(kharedi)||TextUtils.isEmpty(haat)||TextUtils.isEmpty(qqqq)){
            Toast.makeText(this, "ALL fields are compulsory ", Toast.LENGTH_SHORT).show();

            return;
        }

        Long kimmat=Long.parseLong(qqqq);
        Long boli=kimmat;


        int soldflag=0;
        int bidflag=0;
        String  b1=new String();








        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        String sellid = user.getEmail();


        if(!TextUtils.isEmpty(name)){

            if(limit!=1) {
                String id = databaseproduct.push().getKey();

                Product product = new Product(id, comp, name, dist, kharedi, haat, kimmat, sellid, b1, boli, soldflag, bidflag);
                databaseproduct.child(id).setValue(product);

                Toast.makeText(this, "Product added ", Toast.LENGTH_LONG).show();
            }
            else if(limit==1){


                        AlertDialog.Builder builder = new AlertDialog.Builder(SellActivity.this);
                        builder.setMessage("Make sure your previous car has been sold out " +
                                " STOP THE BID FOR YOUR PREVIOUS CAR           ")
                                .setNegativeButton("Cancel", null);

                        AlertDialog alert = builder.create();
                        alert.setTitle("WARNING");
                        alert.show();

            }

        }else{
            Toast.makeText(this,"You must enter a product name", Toast.LENGTH_SHORT).show();

        }
    }
}
