package com.example.nutrikids;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RemoveCardapioActivity extends AppCompatActivity {

    private FirebaseFirestore bd;
    private EditText campo_email;
    private String email;
    private String texto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_cardapio);

        bd=FirebaseFirestore.getInstance();

        campo_email=findViewById(R.id.campo_email_remove);

    }
    public void RemoverCard(View view){
        email=campo_email.getText().toString();

        Task<Void> docRef = bd.collection("Cardapios").document(email).delete();
        docRef.addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(RemoveCardapioActivity.this, "Cardapio Removido com Sucesso!", Toast.LENGTH_LONG).show();
                Log.d(texto, "Card excluido: "+docRef);

            }
        });
    }
}