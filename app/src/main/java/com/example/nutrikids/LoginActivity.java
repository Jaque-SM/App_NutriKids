package com.example.nutrikids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.nutrikids.modelo.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private ProgressBar load;
    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firebaseFirestore;
    private FirebaseAuth mAuth;

    private String testar;
    private String currentUserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();

        load = findViewById(R.id.loading);
        email = findViewById(R.id.camp_login);
        senha = findViewById(R.id.camp_senha);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        AppCompatButton buttonLogin = findViewById(R.id.botao_entrar);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TelaLogin();
            }
        });

    }

    public void TelaLogin() {
        String loginEmail = email.getText().toString();
        String loginSenha = senha.getText().toString();

        if (!TextUtils.isEmpty(loginEmail) || !TextUtils.isEmpty(loginSenha)) {
            load.setVisibility(View.VISIBLE);
            firebaseAuth.signInWithEmailAndPassword(loginEmail, loginSenha)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                verificarTipoDeUsuario();

                            } else {
                                String error = task.getException().getMessage();
                                Toast.makeText(LoginActivity.this, "Error:  " + error, Toast.LENGTH_SHORT).show();
                                load.setVisibility(View.INVISIBLE);

                            }
                        }
                    });

        }


    }

    public void verificarTipoDeUsuario() {
        currentUserID = firebaseAuth.getCurrentUser().getUid();
        try {
            firebaseFirestore.collection("User").document(currentUserID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {

                        DocumentSnapshot document = task.getResult();
                        User user = document.toObject(User.class);
                        Log.d(testar, "Entrou aki no metodo login");
                        Intent intent;

                        if(user.getTipo() != null){
                            if (user.getTipo().equals("nutricionista")) {
                                intent = new Intent(LoginActivity.this, ListaCardapioActivity.class);
                                startActivity(intent);
                                finish();
                            }
                            if (user.getTipo().equals("responsavel")) {
                                intent = new Intent(LoginActivity.this, PacienteActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        else {
                            if (user.getTipo()==null) {
                                Toast.makeText(LoginActivity.this, "User não encontrado", Toast.LENGTH_LONG).show();
                                Log.d(testar, "User não encontrado");
                            }
                        }

                    }

                }
            });
        } catch (Exception e){
            Toast.makeText(LoginActivity.this, "O usuário não está cadastrado no sistema", Toast.LENGTH_LONG);
        }
    }

    public void CriarConta(View view) {
        Intent tent = new Intent(this, Cadastro_Activity.class);
        startActivity(tent);
    }
    @Override
    public void onBackPressed() {
        //Desativar o botão voltar
    }

}

