package com.example.fitnessandnutritionappfinal;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ExerciseDialog extends DialogFragment {
    private EditText exName;
    private EditText exReps;
    private EditText exSets;
    private EditText exWeight;

    private Button closeBtn;
    private Button okBtn;

    public interface OnInputSelectedEx{
        void sendInputEx(String _name, int _reps, int _sets, int _weight);
    }
    public OnInputSelectedEx onInputSelectedEx;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_exercise_dialogue, container, false);

        exName = view.findViewById(R.id.exName);
        exReps = view.findViewById(R.id.exReps);
        exSets = view.findViewById(R.id.exSets);
        exWeight = view.findViewById(R.id.exWeight);

        closeBtn = view.findViewById(R.id.closeBtnEx);
        okBtn = view.findViewById(R.id.okBtnEx);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String iName = exName.getText().toString();
                String temp = exReps.getText().toString();
                int iReps = validateTemp(temp);
                temp = exSets.getText().toString();
                int iSets = validateTemp(temp);
                temp = exWeight.getText().toString();
                int iWeight = validateTemp(temp);
                if(!iName.equals("")){
                    onInputSelectedEx.sendInputEx(iName, iReps, iSets, iWeight);
                }


                getDialog().dismiss();
            }
        });

        return view;
    }

    public int validateTemp(String temp){
        if(temp.equals("")){
            return 0;
        }
        return Integer.parseInt(temp);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            onInputSelectedEx = (ExerciseDialog.OnInputSelectedEx) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttack: ClassCast Exception :" + e.getMessage());
        }
    }
}
