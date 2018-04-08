package com.example.hp.carauction;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.hp.carauction.R.id.pid;

public class BidActivity extends AppCompatActivity  {

    ListView listViewProducts;

    public static final String Product_name="Productname";
    public static final String Product_comp="Productcomp";

    List<Product> products;
    private FirebaseAuth firebaseAuth;

    DatabaseReference databaseproduct;
    ImageButton Bstartbid;
    Button btncopytext;
    //public String vikreta;
    //  private TextView disp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bid);

        Intent bb = getIntent();


        listViewProducts = (ListView) findViewById(R.id.listViewProducts);
        products = new ArrayList<>();

        databaseproduct = FirebaseDatabase.getInstance().getReference("product");

        //  disp=(TextView)findViewById(R.id.disp);
        Bstartbid = (ImageButton) findViewById(R.id.Bstartbid);
        Bstartbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aaa = new Intent(BidActivity.this, MyBid.class);
                startActivity(aaa);
            }
        });


        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();


        //vikreta = user.getEmail();


        /*listViewProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Product product = products.get(i);
                Intent info = new Intent(getApplicationContext(), AddInfoActivity.class);

                info.putExtra(Product_name, product.getProductname());
                info.putExtra(Product_comp, product.getProcompany());
                startActivity(info);

            }
        }  );
        */


    }
    @Override
    protected void onStart() {
        super.onStart();
        databaseproduct.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                products.clear();
                for (DataSnapshot productSnapshot: dataSnapshot.getChildren()   )   {

                    Product product=productSnapshot.getValue(Product.class);
                    if(product.soldout==0) {

                        products.add(product);
                    }


                }

                ProductList  adapter=new ProductList(BidActivity.this,products);
                listViewProducts.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        } );


    }

    Intent  bb=getIntent();
}