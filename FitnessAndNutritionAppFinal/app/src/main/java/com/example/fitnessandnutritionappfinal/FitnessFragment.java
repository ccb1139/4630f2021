package com.example.fitnessandnutritionappfinal;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FitnessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FitnessFragment extends Fragment implements RoutineDialog.OnInputSelected, ExerciseDialog.OnInputSelectedEx {
    //Add RecyclerView member
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;

    //For routines
    ArrayList<RoutineItem> routines = new ArrayList<>();
    LinearLayout exercises;


    //For Buttons
    private Button btnAdd;
    private Button btnRemove;

    public int tempPos;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FitnessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FitnessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FitnessFragment newInstance(String param1, String param2) {
        FitnessFragment fragment = new FitnessFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fitness, container, false);

        //For recycler View
        recyclerView = view.findViewById(R.id.routinesRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        adapter = new RecyclerAdapter(view.getContext(), routines);
        recyclerView.setAdapter(adapter);

        //For inside card


        //For button
        btnAdd = view.findViewById(R.id.add);
        btnRemove = view.findViewById(R.id.remove);

        //So I dont have to pas position
        tempPos = 0;

        //Click on the card
        adapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                tempPos = position;
                ExerciseDialog exerciseDialog = new ExerciseDialog();
                exerciseDialog.setTargetFragment(FitnessFragment.this, 1);
                exerciseDialog.show(getFragmentManager(), "Exercise Dialogue");

            }
        });


        //Add button code
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RoutineDialog routineDialog = new RoutineDialog();
                routineDialog.setTargetFragment(FitnessFragment.this, 1);
                routineDialog.show(getFragmentManager(), "Routine Dialogue");

                adapter.notifyDataSetChanged();
            }
        });
        //remove button code
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                routines.remove((routines.size()-1));
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public void sendInput(String input) {
        routines.add(new RoutineItem(input));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void sendInputEx(String _name, int _reps, int _sets, int _weight) {
        String tempPosDebug = Integer.toString(tempPos);
        Log.d("FitnessFragment.java", ("tempPosDebug" + tempPosDebug));
        routines.get(tempPos).addExerciseItem(_name, _reps, _sets, _weight);
        adapter.notifyDataSetChanged();
    }
}