package com.example.bellyful_app;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CurrentJobsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CurrentJobsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Bundle args;
    public CurrentJobsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CurrentJobsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CurrentJobsFragment newInstance(String param1, String param2) {
        CurrentJobsFragment fragment = new CurrentJobsFragment();
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
        View v = inflater.inflate(R.layout.fragment_current_jobs, container, false);
        // Inflate the layout for this fragment
        return v;
    }
    public void onViewCreated (View view, Bundle savedInstanceState) {
        final ViewPager viewPager = view.findViewById(R.id.currentJobsViewPager);
        TabLayout tabLayout = view.findViewById(R.id.currentJobTabLayout);
        CurrentJobViewPagerAdapter viewPagerAdapter = new CurrentJobViewPagerAdapter(getChildFragmentManager(), args);
        //viewPagerAdapter.addFragment(new OutstandingJobTab(), "Current Jobs");
        viewPagerAdapter.addFragment(new CurrentJobTab(), "Current Jobs");
        viewPagerAdapter.addFragment(new OutstandingJobTab(), "My Outstanding");
        viewPagerAdapter.addFragment(new BranchJobTab(), "Branch Outstanding");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

}
