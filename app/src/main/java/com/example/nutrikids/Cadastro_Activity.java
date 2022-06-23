package com.example.nutrikids;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrikids.modelo.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class Cadastro_Activity extends AppCompatActivity {

    private EditText nome_completo;
    private EditText email_barra;
    private EditText senha_barra;
    private EditText confirme_senha_barra;

    private String nome;
    private String email;
    private String senha;
    private String confirme_senha;

    private FirebaseFirestore bd;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        bd = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        email_barra = findViewById(R.id.email_barra);
        senha_barra = findViewById(R.id.senha_barra);
        confirme_senha_barra = findViewById(R.id.confirme_barra);
        nome_completo = findViewById(R.id.nome_completo);

    }

    public void AddCadastro(View view) {
        nome = nome_completo.getText().toString();
        email = email_barra.getText().toString();
        senha = senha_barra.getText().toString();
        confirme_senha = confirme_senha_barra.getText().toString();

        if (Nulidade() != null) {

            User user = new User(null, nome, email, "nutri");

            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, task -> salvarNaColecao());

            fazerLogin();

        }

    }

    private void salvarNaColecao() {
        String uid = Objects.requireNonNull(mAuth.getCurrentUser()).getUid();

        User user = new User(uid, nome, email, "responsável");

        bd.collection("users").document(uid).set(user).addOnSuccessListener(unused -> {
            Snackbar.make(nome_completo, "Cadastrado na coleção", Snackbar.LENGTH_SHORT).show();
        });
        
    }

    private void fazerLogin() {
        mAuth.signInWithEmailAndPassword(email, senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Intent intent = new Intent(Cadastro_Activity.this, ListaCardapioActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public String Nulidade() {

        senha = senha_barra.getText().toString();

        Log.d("testar", "Entrou em nulidade");

        if (TextUtils.isEmpty(nome)) {
            Toast.makeText(this, "O campo nome não pode ser nulo", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "O campo email não pode ser nulo", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (TextUtils.isEmpty(senha)) {
            Toast.makeText(this, "O campo senha não pode ser nulo", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (TextUtils.isEmpty(confirme_senha) || !confirme_senha.equals(senha)) {
            Toast.makeText(this, "O campo senha não esta igual a referencia acima",
                    Toast.LENGTH_SHORT).show();
            return null;

        }
        return "Nada errado em campos";

    }


}








