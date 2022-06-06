package com.example.nutrikids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InicioActivity extends AppCompatActivity {

    private FirebaseAuth user;
    private FirebaseAuth nutri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela2_escolhar_activity);

        user = FirebaseAuth.getInstance();
        nutri= FirebaseAuth.getInstance();


    }
    public void LoginNutri(View view) {
        FirebaseUser correntuser = nutri.getCurrentUser();
            if (correntuser != null) {
                Toast.makeText(getApplicationContext(), "Bem vindo de volta Nutri: \n" +
                        correntuser.getDisplayName() + "; )", Toast.LENGTH_LONG).show();
                         Intent tent = new Intent(this, ListaCardapioActivity.class);
                         startActivity(tent);
            } else {
                Intent tent = new Intent(this, LoginActivity.class);
                startActivity(tent);
                finish();
            }


    }

    public void LoginResponsavel(View view) {
        FirebaseUser correntuser = user.getCurrentUser();
        if (correntuser != null) {
            Intent tent = new Intent(this, PacienteActivity.class);
            startActivity(tent);
            Toast.makeText(getApplicationContext(), "Bem vindo de volta: \n" +
                    correntuser.getEmail()+ "!", Toast.LENGTH_LONG).show();
        }
        else {
            Intent tent=new Intent(this, LoginActivity.class);
            startActivity(tent);
            finish();
        }

    }
}
