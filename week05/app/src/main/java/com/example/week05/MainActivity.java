package com.example.week05;

import android.content.Intent;
import android.os.Bundle;
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

    Button btnAdd, btnView;
    EditText editTextName, editTextEmail, editTextAddress, editTextPhone;

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

        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextAddress = findViewById(R.id.editTextAddress);
        editTextPhone = findViewById(R.id.editTextPhone);

        btnAdd = findViewById(R.id.btnAdd);
        btnView = findViewById(R.id.btnView);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = editTextName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                if (name.isEmpty() || email.isEmpty()
                        || address.isEmpty() || phone.isEmpty()) {

                    Toast.makeText(
                            MainActivity.this,
                            "Enter all data",
                            Toast.LENGTH_SHORT
                    ).show();

                } else {

                    DBHelper dbHelper = new DBHelper(MainActivity.this);

                    EmployeeModel employeeModel =
                            new EmployeeModel(name, email, address, phone);

                    dbHelper.addEmployee(employeeModel);

                    Toast.makeText(
                            MainActivity.this,
                            "Add Successful",
                            Toast.LENGTH_SHORT
                    ).show();

                    finish();
                    startActivity(getIntent());
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        MainActivity.this,
                        ViewEmployee.class
                );

                startActivity(intent);
            }
        });
    }
}