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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class validateOTP extends AppCompatActivity {
    EditText real_otp;
    Button submit;
    TextView resendOtp,mobileNo;

    private PhoneAuthProvider.ForceResendingToken forceResendingToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    private String mVerificationId;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validate_otp);
        real_otp = findViewById(R.id.real_otp);
        submit = findViewById(R.id.submit);
        resendOtp = findViewById(R.id.resendOtp);
        mobileNo = findViewById(R.id.mobileNo);

        mobileNo.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobileNo")
        ));
        String otpCode = getIntent().getStringExtra("mobileNo");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!real_otp.getText().toString().trim().isEmpty()) {
                    if ((real_otp.getText().toString().trim()).length() == 6) {
//                    Toast.makeText(validateOTP.this, "verifying OTP", Toast.LENGTH_SHORT).show();
//                        Intent intent = new Intent(validateOTP.this, UserInputActivity.class);
//                        startActivity(intent);

                        if (otpCode != null) {

                            String enteredOtp = real_otp.getText().toString();

                            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpCode, enteredOtp);
                            signInWithPhoneAuthCredential(credential);
                        }

                        } else {
                            Toast.makeText(validateOTP.this, "OTP Must Be 6 Digit", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(validateOTP.this, "OTP Must Be Non-empty and 6 Digit", Toast.LENGTH_SHORT).show();
                }
            }

            private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
                firebaseAuth.signInWithCredential(credential).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        String phone = firebaseAuth.getCurrentUser().getPhoneNumber();
                        Toast.makeText(validateOTP.this, "welcome", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(validateOTP.this, UserInputActivity.class));
                    }
                });
            }
        });

        resendOtp.setOnClickListener(new View.OnClickListener() {
            private FirebaseAuth mAuth;
            @Override
            public void onClick(View v) {
                firebaseAuth = FirebaseAuth.getInstance();

                PhoneAuthOptions options =
                        PhoneAuthOptions.newBuilder(mAuth)
                                .setPhoneNumber(getIntent().getStringExtra("mobileNo"))       // Phone number to verify
                                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                                .setActivity(validateOTP.this)                 // Activity (for callback binding)
                                .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                                .build();
                PhoneAuthProvider.verifyPhoneNumber(options);

                mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                        Toast.makeText(validateOTP.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(@NonNull String newbackendOtp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(newbackendOtp, forceResendingToken);
                        newbackendOtp=otpCode;
                        Toast.makeText(validateOTP.this, "successfully send", Toast.LENGTH_SHORT).show();
                    }
                };
            }
        });
    }
}