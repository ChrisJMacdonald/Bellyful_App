package com.example.bellyful_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.bellyful_app.MESSAGE";
    /*BottomNavigationView bottomNavigationView; // Bottom navigation bar
    private ArrayList<JobData> newJobList = new ArrayList<>(); // New jobs to pass to the NewJobFragment
    private ArrayList<FreezerModel> freezerList = new ArrayList<>(); // Freezer info to pass to the FreezerFragment
    protected ArrayList <AcceptedJobModel> selectedJobList = new ArrayList<>(); // Jobs for the CurrentJobFragment
    */
    //private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // Initialize an instance of Firebase Auth
    //private FirebaseDatabase database = FirebaseDatabase.getInstance(); // Initialize an instance of the database

    Toolbar mToolbar;
    Fragment ft;

    @Override
    public void onStart() {
        super.onStart();
        Intent startLoginIntent = new Intent(MainActivity.this, Login.class);
        MainActivity.this.startActivity(startLoginIntent);
        // Check if user is signed in (non-null) and update UI accordingly.
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        //updateUI(currentUser);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Get the Intent that started this activity and extract the string

    }
    /*public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }*/
}
