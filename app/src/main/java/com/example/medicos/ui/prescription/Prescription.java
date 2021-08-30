package com.example.medicos.ui.prescription;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.medicos.R;
import com.example.medicos.WritePrescription;
import com.example.medicos.databinding.FragmentPrescriptionBinding;
import com.example.medicos.mobileNumberForVerify;
import com.example.medicos.patientDetails;

public class Prescription extends Fragment {

    private Button getotp;
    private EditText real_Otp;
    private FragmentPrescriptionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentPrescriptionBinding.inflate(inflater, container, false);
        View view = binding.getRoot();



        getotp = view.findViewById(R.id.getOtp);
        real_Otp = view.findViewById(R.id.real_otp);

        String patient_mobileNumber = real_Otp.getText().toString();
        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!real_Otp.getText().toString().trim().isEmpty()) {
                    if ((real_Otp.getText().toString().trim()).length() == 10) {

                        Intent intent = new Intent(getActivity(), patientDetails.class);
                        startActivity(intent);

                    } else {
                        Toast.makeText(getActivity(), "Enter Correct Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}