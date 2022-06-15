package com.example.nutrikids;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Cadastro_Activity extends AppCompatActivity {

    static  EditText nome_completo;
    static  EditText email_barra;
    static  EditText senha_barra;
    static  EditText confirme_senha_barra;

    static String nome;
    static String email;
    static String senha;
    static String confirme_senha;


    public String testar;
    Map<String, Object> user;
    FirebaseFirestore bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

       user=new HashMap<>();

        bd=FirebaseFirestore.getInstance();

        email_barra=findViewById(R.id.email_barra);
        senha_barra=findViewById(R.id.senha_barra);
        confirme_senha_barra=findViewById(R.id.confirme_barra);
        nome_completo=findViewById(R.id.nome_completo);

    }
    public String Nulidade() {

        senha=senha_barra.getText().toString();

        Log.d(testar, "Entrou em nulidade");

        if (TextUtils.isEmpty(nome)){
            Toast.makeText(this, "O campo nome não pode ser nulo", Toast.LENGTH_SHORT).show();
            return null;
       }
        if (TextUtils.isEmpty(email)){
            Toast.makeText(this,"O campo email não pode ser nulo", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (TextUtils.isEmpty(senha)){
            Toast.makeText(this,"O campo senha não pode ser nulo", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (TextUtils.isEmpty(confirme_senha)||!confirme_senha.equals(senha)){
            Toast.makeText(this,"O campo senha não esta igual a referencia acima",
                    Toast.LENGTH_SHORT).show();
            return null;

        }
        return "Nada errado em campos";

    }

    public void AddCadastro(View view){
        nome=nome_completo.getText().toString();
        email=email_barra.getText().toString();
        senha=senha_barra.getText().toString();
        confirme_senha=confirme_senha_barra.getText().toString();

        if (Nulidade()!=null) {


            user.put("Nome", nome);
            user.put("Email", email);
            user.put("Senha", senha);
            user.put("Confirme_Senha", confirme_senha);

            Intent intent=new Intent(this, LoginActivity.class);

            bd.collection("/Nutricionista").document("nutricionista")
                    .set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {

                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(testar, "Dado add com sucesso");
                            Toast.makeText(Cadastro_Activity.this, "Dados cadastrados", Toast.LENGTH_LONG).show();
                            //startActivity(intent);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(testar, "Deu erro aki");

                        }
                    });
        }

        }


}








