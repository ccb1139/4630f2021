package com.example.fitnessandnutritionappfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    private TextView profileHeader;
    private EditText profileWeight, profileAge, profileName;

    private String weight, age, name;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String NAME = "text1";
    public static final String AGE = "text2";
    public static final String WEIGHT = "text3";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        profileHeader = view.findViewById(R.id.profileHead);
        profileName = view.findViewById(R.id.profileName);
        profileAge = view.findViewById(R.id.profileAge);
        profileWeight = view.findViewById(R.id.profileWeight);

        profileName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!view.hasFocus()){
                    saveData();
                }
            }
        });
        profileWeight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!view.hasFocus()){
                    saveData();
                }
            }
        });
        profileAge.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(!view.hasFocus()){
                    saveData();
                }
            }
        });


        loadData();
        updateViews();

        return view;
    }

    public void saveData(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(NAME, profileName.getText().toString());
        editor.putString(WEIGHT, profileWeight.getText().toString());
        editor.putString(AGE, profileAge.getText().toString());

        editor.apply();
    }

    public void loadData(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        name = sharedPreferences.getString(NAME, "");
        weight = sharedPreferences.getString(WEIGHT, "");
        age = sharedPreferences.getString(AGE, "");
    }

    public void updateViews(){
        profileName.setText(name);
        profileAge.setText(age);
        profileWeight.setText(weight);
    }
}