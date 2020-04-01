package com.example.bellyful_app;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    String currentUser = "a";

    //@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //if(currentUser == "a"){
            Intent loginintent = new Intent(MainActivity.this,Login.class);
            startActivityForResult(loginintent, 2);
        //}
        // Get the Intent that started this activity and extract the string

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

    //@Override
    /*public void onStart() {
        super.onStart();
        //FirebaseUser currentUser = mAuth.getCurrentUser();
        System.out.println("Hello");
        updateUI(currentUser);
        System.out.println(currentUser);
        currentUser = "b";
        System.out.println("Goodbye");
        System.out.println(currentUser);
    }*/
    /*public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
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
