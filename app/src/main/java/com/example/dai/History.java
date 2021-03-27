package com.example.dai;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class History extends AppCompatActivity {
    Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        myDialog = new Dialog(this);




        ImageButton returnBtn = (ImageButton) findViewById(R.id.return5Btn);
        returnBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), MainPage.class);
                startActivity(startIntent);
            }
        });

    }
    public void showPopup(View v){
        TextView txtclose;
        Button btn;
        myDialog.setContentView(R.layout.popup_acti);
        btn = (Button) myDialog.findViewById(R.id.btn);
        myDialog.show();
    }

}