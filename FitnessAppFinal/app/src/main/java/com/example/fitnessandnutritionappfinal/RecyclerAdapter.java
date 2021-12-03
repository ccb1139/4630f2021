package com.example.fitnessandnutritionappfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<RoutineItem> data;
    Context context;
    private OnItemClickListener onItemClickListener;



    //For adding exercises
    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public RecyclerAdapter(Context context, ArrayList<RoutineItem> data) {
        this.data = data;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.routine_view,parent,false);
        ViewHolder viewHolder = new ViewHolder(view, onItemClickListener);

        //For Close Button


        return viewHolder;
    }

    public void debugThing(int position){

        for(int j = 0; j < data.get(position).numOfEx; j++){
            String temp = "CardID: " + String.valueOf(position) + " ex#: " + String.valueOf(j) + " --> " + data.get(position).getExercisesAsStrings(j);
            Log.d("dataCheck: ", temp);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        // current Item is clicked on card
        RoutineItem currentItem = data.get(position);
        data.get(position).setIdNum(position);

        //For adding exercises on click
        holder.rtnName.setText(currentItem.getName());
        String idNum = "ID: " + String.valueOf(position);
        holder.idIndex.setText(idNum);

        //debugThing(position);

        // This index is the clicked on spot
        int index = currentItem.getNumOfEx();
        int indexTemp = currentItem.getNumOfExTemp();
        final int[] exToRemove = {-1};

        //Need to update everytime!
        holder.linearLayout.removeAllViews();

        for(int i = 0; i < index; i++){
            //Sets up new linear layout
            LinearLayout wac = new LinearLayout(context);
            wac.setId(i);
            wac.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
            int orientation = 0;
            wac.setOrientation(orientation);

            // SETS UP THE NEW EXERCISE
            CheckBox exView = new CheckBox(context);
            exView.setText(data.get(position).getExercisesAsStrings(i));
            exView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            exView.setTextSize(15);
            data.get(position).setNumOfExTemp(index);

            //SETS UP THE SPACE
            Space space = new Space(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
            space.setLayoutParams(params);

            //ADDS THE DELETE BUTTON
            ImageView closeBtn = new ImageView(context);
            closeBtn.setImageResource(R.drawable.ic_close);
            closeBtn.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            //OnClick Listener for Delete Button
            int positionTmp = position;
            int iTmp = i;
            closeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    data.get(positionTmp).rmvExerciseItem(iTmp);
                    notifyDataSetChanged();
                }
            });

            //ADDS NEW LAYOUT
            wac.addView(exView);
            wac.addView(space);
            wac.addView(closeBtn);

            Log.d("removeTHing", String.valueOf(exToRemove[0]));
            if(exToRemove[0] != -1){
                data.get(i).rmvExerciseItem(exToRemove[0]);
            }
            else {

            }
            holder.linearLayout.addView(wac);
            //ADDS TO BIG ONE
        }
    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView rtnName;
        public TextView idIndex;
        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            rtnName = itemView.findViewById(R.id.routineNames);
            idIndex = itemView.findViewById(R.id.rnIndexNum);
            linearLayout = itemView.findViewById(R.id.exercises);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });


        }
    }
}
