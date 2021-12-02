package com.example.fitnessandnutritionappfinal;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

public class RoutineDialog extends DialogFragment {
    private EditText editTextRoutineName;
    private Button closeBtn;
    private Button okBtn;

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected onInputSelected;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layour_routine_diaglouge, container, false);

        editTextRoutineName = view.findViewById(R.id.rtName);
        closeBtn = view.findViewById(R.id.closeBtn);
        okBtn = view.findViewById(R.id.okBtn);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = editTextRoutineName.getText().toString();
                if(!input.equals("")){
                    onInputSelected.sendInput(input);
                }


                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            onInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttack: ClassCast Exception :" + e.getMessage());
        }
    }

}
