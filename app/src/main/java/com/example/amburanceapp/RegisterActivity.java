package com.example.amburanceapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.amburanceapp.Common.Common;
import com.example.amburanceapp.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class RegisterActivity extends AppCompatActivity {

    Button createAccount,cancle;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout rootLayout;
    private Intent WelcomeActivity;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_register);
        auth = FirebaseAuth.getInstance();
        WelcomeActivity = new Intent(this,WelcomeActivity.class);
        db = FirebaseDatabase.getInstance();
        users = db.getReference( Common.user_doctor_tb1);

        loading = findViewById(R.id.progressing);
        cancle = findViewById( R.id.Cancel );
        cancle.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelAccountCreation();
            }
        } );



        createAccount = findViewById( R.id.btn_RegAccount);

        createAccount.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                createUserAccount();
            }
        } );
    }

    private void cancelAccountCreation() {

        Intent intent = new Intent( RegisterActivity.this,MainActivity.class );
        startActivity( intent );
    }

    private void createUserAccount() {
        final MaterialEditText editEmail = findViewById(R.id.editEmail);
        final MaterialEditText editName = findViewById(R.id.editName);
        final MaterialEditText editPhone = findViewById(R.id.editphne);
        final MaterialEditText confirmPassword =findViewById(R.id.editConfirmpassword);
        final MaterialEditText hospital = findViewById(R.id.hospital);
        final MaterialEditText specialisation = findViewById(R.id.specialisation);
        final MaterialEditText editPassword = findViewById(R.id.editpassword);

        if(TextUtils.isEmpty(editEmail.getText().toString())){

            Toast.makeText( RegisterActivity.this,"Please enter email",Toast.LENGTH_SHORT ).show();

            return;

        }
        if(TextUtils.isEmpty(editName.getText().toString())){
            Toast.makeText( RegisterActivity.this,"Please enter name",Toast.LENGTH_SHORT ).show();

            return;

        }
        if(TextUtils.isEmpty(editPassword.getText().toString())){
            Toast.makeText( RegisterActivity.this,"Please enter password",Toast.LENGTH_SHORT ).show();

            return;

        }
        if(TextUtils.isEmpty(editPhone.getText().toString())){
            Toast.makeText( RegisterActivity.this,"Please enter phone",Toast.LENGTH_SHORT ).show();
            return;

        }
        if(editPassword.getText().toString().length()<6){
            Toast.makeText( RegisterActivity.this,"Please password cant be less than 6 characters",Toast.LENGTH_SHORT ).show();

            return;

        }
        if(TextUtils.isEmpty(hospital.getText().toString())){
            Toast.makeText( RegisterActivity.this,"Please enter hospital",Toast.LENGTH_SHORT ).show();

            return;

        }
        if(TextUtils.isEmpty(specialisation.getText().toString())){
            Toast.makeText( RegisterActivity.this,"Please enter proffesional",Toast.LENGTH_SHORT ).show();

            return;

        }
        if(!confirmPassword.getText().toString().equals(editPassword.getText().toString())){
            Toast.makeText( RegisterActivity.this,"Password fields must be matching",Toast.LENGTH_SHORT ).show();
            return;

        }
        //Register user

        //Display progress bar
        // loading.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        // loading.setVisibility(View.INVISIBLE);
                        User user = new User();
                        user.setEmail(editEmail.getText().toString());
                        user.setPassword(editPassword.getText().toString());
                        user.setAmbulance_name(editName.getText().toString());
                        user.setPhone(editPhone.getText().toString());
                        user.setHospital(hospital.getText().toString());
                        user.setHospital(specialisation.getText().toString());


                        //user email to key
                        users.child( FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener( new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(RegisterActivity.this,"Registeration successfull",Toast.LENGTH_SHORT).show();


                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // loading.setVisibility(View.INVISIBLE);
                                Toast.makeText(RegisterActivity.this,"Registeration failed"+e.getMessage(),Toast.LENGTH_SHORT).show();

                            }
                        });

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //loading.setVisibility(View.INVISIBLE);
                Toast.makeText( RegisterActivity.this,"Registeration failed"+e.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });



//            }
//        });



    }
}
