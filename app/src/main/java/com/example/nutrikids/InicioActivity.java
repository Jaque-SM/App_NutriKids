package com.example.nutrikids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioActivity extends AppCompatActivity {

     FirebaseUser auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2_escolhar_activity);


    }

    public void LoginNutri(View view) {
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

    public void LoginResponsavel(View view) {
        if (auth != null) {
            Intent tent = new Intent(this, PacienteActivity.class);
            startActivity(tent);
            Toast.makeText(getApplicationContext(), "Bem vindo de volta: \n" +
                    auth.getEmail()+ "!", Toast.LENGTH_LONG).show();
        }
        else {
            Intent tent=new Intent (this, LoginActivity.class);
            startActivity(tent);

        }


    }
}
