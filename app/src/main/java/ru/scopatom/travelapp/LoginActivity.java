package ru.scopatom.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    EditText editText_email, editText_password;
    Button button_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        } else {
            setContentView(R.layout.activity_login);
            editText_email = findViewById(R.id.editText_email);
            editText_password = findViewById(R.id.editText_password);
            button_auth = findViewById(R.id.button_auth);
            button_auth.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        String email = "0";
        String password = "0";
        if (editText_email.getText().toString().equals("") || editText_password.getText().toString().equals(""))
            ;
        else {
            email = editText_email.getText().toString();
            password = editText_password.getText().toString();
        }
        if (v.getId() == R.id.button_auth) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "Неверная почта или пароль.", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}