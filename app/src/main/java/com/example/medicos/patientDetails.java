package com.example.medicos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.medicos.databinding.ActivityPatientDetailsBinding;

public class patientDetails extends AppCompatActivity {

    private ActivityPatientDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPatientDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.Gender.setAdapter(adapter);


        binding.patientDetailsSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!binding.PatientName.getText().toString().trim().isEmpty() & !binding.patientDateOfBirth.getText().toString().trim().isEmpty()) {

                    Intent intent = new Intent(patientDetails.this, WritePrescription.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(patientDetails.this, "Fields Must be non-Empty", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}