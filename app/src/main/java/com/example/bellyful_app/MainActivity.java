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

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.bellyful_app.MESSAGE";
    BottomNavigationView bottomNavigationView; // Bottom navigation bar
    /*
    private ArrayList<JobData> newJobList = new ArrayList<>(); // New jobs to pass to the NewJobFragment
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
        setContentView(R.layout.activity_main);
        Intent loginintent = new Intent(MainActivity.this,Login.class);
        startActivityForResult(loginintent, 2);

        //Bottom Navigation Bar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle(R.string.new_deliveries);

        //Load starting fragment
        loadNewJobsFragment();
    }

        private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_new_jobs:
                        getSupportActionBar().setTitle(R.string.new_deliveries);
                        loadNewJobsFragment();
                        //System.out.println("New Jobs");
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
                case R.id.action_job_submit:
                    getSupportActionBar().setTitle(R.string.job_submit);
                   ft = new JobSubmitFragment();
                    loadFragment(ft);
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
            String message = data.getStringExtra("MESSAGE");
            System.out.println(message);

            Toast toast = Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT);
            toast.setGravity(0, 0, 0);
            toast.show();
            //textView1.setText(message);
        }
    }

   /* public void updateUI(String currentUser){
        if(currentUser == "a"){
            //User not logged in
            //Start login activity
            Intent startLoginIntent = new Intent(MainActivity.this, Login.class);
            MainActivity.this.startActivity(startLoginIntent);

        }else{
            //User is already logged in
            //Start activity
            Toast.makeText(MainActivity.this, "Logged in",
                    Toast.LENGTH_SHORT).show();
        }
    }*/

    public Connection connectionclass(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://programmingprojects.database.windows.net:1433;DatabaseName=Bellyful_DB;user=Oscar@programmingprojects;password=Acromantula7;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
            connection = DriverManager.getConnection(ConnectionURL);
        }catch (SQLException se){
            Log.e("error here 1 : ", se.getMessage());
        }
        catch(ClassNotFoundException e){
            Log.e("error here 2 : ", e.getMessage());

        }catch(Exception e){
            Log.e("error here 3 : ", e.getMessage());
        }
        return connection;
    }
}
