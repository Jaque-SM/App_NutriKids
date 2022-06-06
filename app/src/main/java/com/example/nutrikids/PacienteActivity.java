package com.example.nutrikids;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class PacienteActivity extends AppCompatActivity implements View.OnClickListener {

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
        if (view.getId()==R.id.button_cardapio){
            getSupportFragmentManager().beginTransaction().replace(R.id.button_cardapio, new CardapioFragment()).commit();
            btn.setVisibility(View.GONE);
        }

    }




}