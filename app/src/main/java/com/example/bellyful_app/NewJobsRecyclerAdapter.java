package com.example.bellyful_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import com.example.bellyful_app.JobData;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class NewJobsRecyclerAdapter extends RecyclerView.Adapter<NewJobsRecyclerAdapter.Myviewholder> {
    private LayoutInflater mInflater;
    private Context mContext;
    private ArrayList<JobData> mJobList;

    public NewJobsRecyclerAdapter(Context context, ArrayList<JobData> mJobList){
        this.mContext = context;
        this.mInflater = LayoutInflater.from(context);
        this.mJobList = mJobList;

    }

    @Override
    public Myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // TextView textView = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_new_jobs,parent,false);
       // Myviewholder myviewholder = new Myviewholder(textView);
       // return myviewholder;

        ViewGroup viewGroup;
        viewGroup = (ViewGroup) mInflater.inflate((R.layout.list_item_new_jobs), parent, false);
        Myviewholder vh = new Myviewholder(viewGroup);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final Myviewholder viewHolder, int position){
        final JobData currentItem = mJobList.get(position);
       // holder.Name.setText(list.get(position));
        viewHolder.nameLabel.setText(currentItem.getName());
        viewHolder.addressLabel.setText(currentItem.getAddress());
        viewHolder.phoneLabel.setText(currentItem.getPhone());
        viewHolder.foodLabel.setText(currentItem.getFood());


        //viewHolder.foodLabel.setText(mJobList.get(position).getna);
    }

    @Override
    public int getItemCount() {
        return mJobList.size();
    }

    public static class Myviewholder extends RecyclerView.ViewHolder{
        TextView nameLabel;
        TextView addressLabel;
        TextView phoneLabel;
        TextView foodLabel;


        public Myviewholder(@NonNull View itemView) {
            super(itemView);
            nameLabel = itemView.findViewById(R.id.lblJobName);
            addressLabel = itemView.findViewById(R.id.lblJobAddress);
            phoneLabel = itemView.findViewById(R.id.lblJobPhone);
            foodLabel = itemView.findViewById(R.id.lblJobFood);
        }
    }

}
