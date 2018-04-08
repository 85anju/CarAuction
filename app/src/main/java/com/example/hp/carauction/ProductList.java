package com.example.hp.carauction;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by hp on 2/10/17.
 */


public class ProductList extends ArrayAdapter<Product> {

    private Activity context;
    List<Product> products;
    DatabaseReference databaseproduct;
    private FirebaseAuth firebaseAuth;

    private TextView copy;
    private TextView pname;
    private TextView pcomp;
    private TextView pbid;
    private EditText pid;
    private TextView pavg;
    private TextView phand;

    /*private Button CheckBid;
    private EditText YourBid;
    private int position;
    private TextView just;
*/

    public ProductList(Activity context, List<Product> products) {
        super(context, R.layout.list_layout, products);
        this.context = context;
        this.products = products;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //      this.position = position;
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);


        TextView copy=(TextView)listViewItem.findViewById(R.id.copy);
        TextView pname = (TextView) listViewItem.findViewById(R.id.pname);
        TextView pcomp = (TextView) listViewItem.findViewById(R.id.pcomp);
        TextView pbid = (TextView) listViewItem.findViewById(R.id.pbid);
        EditText  pid=(EditText)listViewItem.findViewById(R.id.pid);
        TextView phand = (TextView) listViewItem.findViewById(R.id.phand);
        TextView pavg = (TextView) listViewItem.findViewById(R.id.pavg);




        databaseproduct = FirebaseDatabase.getInstance().getReference("product");
        Product product = products.get(position);

        pname.setText("car name  "+product.getProductname());
        // pid.setText(product.getProductid());
        pid.setText(product.getProductid(), TextView.BufferType.EDITABLE);

        pcomp.setText("car brand  "+product.getProcompany());


        phand.setText(product.getTypehand()+"  hand vehicle");
        pavg.setText("car avg "+product.getAvg());

        Long ggg =product.getBid1();
        String ccc=ggg.toString().trim();
        pbid.setText("current bid  "+ccc);
        pbid.setText(product.getBid1().toString().trim());


        //       databaseproduct.child(product.getProductid()).child("bid1").setValue("99999999");


        //String hhh = product.getBid1().toString();
        //CurrentBid.setText(hhh);

        return listViewItem;

    }
}