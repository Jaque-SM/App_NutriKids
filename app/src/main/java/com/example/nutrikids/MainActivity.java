package com.example.nutrikids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler hand=new Handler();
        hand.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrar_login();
            }
        },2000);

    }

    public void mostrar_login(){
        if (auth != null) {
            Intent tent = new Intent(this, PacienteActivity.class);
            startActivity(tent);
            Toast.makeText(getApplicationContext(), "Bem vindo de volta: \n" +
                    auth.getEmail()+ "Doutor :)", Toast.LENGTH_LONG).show();
        }
        else {
            Intent tent=new Intent (this, LoginActivity.class);
            startActivity(tent);

        }

    }

}

