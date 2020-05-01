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
import com.example.bellyful_app.connectionclass;
import com.example.bellyful_app.R;
import com.example.bellyful_app.JobData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CurrentJobTab extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<JobData> mAllSelectedItems;
    //private ArrayList<JobData> mUserSelectedItems = new ArrayList<>();
    public Connection con;
    connectionclass c = new connectionclass();
    //Connection con =  c.con();

    public CurrentJobTab(){
        //Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle args = getArguments();
        // if(args != null) {
        //     mAllSelectedItems = args.getParcelableArrayList("selectedJobList");
        // }



        //Init NewJobRecycler
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.tab_outstanding_job, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.myOutstandingRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerAdapter = new CurrentJobsRecyclerAdapter(getActivity(), 0, storedbinfo(), getFragmentManager());
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }

    public ArrayList<JobData> storedbinfo(){
        try{
            con =  c.con();
            if(con == null){
                Toast errortoast = Toast.makeText(getActivity(), "Check Internet Connection", Toast.LENGTH_SHORT);
                errortoast.setGravity(0, 0, 0);
                errortoast.show();
            }else{
                String Query = "SELECT * FROM TEST_DELIVERIES WHERE status = 'confirmed'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(Query);
                mAllSelectedItems = new ArrayList<>();
                while(rs.next()){
                    JobData jobdata = new JobData(rs.getString("name"),rs.getString("address"),rs.getString("phone"),rs.getString("food"));
                    mAllSelectedItems.add(jobdata);
                    //Toast toast = Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT);
                    //toast.setGravity(0, 0, 0);
                    //toast.show();
                }
                con.close();
            }
        }catch(Exception ex){
            Log.d("MyTag","SQL Error");
        }
        return mAllSelectedItems;
    }

}