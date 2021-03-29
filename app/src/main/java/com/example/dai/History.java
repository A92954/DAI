package com.example.dai;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showPopup(View v){
        TextView txtclose;
        Button btn;
        myDialog.setContentView(R.layout.popup_acti);
        btn = (Button) myDialog.findViewById(R.id.btn);
        myDialog.show();
        Display display =((WindowManager)getSystemService(History.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        myDialog.getWindow().setLayout((6*width + 1)/7,(4*height + 1)/5);
    }

}