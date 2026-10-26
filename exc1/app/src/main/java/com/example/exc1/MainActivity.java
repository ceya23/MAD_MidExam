package com.example.exc1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CheckBox checkOnline;
    Button buttonCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkOnline = findViewById(R.id.checkOnline);
        buttonCheck = findViewById(R.id.buttonCheck);

        buttonCheck.setOnClickListener(v -> {
            TextView toastText = new TextView(MainActivity.this);
            toastText.setPadding(32, 20, 32, 20);
            toastText.setTextColor(Color.WHITE);
            toastText.setTextSize(16);

            if (checkOnline.isChecked()) {
                toastText.setText("online");
                toastText.setBackgroundColor(Color.GREEN);
            } else {
                toastText.setText("offline");
                toastText.setBackgroundColor(Color.RED);
            }

            Toast toast = new Toast(MainActivity.this);
            toast.setView(toastText);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM, 0, 150);
            toast.show();
        });
    }
}