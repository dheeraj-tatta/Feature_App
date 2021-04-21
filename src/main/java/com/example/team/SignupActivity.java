package com.example.team;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
/*class flag_marker {
    int first_time = 0;
    void incrementer() {
        first_time = 1;
    }
    void decrementer() {
        first_time = 0;
    }
}*/
public class SignupActivity extends AppCompatActivity {
  //  flag_marker fm = new flag_marker();
    TextView textView,mail,pwd,existing_user;
    Button btn_signup, btn_signin, btn_join;
    ImageView imageView;
    private FirebaseAuth mAuth;
    FirebaseAuth finalMAuth = mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mail = findViewById(R.id.email_id_signup);
        pwd = findViewById(R.id.password_id_signup);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        btn_signup = findViewById(R.id.signup);
        btn_signin = findViewById(R.id.signin);
        btn_join = findViewById(R.id.join);
        existing_user = findViewById(R.id.already_a_user);

        if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home.class));
            finish();
        }

        existing_user.setOnClickListener(s -> {
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
        });


        btn_join.setOnClickListener(v -> {
            String email = mail.getText().toString().trim();
            String password = pwd.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                mail.setError("Hey, we can't do this without an e-mail !");
                mail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                pwd.setError("Let's get some security. Add a password !");
                pwd.requestFocus();
                return;
            }
            if (password.length() < 8) {
                pwd.setError("Let's make it strong. Set a password of minimum 8 characters");
                pwd.requestFocus();
                return;
            }
            finalMAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @SuppressLint("WrongConstant")
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                        finalMAuth.signInWithEmailAndPassword(email, password);
                       // fm.incrementer();
                        startActivity(new Intent(SignupActivity.this, Home.class));
                       // Intent intentfromsignup = new Intent(SignupActivity.this, MainActivity.class);
                        //intentfromsignup.addFlags(1);
                        startActivity(new Intent(getApplicationContext(), Home.class));
                    } else {
                        Toast.makeText(SignupActivity.this, "Registration unsuccessful, please try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
});}}