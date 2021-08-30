package com.example.medicos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicos.databinding.ActivityWritePrescriptionBinding;


public class WritePrescription extends AppCompatActivity {

    private ActivityWritePrescriptionBinding binding;
    private static final String[] Medicine = new String[]{
            "hh", "vv"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWritePrescriptionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        ArrayAdapter<String> search_adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, Medicine);

    }

    public void OnAddTest(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View addMoreTest = inflater.inflate(R.layout.advice_test_box, null);
        binding.addMoreAdviceTest.addView(addMoreTest, binding.addMoreAdviceTest.getChildCount() - 1);
    }

    public void OnDeleteTest(View v) {
        binding.addMoreAdviceTest.removeView((View) v.getParent());

    }

    public void OnAddMedicine(View v) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        final View addMoreTest = inflater.inflate(R.layout.prescription_box, null);
        binding.addMoreMedicine.addView(addMoreTest, binding.addMoreMedicine.getChildCount() - 1);
    }

    public void OnDeleteMedicine(View v) {
        binding.addMoreMedicine.removeView((View) v.getParent());
    }

}