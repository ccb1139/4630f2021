package com.example.fitnessandnutritionappfinal;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class AddDaysDialog extends DialogFragment {
    private TextView adName;
    private LinearLayout adDialogLinLay;
    private Button closeBtn;
    private Button okBtn;
    ArrayList<RoutineItem> routines;

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

    public interface ADOnInputSelected{
        void ADsendInput(ArrayList<String> input);
    }
    public ADOnInputSelected onInputSelected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_add_days_dialog, container, false);

        adName = view.findViewById(R.id.adName);
        adDialogLinLay = view.findViewById(R.id.adDialogLinLay);
        closeBtn = view.findViewById(R.id.adcloseBtn);
        okBtn = view.findViewById(R.id.adokBtn);
        loadData();
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();

        for(int i = 0; i < routines.size(); i++){
            CheckBox rtName = new CheckBox(view.getContext());
            rtName.setText(routines.get(i).getName());
            rtName.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            rtName.setTextSize(15);
            rtName.setId(i);
            checkBoxes.add(rtName);
            adDialogLinLay.addView(rtName);
        }


        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            ArrayList<String> names = new ArrayList<>();
            @Override
            public void onClick(View view) {
                for(int i = 0; i < checkBoxes.size(); i++){
                    if(checkBoxes.get(i).isChecked()){
                        names.add((checkBoxes.get(i).getText().toString()));
                        //Log.d("name: ", checkBoxes.get(i).getText().toString());
                    }
                }
                onInputSelected.ADsendInput(names);

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            onInputSelected = (ADOnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttack: ClassCast Exception :" + e.getMessage());
        }
    }
}
