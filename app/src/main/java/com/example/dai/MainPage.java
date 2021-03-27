package com.example.dai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;

public class MainPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);



        //BUTTON SECTION
        Button forumBtn = (Button) findViewById(R.id.forumBtn);
        forumBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Forum.class);
                startActivity(startIntent);
                overridePendingTransition(R.anim.go_up,R.anim.go_down);
            }
        });

        Button suggestion = (Button) findViewById(R.id.suggestionBtn);
        suggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Suggestion.class);
                startActivity(startIntent);
            }
        });

        Button historyBtn = (Button) findViewById(R.id.historyBtn);
        historyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), History.class);
                startActivity(startIntent);
            }
        });

        Button profileBtn = (Button) findViewById(R.id.profileBtn);
        profileBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Profile.class);
                startActivity(startIntent);
            }
        });
        //END OF BUTTON SECTION




    }
}