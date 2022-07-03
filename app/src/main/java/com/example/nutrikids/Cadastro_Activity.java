package com.example.nutrikids;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nutrikids.modelo.User;
import com.google.android.gms.tasks.OnFailureListener;
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

    static String nome;
    static String email;
    static String senha;
    static String confirme_senha;

    static  String tipo_selecionado;

    static FirebaseFirestore bd;
    static FirebaseAuth mAuth;
    static FirebaseUser currentUser;

    private String testar;

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

        tipo_selecionado = "";



    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()) {
            case R.id.tipo_nutri:
                if (checked)
                    this.tipo_selecionado="nutricionista";
                break;
            case R.id.tipo_paciente:
                if (checked)
                    this.tipo_selecionado="responsavel";
                break;
        }
    }

    public void AddCadastro(View view) {
        nome = nome_completo.getText().toString();
        email = email_barra.getText().toString();
        senha = senha_barra.getText().toString();
        confirme_senha = confirme_senha_barra.getText().toString();

        Log.d(testar, "Tipo: "+ tipo_selecionado);

        if (Nulidade() != null) {
            mAuth.createUserWithEmailAndPassword(email, senha)
                    .addOnCompleteListener(this, task -> salvarNaColecao());
        }

    }

    private void salvarNaColecao() {

        String uid =mAuth.getCurrentUser().getUid();

        User user = new User(uid, nome, email, tipo_selecionado);

        bd.collection("User").document(uid).set(user).addOnSuccessListener(unused -> {
            Toast.makeText(this, "Novo User cadastrado com sucesso", Toast.LENGTH_LONG).show();
            fazerLogin();
        });

    }

    private void fazerLogin() {
        LoginActivity ab=new LoginActivity();
        mAuth.signInWithEmailAndPassword(email, senha).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                ab.verificarTipoDeUsuario();
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
            Toast.makeText(this, "O campo senha não esta igual a senha acima",
                    Toast.LENGTH_SHORT).show();
            return null;

        }
        if (TextUtils.isEmpty(tipo_selecionado)) {
            Toast.makeText(this, "Escolha entre Responsável e Nutricionista", Toast.LENGTH_SHORT).show();
            return null;
        }
        return "Nada errado em campos";

    }


}








