package com.example.dai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Forum extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forum);

        //BUTTON SECTION

        ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn2);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
                overridePendingTransition(R.anim.go_up,R.anim.go_down);
            }
        });

       /*findViewById(R.id.returnBtn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });*/
    }
    public void onBackPressed(){
        super.onBackPressed();
        overridePendingTransition(R.anim.go_up,R.anim.go_down);
    }
}