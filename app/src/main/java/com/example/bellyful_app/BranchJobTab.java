package com.example.bellyful_app;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bellyful_app.JobData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BranchJobTab extends Fragment{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<JobData> mSelectedItems = new ArrayList<>();
    private Bundle args;
    public Connection con;
    public BranchJobTab(){
        //Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //Init NewJobRecycler
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_branch_job, null);
        mRecyclerView = root.findViewById(R.id.branchOutstandingRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CurrentJobsRecyclerAdapter(getActivity(), 2, storedbinfo(), getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }

    public ArrayList<JobData> storedbinfo(){
        try{
            con = connectionclass();
            if(con == null){
                Toast errortoast = Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT);
                errortoast.setGravity(0, 0, 0);
                errortoast.show();
            }else{
                String Query = "SELECT * FROM TEST_DELIVERIES where status = 'branch'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(Query);
                mSelectedItems = new ArrayList<>();
                while(rs.next()){
                    JobData jobdata = new JobData(rs.getString("name"),rs.getString("address"),rs.getString("phone"),rs.getString("food"));
                    mSelectedItems.add(jobdata);
                    //Toast toast = Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT);
                    //toast.setGravity(0, 0, 0);
                    //toast.show();
                }
                con.close();
            }
        }catch(Exception ex){
            Log.d("MyTag","SQL Error");
        }
        return mSelectedItems;
    }

    public Connection connectionclass() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String ConnectionURL = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://programmingprojects.database.windows.net:1433;DatabaseName=Bellyful_DB;user=Oscar@programmingprojects;password=B311yfu1;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
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
