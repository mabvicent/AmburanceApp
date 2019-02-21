package com.example.amburanceapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.amburanceapp.Common.Common;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {
    Button signUp, Register;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout rootLayout;
    private Intent WelcomeActivity;
    ProgressBar loading;
    Button createAccount,cancel;


    @Override
    protected void attachBaseContext(Context newBase) {
        //before set content view
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Arkhip.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());

        super.attachBaseContext( CalligraphyContextWrapper.wrap(newBase));




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);

        //Loading the current user if exists
        auth = FirebaseAuth.getInstance();
        WelcomeActivity = new Intent(this,WelcomeActivity.class);
        db = FirebaseDatabase.getInstance();
        users = db.getReference( Common.user_doctor_tb1);
        Register = findViewById(R.id.btn_Reg);
        signUp = findViewById(R.id.btn_signIn);
        loading = findViewById(R.id.progressing);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent( MainActivity.this,RegisterActivity.class );
                startActivity( intent );
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoginDialog();
            }
        });

    }

    private void showLoginDialog() {


        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("LOG IN");
        dialog.setMessage("Please use email to login");

        LayoutInflater inflater = LayoutInflater.from(this);

        View login_layout = inflater.inflate(R.layout.layout_login,null);
        final MaterialEditText editEmail = login_layout.findViewById(R.id.editEmail);
        final MaterialEditText editPassword = login_layout.findViewById(R.id.editpassword);

        rootLayout = findViewById(R.id.rootLayout);
        dialog.setView(login_layout);


        dialog.setPositiveButton("LOG IN", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
                //set login button disabled
                signUp.setEnabled(false);


                if (TextUtils.isEmpty(editEmail.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter email", Snackbar.LENGTH_SHORT).show();
                    return;

                }
                if (TextUtils.isEmpty(editPassword.getText().toString())) {
                    Snackbar.make(rootLayout, "Please enter password", Snackbar.LENGTH_SHORT).show();
                    return;

                }
                if (editPassword.getText().toString().length() < 6) {
                    Snackbar.make(rootLayout, "password cant be less than 6 characters", Snackbar.LENGTH_SHORT).show();
                    return;

                }


                // Open sign in activity



                // lOGIN
                auth.signInWithEmailAndPassword(editEmail.getText().toString(),editPassword.getText().toString())
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                // waitingDialog.dismiss();

                                startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
                                finish();



                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //  waitingDialog.dismiss();
                        Snackbar.make(rootLayout,"Fialed"+e.getMessage(),Snackbar.LENGTH_LONG).show();
                        signUp.setEnabled(true);
                    }
                });



            }}).setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });


        dialog.show();




    }


    private void showMessage(String message) {
        Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
    }
}
