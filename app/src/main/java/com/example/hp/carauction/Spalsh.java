package com.example.hp.carauction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Spalsh extends AppCompatActivity {


    private  Button ButtonSpalsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spalsh);

        ButtonSpalsh = (Button) findViewById(R.id.ButtonSpalsh);


        ButtonSpalsh= (Button) findViewById(R.id.ButtonSpalsh);

        ButtonSpalsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Spalsh.this, Sign_up.class);
                startActivity(i);
            }
        });


    }
}


