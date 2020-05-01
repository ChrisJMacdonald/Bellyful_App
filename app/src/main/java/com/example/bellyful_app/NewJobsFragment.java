package com.example.bellyful_app;

import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.example.bellyful_app.connectionclass;


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
    private ArrayList<JobData> mCurrentSelectedItems = new ArrayList<>();//Keeps a list of selected items
    private ArrayList<String> mJobsTaken = new ArrayList<>();
    private ArrayList<JobData> mJobList;
    private OnFragmentInteractionListener mListener;



    public Connection con;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mRecyclerAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    connectionclass c = new connectionclass();
    public String uid = "";

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

    }
    

     @Override
   public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         Bundle bundle = this.getArguments();
         uid = bundle.getString("message");

         Log.d("MyTag","From new jobs fragment Logged in as: " + uid);
         //Toast errortoast = Toast.makeText(getActivity(), "From new jobs fragment Logged in as: " + uid, Toast.LENGTH_SHORT);
         //errortoast.setGravity(0, 0, 0);
         //errortoast.show();
        // Inflate the layout for this fragment
        ViewGroup root = (ViewGroup)inflater.inflate(R.layout.fragment_new_jobs, container, false);
        mRecyclerView = root.findViewById(R.id.NewJobRecycler);

         storedbinfo();
        createRecyclerView();


        //Log.d("MyTag","Size of joblist" + String.valueOf(mJobList.size()));

        //mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecyclerAdapter);
        return root;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button acceptJobButton = view.findViewById(R.id.btnAcceptJobs);
        //OnClickListener for the accept button
        acceptJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //"Are you sure?" popup dialogue
                new AlertDialog.Builder(getActivity())
                        .setTitle("Confirm Delivery")
                        .setMessage("Are you sure you want to take this delivery?")
                        //Specifying a listener allows you to take an action before dismissing the dialog.
                        //The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            //Accept Job
                            public void onClick(DialogInterface dialog, int which) {
                                con =  c.con();
                                //Loop through all the ticked items and move them into the CurrentJob collection
                                //for(int i = 0; i < mCurrentSelectedItems.size(); i++){
                                    //JobData currentItem = mCurrentSelectedItems.get(i);

                                    try{
                                        for(int i = 0; i < mCurrentSelectedItems.size(); i++){
                                            JobData currentItem = mCurrentSelectedItems.get(i);
                                            String Query = "SELECT * FROM TEST_DELIVERIES WHERE status = 'New'";
                                            Statement stmt = con.createStatement();
                                            ResultSet rs = stmt.executeQuery(Query);
                                            mJobList.remove(mCurrentSelectedItems.get(i));
                                        }
                                        con.close();
                                    }catch(Exception ex){
                                        Log.d("MyTag","SQL Error");
                                    }

                                    //Set job to accepted bind uid to delivery

                                    /*String id = currentItem.getID();
                                    DatabaseHelper.removeFromDbByID(currentItem, id);
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                    String userID = user.getUid();
                                    AcceptedJobModel acceptedJob = new AcceptedJobModel();
                                    acceptedJob.fillObject(currentItem.getID(), userID, "outstanding", currentItem.getName(),
                                            currentItem.getAddress(), currentItem.getPhone(), currentItem.getMeals());
                                    DatabaseHelper.addToDb(acceptedJob);
                                    */
                                    //mJobList.remove(mCurrentSelectedItems.get(i));
                                    //Log.d("deletion", id + " was removed");
                                //}

                                createRecyclerView();
                            }
                        })
                        // A null listener allows the button to dismiss the dialog and take no further action.
                        .setNegativeButton(android.R.string.no, null)
                        .show();
            }
        });
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);

    }

    private void createRecyclerView(){
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //The mJobList is a parameter of the adapter to pass the list to the recycler
        mRecyclerAdapter = new NewJobsRecyclerAdapter(getActivity(), mJobList, new NewJobsRecyclerAdapter.OnItemCheckListener() {
            @Override
            public void onItemCheck(JobData item) {
                mCurrentSelectedItems.add(item);
            }

            @Override
            public void onItemUncheck(JobData item) {
                mCurrentSelectedItems.remove(item);
            }
        });
        mRecyclerView.setAdapter(mRecyclerAdapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();

    }


    public ArrayList<JobData> storedbinfo(){
        Log.d("MyTag","uid is currently " + uid);
        try{
            con =  c.con();
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
}
