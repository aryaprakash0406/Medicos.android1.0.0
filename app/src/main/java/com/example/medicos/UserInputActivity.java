package com.example.medicos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.medicos.databinding.ActivityUserInputBinding;
import com.example.medicos.ui.userprofile.Userprofile;

import java.util.jar.Attributes;

public class UserInputActivity extends AppCompatActivity {

    private Spinner spinner1,spinner2;
    private TextView Name,dateOfBirth;
    private Button usreInputButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);



        spinner1 = (Spinner)findViewById(R.id.LogInAs);
        spinner2 = (Spinner)findViewById(R.id.Gender);
        Name = findViewById(R.id.Name);
        dateOfBirth = findViewById(R.id.dateOfBirth);
        usreInputButton=findViewById(R.id.usreInputButton);



        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.LogIn_array, android.R.layout.simple_spinner_item);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.gender_array, android.R.layout.simple_spinner_item);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);



        usreInputButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Name.getText().toString().trim().isEmpty() & !dateOfBirth.getText().toString().trim().isEmpty()){
                    Intent intent=new Intent(UserInputActivity.this,MainActivity.class);
//                    intent.putExtra("name",Name.getText().toString());
//                    intent.putExtra("dateOfbirth",dateOfBirth.getText().toString());
//                    intent.putExtra("loginas",spinner1.toString());
//                    intent.putExtra("gender",spinner2.toString());

                    Bundle bundle = new Bundle();
                    bundle.putString("name", Name.getText().toString());
                    bundle.putString("dateOfbirth", dateOfBirth.getText().toString());
                    bundle.putString("loginas", String.valueOf(spinner1.getSelectedItem().toString()));
                    bundle.putString("gender", String.valueOf(spinner2.getSelectedItem().toString()));

                    //PASS OVER THE BUNDLE TO OUR FRAGMENT
                    Userprofile userprofile = new Userprofile();
                    userprofile.setArguments(bundle);

                    startActivity(intent);

                }else {
                    Toast.makeText(UserInputActivity.this, "Fill out all the blanck", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}