package com.example.fitnessandnutritionappfinal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements AddDaysDialog.ADOnInputSelected{

    LinearLayout mLayout, tLayout, wLayout, trLayout, fLayout, sLayout, suLayout;
    TextView mTxtView, tTxtView, wTxtView, trTxtView, fTxtView, sTxtView, suTxtView;
    Button clear;
    ArrayList<RoutineItem> routines;
    ArrayList<String> names = new ArrayList<>();
    ArrayList<String> mnames = new ArrayList<>();
    ArrayList<String> tnames = new ArrayList<>();
    ArrayList<String> wnames = new ArrayList<>();
    ArrayList<String> trnames = new ArrayList<>();
    ArrayList<String> fnames = new ArrayList<>();
    ArrayList<String> snames = new ArrayList<>();
    ArrayList<String> sunames = new ArrayList<>();

    Context context;

    // 1 for mon ... 7 for sun ... -1 for none
    int dayClicked = -1;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String ROUTINES = "routines";

    private void loadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS, 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(ROUTINES, null);
        Type type = new TypeToken<ArrayList<RoutineItem>>() {}.getType();
        routines = gson.fromJson(json, type);

        if (routines == null){
            routines = new ArrayList<>();
        }
    }

    public static final String SHARED_PREFS2 = "sharedPrefs2";
    public static final String M = "m", T = "t", W = "w", TR = "tr", F = "f", S = "s", SU = "su";

    private void HFsaveData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS2, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mnames);
        editor.putString(M, json);

        json = gson.toJson(tnames);
        editor.putString(T, json);

        json = gson.toJson(wnames);
        editor.putString(W, json);
        json = gson.toJson(trnames);
        editor.putString(TR, json);
        json = gson.toJson(fnames);
        editor.putString(F, json);
        json = gson.toJson(snames);
        editor.putString(S, json);
        json = gson.toJson(sunames);
        editor.putString(SU, json);

        Log.d("WORK PLEASE", "saved==================");
        editor.apply();
    }

    private void HFloadData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREFS2, 0);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(M, null);
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        mnames = gson.fromJson(json, type);
        Log.d("WORK PLEASE", "LOADED==================" + json);
        if (mnames == null){
            mnames = new ArrayList<>();
        }

        json = sharedPreferences.getString(T, null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        tnames = gson.fromJson(json, type);
        Log.d("WORK PLEASE", "LOADED==================" + json);
        if (tnames == null){
            tnames = new ArrayList<>();
        }

        json = sharedPreferences.getString(W, null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        wnames = gson.fromJson(json, type);
        Log.d("WORK PLEASE", "LOADED==================" + json);
        if (wnames == null){
            wnames = new ArrayList<>();
        }

        json = sharedPreferences.getString(TR, null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        trnames = gson.fromJson(json, type);
        Log.d("WORK PLEASE", "LOADED==================" + json);
        if (trnames == null){
            trnames = new ArrayList<>();
        }

        json = sharedPreferences.getString(F, null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        fnames = gson.fromJson(json, type);
        Log.d("WORK PLEASE", "LOADED==================" + json);
        if (fnames == null){
            fnames = new ArrayList<>();
        }

        json = sharedPreferences.getString(S, null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        snames = gson.fromJson(json, type);
        if (snames == null){
            snames = new ArrayList<>();
        }

        json = sharedPreferences.getString(SU, null);
        type = new TypeToken<ArrayList<String>>() {}.getType();
        sunames = gson.fromJson(json, type);
        if (sunames == null){
            sunames = new ArrayList<>();
        }
    }

    private void JustDoit(SharedPreferences sharedPreferences, Gson gson) {
        String json;
        Type type;
        json = sharedPreferences.getString(T, null);
        type = new TypeToken<ArrayList<String>>() {
        }.getType();
        tnames = gson.fromJson(json, type);
        Log.d("WORK PLEASE", "LOADED==================" + json);
        if (tnames == null) {
            tnames = new ArrayList<>();
        }
    }


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        HFloadData();



        context = view.getContext();

        mLayout = view.findViewById(R.id.mLinLayout);
        tLayout = view.findViewById(R.id.tLinLayout);
        wLayout = view.findViewById(R.id.wLinLayout);
        trLayout = view.findViewById(R.id.trLinLayout);
        fLayout = view.findViewById(R.id.fLinLayout);
        sLayout = view.findViewById(R.id.sLinLayout);
        suLayout = view.findViewById(R.id.suLinLayout);

        mTxtView = view.findViewById(R.id.mHeader);
        tTxtView = view.findViewById(R.id.tHeader);
        wTxtView = view.findViewById(R.id.wHeader);
        trTxtView = view.findViewById(R.id.trHeader);
        fTxtView = view.findViewById(R.id.fHeader);
        sTxtView = view.findViewById(R.id.sHeader);
        suTxtView = view.findViewById(R.id.suHeader);

        clear = view.findViewById(R.id.clearBtn);


        addRoutinesToDay(mLayout, mnames);
        addRoutinesToDay(tLayout, tnames);
        addRoutinesToDay(wLayout, wnames);
        addRoutinesToDay(trLayout, trnames);
        addRoutinesToDay(fLayout, fnames);
        addRoutinesToDay(sLayout, snames);
        addRoutinesToDay(suLayout, sunames);


        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearItem();
            }
        });

        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 1;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });
        tLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 2;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });
        wLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 3;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });
        trLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 4;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });
        fLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 5;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });
        sLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 6;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });
        suLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dayClicked = 7;
                AddDaysDialog addDaysDialog = new AddDaysDialog();
                addDaysDialog.setTargetFragment(HomeFragment.this, 1);
                addDaysDialog.show(getFragmentManager(), "AddDays Dialogue");
            }
        });


        loadData();

        return view;
    }

    public void clearItem(){
        mLayout.removeViews(1,mnames.size());
        mnames.clear();
        tLayout.removeViews(1,tnames.size());
        tnames.clear();
        wLayout.removeViews(1,wnames.size());
        wnames.clear();
        trLayout.removeViews(1,trnames.size());
        trnames.clear();
        fLayout.removeViews(1,fnames.size());
        fnames.clear();
        sLayout.removeViews(1,snames.size());
        snames.clear();
        suLayout.removeViews(1,sunames.size());
        sunames.clear();
        HFsaveData();

    }

    public ArrayList<String> noRepeats(ArrayList<String> generic, ArrayList<String> specific){
        ArrayList<String> temp = new ArrayList<>();
        for(int i = 0; i <specific.size();i++){
            if(!generic.contains(specific.get(i))){
                temp.add(specific.get(i));
            }
        }
        return temp;
    }

    public void converToDay(int num){
        switch (num){
            case 1:
                mnames = noRepeats(mnames, names);
                addRoutinesToDay(mLayout, mnames);
                break;
            case 2:
                tnames = noRepeats(tnames, names);
                addRoutinesToDay(tLayout, tnames);
                break;
            case 3:
                wnames = noRepeats(wnames, names);
                addRoutinesToDay(wLayout, wnames);
                break;
            case 4:
                trnames = noRepeats(trnames, names);
                addRoutinesToDay(trLayout, trnames);
                break;
            case 5:
                fnames = noRepeats(fnames, names);
                addRoutinesToDay(fLayout, fnames);
                break;
            case 6:
                snames = noRepeats(snames, names);
                addRoutinesToDay(sLayout, snames);
                break;
            case 7:
                sunames = noRepeats(sunames, names);
                addRoutinesToDay(suLayout, sunames);
                break;
        }
    }

    public void addRoutinesToDay(LinearLayout layout, ArrayList<String> tmpnames){

        for(int i = 0; i < tmpnames.size(); i++) {
            TextView textView = new TextView(context);
            textView.setText(tmpnames.get(i));
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setPadding(25, 0, 0, 5);
            textView.setTextSize(17);
            textView.setTextColor(getResources().getColor(R.color.white));

            layout.addView(textView);
        }
        HFsaveData();
    }


    @Override
    public void ADsendInput(ArrayList<String> input) {
        names = input;
        converToDay(dayClicked);
    }
}