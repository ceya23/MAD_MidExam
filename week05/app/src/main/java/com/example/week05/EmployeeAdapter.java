package com.example.week05;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    List<EmployeeModel> employee;
    Context context;
    DBHelper dbHelper;

    public EmployeeAdapter(List<EmployeeModel> employee, Context context) {
        this.employee = employee;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(
                R.layout.employee_item_list,
                parent,
                false
        );

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull final ViewHolder holder,
            @SuppressLint("RecyclerView") final int position
    ) {
        final EmployeeModel employeeModel = employee.get(position);

        holder.textViewID.setText(
                Integer.toString(employeeModel.getId())
        );

        holder.editTextName.setText(employeeModel.getName());
        holder.editTextEmail.setText(employeeModel.getEmail());
        holder.editTextAddress.setText(employeeModel.getAddress());
        holder.editTextPhone.setText(employeeModel.getPhone());

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strName = holder.editTextName.getText().toString().trim();
                String strEmail = holder.editTextEmail.getText().toString().trim();
                String strAddress = holder.editTextAddress.getText().toString().trim();
                String strPhone = holder.editTextPhone.getText().toString().trim();

                if (strName.isEmpty() || strEmail.isEmpty()
                        || strAddress.isEmpty() || strPhone.isEmpty()) {

                    if (context instanceof Activity) {
                        android.widget.Toast.makeText(
                                context,
                                "Enter all data",
                                android.widget.Toast.LENGTH_SHORT
                        ).show();
                    }

                    return;
                }

                EmployeeModel updatedEmployee = new EmployeeModel(
                        employeeModel.getId(),
                        strName,
                        strEmail,
                        strAddress,
                        strPhone
                );

                dbHelper.updateEmployee(updatedEmployee);

                notifyDataSetChanged();

                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper.deleteEmployee(employeeModel.getId());

                employee.remove(holder.getAdapterPosition());

                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewID;
        EditText editTextName, editTextEmail, editTextAddress, editTextPhone;
        Button btnEdit, btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.textViewID);
            editTextName = itemView.findViewById(R.id.editTextName);
            editTextEmail = itemView.findViewById(R.id.editTextEmail);
            editTextAddress = itemView.findViewById(R.id.editTextAddress);
            editTextPhone = itemView.findViewById(R.id.editTextPhone);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}