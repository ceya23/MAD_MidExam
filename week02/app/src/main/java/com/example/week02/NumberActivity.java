package com.example.week02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NumberActivity extends AppCompatActivity {

    TextView textNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_number);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textNumber = findViewById(R.id.textNumber);

        Intent intent = getIntent();
        int number = intent.getIntExtra(MainActivity.NUMBER, 0);
        Log.d(MainActivity.TAG, "Number from intent: " + number);

        String message = "";
        if (number % 2 == 0) {
            message = "Number " + number + " is Even";
        } else {
            message = "Number " + number + " is Odd";
        }

        textNumber.setText(message);
    }
}
