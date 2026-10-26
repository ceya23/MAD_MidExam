package com.example.week04;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfigActivity extends AppCompatActivity {

    Button buttonSave;
    EditText textName;
    CheckBox isDarkMode;
    boolean isValid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_config);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        buttonSave = findViewById(R.id.buttonSave);
        textName = findViewById(R.id.textName);
        isDarkMode = findViewById(R.id.isDarkMode);
        String name = Preferences.getName(getApplicationContext());
        boolean isDarkModeOn = Preferences.getIsDarkMode(getApplicationContext());
        textName.setText(name);
        isDarkMode.setChecked(isDarkModeOn);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validateData();
                if (isValid) {
                    backToMain();
                }
            }
        });
    }
    private void validateData() {
        textName.setError(null);
        View focus = null;
        String name = textName.getText().toString();

        if (TextUtils.isEmpty(name)) {
            textName.setError("Name is required");
            focus = textName;
        }

        if (focus != null) {
            focus.requestFocus();
            isValid = false;
        } else {
            isValid = true;
        }
    }

    private void backToMain() {
        // set data and move to MainActivity
        Preferences.setName(getApplicationContext(), textName.getText().toString());
        Preferences.setIsDarkMode(getApplicationContext(), isDarkMode.isChecked());
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}
