package com.example.team;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;


/*class flag_setter extends FlagClass {
    FlagClass fc = new FlagClass();
    void incrementer() {
        fc.first_time++;
    }
    void decrementer() {
        fc.first_time--;
    }

}*/


public class Home extends AppCompatActivity {
//
   //// public void load_image(View view) {

   // }

    // flag_marker fm = new flag_marker();
    FirebaseAuth fbauth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
       // if (fm.first_time == 0){
           if (fbauth.getCurrentUser() == null) {
                //1fm.first_time = 1;
                Intent signup_intent = new Intent(Home.this, SignupActivity.class);
                startActivity(signup_intent);
        }


    }
        public void logout(View view){
            //fm.decrementer();
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
}