package com.example.bellyful_app;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bellyful_app.JobData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class CurrentJobsRecyclerAdapter extends RecyclerView.Adapter<CurrentJobsRecyclerAdapter.NewJobViewHolder> {

    private LayoutInflater mInflater;
    private Context mContext;
    private int mCode; // 1 = Outstanding, 2 = Complete
    private ArrayList<JobData> mCurrentJobs = new ArrayList<>();
    private ArrayList<JobData> mOutstandingJobs = new ArrayList<>();
    private ArrayList<JobData> mBranchJobs = new ArrayList<>();
    private FragmentManager mFragmentManager;

    CurrentJobsRecyclerAdapter(Context context, int code, ArrayList<JobData> itemsList, FragmentManager fragmentManager) {
        //Load outstanding jobs
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mCode = code;
        this.mFragmentManager = fragmentManager;
        if(code == 0) {
            this.mCurrentJobs = itemsList;
            //Log.d("MyTag","Selected Current Jobs");
        }else if(mCode == 1){
           this.mOutstandingJobs = itemsList;
            //Log.d("MyTag","Selected Branch Jobs");
        }else if(mCode == 2){
            this.mBranchJobs = itemsList;
            //Log.d("MyTag","Selected Branch Jobs");
        }
    }

    @NonNull
    @Override
    public NewJobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewGroup viewGroup;
        //Was going to use this adapter for multiple fragments but it caused a lot of issues.
        //Only use code 1 for now
        if(mCode == 0){
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_confirmed), parent, false);
        }else if(mCode == 1){
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_outstanding), parent, false);
        }else if(mCode == 2){
            viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_branch), parent, false);
        }else{
        viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_tab_confirmed), parent, false);
         }
        NewJobViewHolder vh = new NewJobViewHolder(viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NewJobViewHolder viewHolder, int position) {
        final JobData currentItem;
        if(mCode == 0){
            currentItem = mCurrentJobs.get(position);
        }else if (mCode == 1){
            currentItem = mOutstandingJobs.get(position);
        }else{
            currentItem = mBranchJobs.get(position);
        }
        // holder.Name.setText(list.get(position));
        viewHolder.nameLabel.setText(currentItem.getName());
        viewHolder.addressLabel.setText(currentItem.getAddress());
        viewHolder.phoneLabel.setText(currentItem.getPhone());
        viewHolder.foodLabel.setText(currentItem.getFood());


        //Button login;


       /* if(mCode == 1) {
            if (mOutstandingJobs.size() > 0) {
                final AcceptedJobModel currentItem = mOutstandingJobs.get(position);
                viewHolder.nameLabel.setText(currentItem.getName());
                viewHolder.addressLabel.setText(currentItem.getAddress());
                viewHolder.phoneLabel.setText(currentItem.getPhone());

                //Build string from meal HashMap
                StringBuilder mealString = new StringBuilder();
                for (String key : currentItem.getMeals().keySet()) {
                    mealString.append(key).append("x");
                    mealString.append(currentItem.getMeals().get(key).toString()).append(" ");
                }

                viewHolder.foodLabel.setText(mealString);
            }
        }*/
    }
    @Override
    public int getItemCount() {
        if(mCode == 0){
            return mCurrentJobs.size();
        }else if (mCode == 1){
            return mOutstandingJobs.size();
        }else{
            return mBranchJobs.size();
        }
    }
    class NewJobViewHolder extends RecyclerView.ViewHolder {

        TextView nameLabel;
        TextView addressLabel;
        TextView phoneLabel;
        TextView foodLabel;
        Button startButton;
        Button completeButton;

        public NewJobViewHolder(@NonNull View itemView) {
            super(itemView);
            startButton =  itemView.findViewById(R.id.btnOptions);
            nameLabel = itemView.findViewById(R.id.lblJobName);
            addressLabel = itemView.findViewById(R.id.lblJobAddress);
            phoneLabel = itemView.findViewById(R.id.lblJobPhone);
            foodLabel = itemView.findViewById(R.id.lblJobFood);

            //startButton.setOnClickListener((View.OnClickListener) this);

            startButton.setOnClickListener(new View.OnClickListener() {
                //@Override
                public void onClick(View view) {
                    startButton.setText("Done");
                }
            });

           // startButton = itemView.findViewById(R.id.btnStart);
           // completeButton = itemView.findViewById(R.id.btnComplete);
        }
    }

    public void onClick(View v) {

    }
}
