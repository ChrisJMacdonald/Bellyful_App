package com.example.bellyful_app;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.bellyful_app.JobData;
import com.example.bellyful_app.connectionclass;



public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.bellyful_app.MESSAGE";
    public Connection con;
    String uid = "";

    BottomNavigationView bottomNavigationView; // Bottom navigation bar


    /*
    private ArrayList<com.example.bellyful_app.JobData> newJobList = new ArrayList<>(); // New jobs to pass to the NewJobFragment
    private ArrayList<FreezerModel> freezerList = new ArrayList<>(); // Freezer info to pass to the FreezerFragment
    protected ArrayList <AcceptedJobModel> selectedJobList = new ArrayList<>(); // Jobs for the CurrentJobFragment
    */
    //private FirebaseAuth mAuth = FirebaseAuth.getInstance(); // Initialize an instance of Firebase Auth
    //private FirebaseDatabase database = FirebaseDatabase.getInstance(); // Initialize an instance of the database

    Toolbar mToolbar;
    Fragment ft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //c.setuid("Hello There");
        setContentView(R.layout.activity_main);
        Intent loginintent = new Intent(MainActivity.this,Login.class);
        //loginintent.putExtra("Connection", connectionAsAString);
        //loginintent.putExtra("Connection",c);
        startActivityForResult(loginintent, 2);

        //i.putExtra("puzzle", easyPuzzle); send
        //String easyPuzzle = i.getStringExtra("puzzle"); recieve;

        //Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.new_deliveries);

        //Load starting fragment
        //loadNewJobsFragment();
    }

        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_new_jobs:
                        getSupportActionBar().setTitle(R.string.new_deliveries);
                        loadNewJobsFragment();
                        return true;
                    case R.id.action_current_jobs:
                        getSupportActionBar().setTitle(R.string.current_jobs);
                        //Bundle contains jobs selected in the newJobFragment
                        // Bundle currentJobsBundle = new Bundle();
                        // currentJobsBundle.putParcelableArrayList("selectedJobList", selectedJobList);
                        ft = new CurrentJobsFragment();
                        // ft.setArguments(currentJobsBundle);
                        loadFragment(ft);
                        return true;
                    case R.id.action_freezers:
                        getSupportActionBar().setTitle(R.string.freezers);
                        ft = new FreezersFragment();
                        loadFragment(ft);
                      //  loadFreezerFragment();
                        return true;
                    case R.id.action_user:
                        getSupportActionBar().setTitle(R.string.account_info);
                        ft = new UserAccountFragment();
                        loadFragment(ft);
                        return true;
                }
                return false;
            }
        };

    //Function to load the NewJobsFragment
    //This is it's own function because it's used a couple of times
    public void loadNewJobsFragment(){
        //Send jobData to the fragment
        //Bundle newJobsBundle = new Bundle();
        //newJobsBundle.putParcelableArrayList("newJobList", newJobList);
        ft = new NewJobsFragment();
        //ft.setArguments(newJobsBundle);
        loadFragment(ft);
    }

    //Generalised function to load fragments
    public void loadFragment(Fragment fragment) {
        //For making loading fragments easier and cleaner
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("message", uid );
        fragment.setArguments(bundle);
        ft.replace(R.id.frameContainer, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            uid = data.getStringExtra("MESSAGE");
            //System.out.println(message);
            Log.d("MyTag","Uid from main is set to " + uid);
            loadNewJobsFragment();
        }
    }

}
