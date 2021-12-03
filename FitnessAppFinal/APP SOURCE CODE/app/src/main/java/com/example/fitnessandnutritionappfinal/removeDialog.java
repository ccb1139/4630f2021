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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class removeDialog extends DialogFragment {
    private TextView rmvMsg;
    private EditText rmvIndex;

    private Button closeBtn;
    private Button okBtn;


    public interface OnInputSelectedRMV{
        void sendInputRMV(int rmvIndex);
    }
    public removeDialog.OnInputSelectedRMV onInputSelectedRMV;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_remove_dialogue, container, false);

        rmvMsg = view.findViewById(R.id.rmvMsg);
        rmvIndex = view.findViewById(R.id.rmvIndex);


        closeBtn = view.findViewById(R.id.closeBtnRMV);
        okBtn = view.findViewById(R.id.okBtnRMV);

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempThing2 = rmvIndex.getText().toString();
                if(!tempThing2.equals("")){
                    int indexRMV = Integer.parseInt(rmvIndex.getText().toString());
                    onInputSelectedRMV.sendInputRMV(indexRMV);
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
            onInputSelectedRMV = (removeDialog.OnInputSelectedRMV) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttack: ClassCast Exception :" + e.getMessage());
        }
    }
}
