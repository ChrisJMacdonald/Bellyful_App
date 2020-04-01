package com.example.bellyful_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private Button login;
    private TextView registerHere;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private ProgressBar loginProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        editTextUsername = findViewById(R.id.editTextUserEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginProgressBar = findViewById(R.id.loginProgressBar);

        login.setOnClickListener(new View.OnClickListener() {
            //@Override
            public void onClick(View view) {
                //Hide the virtual keyboard
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getApplicationWindowToken(),0);

                //Show progress bar
                loginProgressBar.setVisibility(View.VISIBLE);

                String usernameText = editTextUsername.getText().toString();
                String passwordText = editTextPassword.getText().toString();

                loginProgressBar.setVisibility(View.GONE); //

                String message = usernameText;
                Intent intent=new Intent();
                intent.putExtra("MESSAGE",message);
                setResult(2,intent);
                finish();//finishing activity

                //Intent startMainIntent = new Intent(Login.this, MainActivity.class);
                //Login.this.startActivity(startMainIntent);

                /*boolean isValid = validate(usernameText, passwordText);
                if(isValid){
                    signIn(usernameText, passwordText);
                }else{
                    //Some error occurred
                    loginProgressBar.setVisibility(View.GONE);
                }*/
            }
        });





    }
}
