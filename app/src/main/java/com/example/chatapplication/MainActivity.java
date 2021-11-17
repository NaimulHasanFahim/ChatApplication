package com.example.chatapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import androidx.appcompat.widget.Toolbar;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button alredy,need;
    FirebaseUser muser;


    @Override
    protected void onStart() {
        super.onStart();
        muser = FirebaseAuth.getInstance().getCurrentUser();
        if(muser!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class ));
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alredy = (Button) findViewById(R.id.alrd_acc);
        need = (Button) findViewById(R.id.need_acc);

        alredy.setOnClickListener(this);
        need.setOnClickListener(this);


        if(muser!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class ));
            finish();
        }

    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.alrd_acc){
            //Toast.makeText(MainActivity.this,"Login Button clicked  !!!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,LoginActivity.class ));
            finish();
        }

        if(view.getId()==R.id.need_acc){
            //Toast.makeText(MainActivity.this,"Register Button clicked  !!!",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,RegisterActivity.class ));
            finish();
        }


    }
}
