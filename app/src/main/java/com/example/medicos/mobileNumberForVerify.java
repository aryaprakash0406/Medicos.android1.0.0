package com.example.medicos;

import static android.service.controls.ControlsProviderService.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;


public class mobileNumberForVerify extends AppCompatActivity {
    Button getOtp;
    EditText real_otp;
    ProgressBar progressBar;

    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private static final String TAG = "MAIN_TAG";
    private FirebaseAuth firebaseAuth;
    private ProgressDialog pd;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_number_for_verify);
        getOtp = findViewById(R.id.getOtp);
        real_otp = findViewById(R.id.real_otp);
        progressBar = findViewById(R.id.progressBar);

        phone = real_otp.getText().toString().trim();

        getOtp.setOnClickListener(new View.OnClickListener() {
            private FirebaseAuth mAuth;

            @Override
            public void onClick(View v) {
                if (!real_otp.getText().toString().trim().isEmpty()) {
                    if ((real_otp.getText().toString().trim()).length() == 10) {

//
//                        progressBar.setVisibility(View.VISIBLE);
//                        getOtp.setVisibility(View.INVISIBLE);
                        phone = "+91" + real_otp.getText().toString().trim();

                        firebaseAuth = FirebaseAuth.getInstance();
                        PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                                .setPhoneNumber(phone)       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(mobileNumberForVerify.this)                 // Activity (for callback binding)
                                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {


                                        progressBar.setVisibility(View.VISIBLE);
                                        getOtp.setVisibility(View.GONE);


                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {

                                        Toast.makeText(mobileNumberForVerify.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();

                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        super.onCodeSent(backendOtp, forceResendingToken);
                                        progressBar.setVisibility(View.GONE);
                                        getOtp.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(mobileNumberForVerify.this, validateOTP.class);
                                        intent.putExtra("mobileNo", real_otp.getText().toString());
                                        intent.putExtra("backendOtp", backendOtp);
                                        startActivity(intent);
                                    }
                                })
                                .build();
                        PhoneAuthProvider.verifyPhoneNumber(options);

                    } else {
                        Toast.makeText(mobileNumberForVerify.this, "Enter Correct Number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(mobileNumberForVerify.this, "Enter Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}