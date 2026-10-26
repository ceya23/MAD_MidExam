package com.example.week02;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "INTENT_LOG";
    public static final String NUMBER = "KEY_NUMBER";
    EditText textData;
    Button buttonSend, buttonAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textData = findViewById(R.id.textData);
        buttonSend = findViewById(R.id.buttonSend);
        buttonAbout = findViewById(R.id.buttonAbout);

        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutIntent = new Intent(getApplicationContext(), AboutActivity.class);
                // startActivities(new Intent[]{aboutIntent});
                startActivity(aboutIntent);
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = Integer.parseInt(textData.getText().toString());
                Log.d(TAG, "Number entered " + number);
                if (number < 0) {
                    Toast.makeText(getApplicationContext(), "Please enter positive integer", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent intent = new Intent(getApplicationContext(), NumberActivity.class);
                intent.putExtra(NUMBER, number);
                startActivity(intent);
            }
        });
    }
}
