package com.example.medicos.ui.userprofile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.medicos.R;
import com.example.medicos.databinding.FragmentUserprofileBinding;

public class Userprofile extends Fragment {

    TextView username,birthdate,user_gender,user_contact_number_profile;
    private FragmentUserprofileBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserprofileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        View view = inflater.inflate(R.layout.fragment_userprofile,container,false);

//        username = (TextView) view.findViewById(R.id.UserName);
//        birthdate = (TextView) view.findViewById(R.id.dateOfBirth);
//        user_gender = (TextView) view.findViewById(R.id.Usergender);
//        user_contact_number_profile = (TextView) view.findViewById(R.id.UserContactNumberProfile);


//        String name=Userprofile.this.getArguments().getString("name").toString();
//        String dob=Userprofile.this.getArguments().getString("dateOfbirth").toString();
//        String login_as=Userprofile.this.getArguments().getString("loginas").toString();
//        String gender=Userprofile.this.getArguments().getString("gender").toString();
//
//        username.setText(name);
//        birthdate.setText(dob);
//        user_gender.setText(login_as);
//        user_contact_number_profile.setText(gender);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}