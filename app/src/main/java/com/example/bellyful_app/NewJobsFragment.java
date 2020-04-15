package com.example.bellyful_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.bellyful_app.JobData;
import androidx.recyclerview.widget.RecyclerView;


//int myNum = 0; try { myNum = Integer. parseInt(myString); } catch(NumberFormatException nfe) { // Handle parse error. }


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewJobsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<String> mCurrentSelectedItems = new ArrayList<>();//Keeps a list of selected items
    private ArrayList<String> mJobsTaken = new ArrayList<>();
    private ArrayList<JobData> mJobList;


    public Connection con;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public NewJobsFragment() {
        // Required empty public constructor

    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewJobsFragment newInstance(String param1, String param2) {
        NewJobsFragment fragment = new NewJobsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    

     @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_new_jobs, container, false);
        mRecyclerView = root.findViewById(R.id.NewJobRecycler);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        storedbinfo();

        //Log.d("MyTag","Size of joblist" + String.valueOf(mJobList.size()));
        mRecyclerAdapter = new NewJobsRecyclerAdapter(getActivity(),storedbinfo());
        //mRecyclerView.setHasFixedSize(true);
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
                String Query = "SELECT * FROM TEST_DELIVERIES WHERE status = 'New'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(Query);
                mJobList = new ArrayList<>();
                while(rs.next()){
                    JobData jobdata = new JobData(rs.getString("name"),rs.getString("address"),rs.getString("phone"),rs.getString("food"));
                    mJobList.add(jobdata);
                    //Toast toast = Toast.makeText(MainActivity.this, output, Toast.LENGTH_SHORT);
                    //toast.setGravity(0, 0, 0);
                    //toast.show();
                }
                con.close();
            }
        }catch(Exception ex){
            Log.d("MyTag","SQL Error");
        }
        return mJobList;
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


    //public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
      //  super.onViewCreated(view, savedInstanceState);

    //}

   /* private void createRecyclerView(){


        //The mJobList is a parameter of the adapter to pass the list to the recycler
        mRecyclerAdapter = new NewJobsRecyclerAdapter(getActivity(), mJobList, new NewJobsRecyclerAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(com.example.bellyful_app.JobData item) {
                mCurrentSelectedItems.add(item);
            }

            @Override
            public void onItemUncheck(com.example.bellyful_app.JobData item) {
                mCurrentSelectedItems.remove(item);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }*/





}
