package com.example.nutrikids;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PacienteActivity extends AppCompatActivity implements View.OnClickListener {
    String texto;

    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        btn=findViewById(R.id.button_cardapio);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d(texto,"entrou");
        if (view.getId()==R.id.button_cardapio) {
            getSupportFragmentManager().beginTransaction().replace(R.id.card_pio_pe, new CardapioFragment()).commit();
            btn.setVisibility(View.INVISIBLE);
        }
    }
    public void logaut(View view) {
        FirebaseAuth.getInstance().signOut();
        Intent i=new Intent(this, LoginActivity.class);
        startActivity(i);

    }


}