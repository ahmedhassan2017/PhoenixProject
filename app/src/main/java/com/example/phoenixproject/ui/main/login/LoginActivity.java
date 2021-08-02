package com.example.phoenixproject.ui.main.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.phoenixproject.Models.LoginModel;
import com.example.phoenixproject.R;
import com.example.phoenixproject.ui.main.Home;

public class LoginActivity extends AppCompatActivity {
    Button button;
    loginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button = findViewById(R.id.BBBB);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, Home.class);
                startActivity(i);
            }
        });
        viewModel = new ViewModelProvider(this).get(loginViewModel.class);
        doLogin("login", "01063052494", "123456");


    }

    private void doLogin(String action, String phone, String password) {

        viewModel.doLogin(action, phone, password);
        viewModel.userMutableLiveData.observe(this, new Observer<LoginModel>() {
            @Override
            public void onChanged(LoginModel loginModel) {
                Toast.makeText(LoginActivity.this, loginModel.getId(), Toast.LENGTH_SHORT).show();
                Toast.makeText(LoginActivity.this, loginModel.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}