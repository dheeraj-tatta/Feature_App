package com.example.team;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {

    EditText mail, pwd;
    TextView textView;
    Button btn_signup, btn_signin;
    ImageView imageView;
    FirebaseAuth mAuth;
    FirebaseAuth finalMAuth = mAuth = FirebaseAuth.getInstance();
    ProgressBar pgbar;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mail = findViewById(R.id.email_id);
        pwd = findViewById(R.id.password_id);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        btn_signup = findViewById(R.id.signup);
        btn_signin = findViewById(R.id.signin);
        pgbar = findViewById(R.id.progressBar);
        pgbar.setVisibility(View.INVISIBLE);


        /*if (mAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), Home.class));

        }*/
        /*   public void onClickSignin(View view) {
               Intent i = new Intent(MainActivity.this, Home.class);
               startActivity(i);

           }

           public void onClickSignUp(View view) {
               Intent j = new Intent(MainActivity.this, SignupActivity.class);
               startActivity(j);
           }*/
        btn_signin.setOnClickListener(v -> {
            String email = mail.getText().toString().trim();
            String password = pwd.getText().toString().trim();

            if (TextUtils.isEmpty(email)) {
                mail.setError("Hey, we can't do this without an e-mail !");
                mail.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                pwd.setError("It's not gonna work without a password !");
                pwd.requestFocus();
                return;
            } else {
               pgbar.setVisibility(View.VISIBLE);

                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), Home.class));
                        } else {
                            Toast.makeText(MainActivity.this, "Login unsuccessful, please try again" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                //startActivity(new Intent(MainActivity.this, Home.class));
            }
        });
        btn_signup.setOnClickListener(vi -> {
            pgbar.setVisibility(View.VISIBLE);
            startActivity(new Intent(MainActivity.this, SignupActivity.class));
        });
    }
}
