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
import com.example.bellyful_app.connectionclass;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private Button login;
    private TextView registerHere;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private ProgressBar loginProgressBar;
    public Connection con;
    connectionclass c = new connectionclass();
    String uid = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
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

                try{
                    con =  c.con();
                    if(con == null){
                        Toast errortoast = Toast.makeText(Login.this, "Check Internet Connection", Toast.LENGTH_SHORT);
                        errortoast.setGravity(0, 0, 0);
                        errortoast.show();
                    }else{
                        String Query = "SELECT * FROM USER_LOGIN WHERE username = \'" + usernameText + "\' AND password = \'" + passwordText + "\'";
                        Log.d("MyTag","" + Query);
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(Query);
                        //if(!rs.next()){
                         //   Log.d("MyTag","User does not exist");
                         //   con.close();
                          //  return;
                        //}
                        if(rs.next()){
                            //if(rs.getString("username") == usernameText && rs.getString("password") == passwordText){
                                uid = (rs.getString("uid"));
                                Log.d("MyTag","Uid is set to " + uid);
                                Toast errortoast = Toast.makeText(Login.this, "Logged in as: " + rs.getString("username"), Toast.LENGTH_SHORT);
                                errortoast.setGravity(0, 0, 0);
                                errortoast.show();
                                String message = uid;
                                Intent intent=new Intent();
                                intent.putExtra("MESSAGE",message);
                                setResult(2,intent);
                                finish();//finishing activity

                            //}else{

                           // }

                        }else{
                            Toast errortoast = Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT);
                            errortoast.setGravity(0, 0, 0);
                            errortoast.show();
                            con.close();

                        }

                    }
                }catch(Exception ex){
                    Log.d("MyTag","SQL Error");
                }



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
