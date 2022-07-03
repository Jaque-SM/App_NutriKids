package com.example.nutrikids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nutrikids.modelo.Cardapio;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class CadastroCardapioActivity extends AppCompatActivity {

    private EditText email_paciente;
    private EditText cafe_manha;
    private EditText lanche_manha;
    private EditText almoco;
    private EditText lanche_tarde;
    private EditText jantar;


    static String email;
    private FirebaseFirestore bd;
    private FirebaseAuth mAuth;
    private String testar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cardapio);


        email_paciente=findViewById(R.id.email_paciente);


        bd=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();

    }

    public void AddCardapio(View view){
        Cardapio card=new Cardapio();

        email=email_paciente.getText().toString();
        card.setCafe_manha(cafe_manha.getText().toString());
        card.setLanche_manha(lanche_manha.getText().toString());
        card.setAlmoco(almoco.getText().toString());
        card.setLanche_tarde(lanche_tarde.getText().toString());
        card.setJantar(jantar.getText().toString());

        Log.d(testar, "entrou aki");

        bd.collection("Cardapios").document(email).set(card)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(CadastroCardapioActivity.this, "Cardapio cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(CadastroCardapioActivity.this, ListaCardapioActivity.class));
                    }
                });

    }



}