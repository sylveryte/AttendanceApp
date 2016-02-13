package com.codedleaf.sylveryte.attendanceapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by sylveryte on 13/2/16.
 */
public class AttendanceFragment extends Fragment {

    private RecyclerView mClassRecyclerView;
    private ClassAdapter mClassAdapter;

    public AttendanceFragment() {

    }

    public static AttendanceFragment newInstance()
    {
        return new AttendanceFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.class_fragment_layout,container,false);

        mClassRecyclerView=(RecyclerView)view.findViewById(R.id.class_layout_container_recycler_view);
        mClassRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI()
    {
        KlassLab klassLab = KlassLab.get(getActivity());
        List<Klass> klasses = klassLab.getKlasses();
        mClassAdapter=new ClassAdapter(klasses);
        mClassRecyclerView.setAdapter(mClassAdapter);
    }




    //view holder to hold views
    private class KlassHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;

        public KlassHolder(View itemView)
        {
            super(itemView);
            mTextView=(TextView)itemView.findViewById(R.id.class_list_text_class_name);
        }

    }


    //adapter to manage
    private class ClassAdapter extends RecyclerView.Adapter<KlassHolder>
    {
        private List<Klass> mKlasses;

        public ClassAdapter(List<Klass> klasses)
        {
            mKlasses =klasses;
        }

        @Override
        public KlassHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.class_list_layout,parent,false);
            return new KlassHolder(view);
        }



        @Override
        public void onBindViewHolder(KlassHolder holder, int position) {
            Klass klass = mKlasses.get(position);
            holder.mTextView.setText(klass.getKlassName());
        }

        @Override
        public int getItemCount() {
            return mKlasses.size();
        }
    }

}