package com.example.week03;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class FragmentCheckbox extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;
    CheckBox checkChocolate, checkVanilla;
    TextView textResult;
    public FragmentCheckbox() {
        // Required empty public constructor
    }

    public static FragmentCheckbox newInstance(String param1, String param2) {
        FragmentCheckbox fragment = new FragmentCheckbox();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_checkbox, container, false);

        checkChocolate = view.findViewById(R.id.checkChocolate);
        checkVanilla = view.findViewById(R.id.checkVanilla);
        textResult = view.findViewById(R.id.textResult);

        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
                updateText();
            }
        };

        checkChocolate.setOnCheckedChangeListener(listener);
        checkVanilla.setOnCheckedChangeListener(listener);

        updateText();

        return view;
    }
    public void updateText() {
        List<String> selected = new ArrayList<>();
        if (checkChocolate.isChecked()) {
            selected.add("Chocolate");
        }

        if (checkVanilla.isChecked()) {
            selected.add("Vanilla");
        }

        if (!selected.isEmpty()) {
            String result = String.join(",", selected);
            textResult.setText(result);
        } else {
            textResult.setText("Please choose");
        }
    }
}
