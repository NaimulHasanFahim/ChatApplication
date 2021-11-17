package com.example.chatapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import androidx.appcompat.widget.Toolbar;
public class LoginActivity extends AppCompatActivity {

    EditText userpasswordlog,useremaillog;
    Button buttonlog;
    TextView logtext;

    FirebaseAuth auth;
    DatabaseReference reference;

    Toolbar logtoolbar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        logtext = (TextView) findViewById(R.id.logtxt);
        userpasswordlog = (EditText) findViewById(R.id.logpass);
        useremaillog = (EditText) findViewById(R.id.logemail);
        buttonlog =(Button) findViewById(R.id.btn_log);

        //logtoolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(logtoolbar);
        //getSupportActionBar().setTitle("Login");
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        buttonlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ue = useremaillog.getText().toString();
                String up = userpasswordlog.getText().toString();

                if(TextUtils.isEmpty(ue)||TextUtils.isEmpty(up)){
                    Toast.makeText(LoginActivity.this,"Each field must be filled !!!",Toast.LENGTH_SHORT).show();
                }
                else{

                    auth.signInWithEmailAndPassword(ue, up).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(LoginActivity.this,"Authentication Failed !!",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });
    }
}
