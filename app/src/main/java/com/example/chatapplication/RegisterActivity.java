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
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
public class RegisterActivity extends AppCompatActivity {

    EditText username,userpassword,useremail;
    Button button;
    TextView createtext;

    FirebaseAuth auth;
    DatabaseReference reference;

    Toolbar regtoolbar ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        createtext = (TextView) findViewById(R.id.crtxt);
        username = (EditText) findViewById(R.id.name);
        userpassword = (EditText) findViewById(R.id.pass);
        useremail = (EditText) findViewById(R.id.email);
        button =(Button) findViewById(R.id.btn_register);

        regtoolbar =(Toolbar) findViewById(R.id.toolregister);
        setSupportActionBar(regtoolbar);
        getSupportActionBar().setTitle("Register");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        auth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String un = username.getText().toString();
                String ue = useremail.getText().toString();
                String up = userpassword.getText().toString();

                if(TextUtils.isEmpty(un)||TextUtils.isEmpty(ue)||TextUtils.isEmpty(up)){
                    Toast.makeText(RegisterActivity.this,"Each field must be filled !!!",Toast.LENGTH_SHORT).show();
                }
                else if(up.length()<6){
                    Toast.makeText(RegisterActivity.this,"Password must be greater than 6  !!!",Toast.LENGTH_SHORT).show();
                }
                else{
                    register(un,ue,up);
                }
            }
        });
    }


    public void register(final String username, String email, String password){

        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    FirebaseUser firebaseUser = auth.getCurrentUser();
                    String uid = firebaseUser.getUid();

                    reference = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

                    HashMap<String,String> hashMap = new HashMap<>();

                    hashMap.put("name",username);
                    hashMap.put("image","default");
                    hashMap.put("id",uid);

                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Intent intent = new Intent(RegisterActivity.this,HomeActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });


                }
                else{

                    Toast.makeText(RegisterActivity.this,"Can't Register The User !!!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
