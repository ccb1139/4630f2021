package com.example.fitnessandnutritionappfinal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        RoutineItem currentItem = data.get(position);

        holder.textView.setText(currentItem.getName());

        Log.d("Position", String.valueOf(position));

        int index = currentItem.getNumOfEx();
        int indexTemp = currentItem.getNumOfExTemp();
        if(index > indexTemp){
            TextView exView = new TextView(context);
            exView.setId(index);
            exView.setText(data.get(position).getExercisesAsStrings(index-1));
            exView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            holder.linearLayout.addView(exView);
            data.get(position).setNumOfExTemp(index);
        }
    }

    @Override
    public int getItemCount() {

        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public LinearLayout linearLayout;


        public ViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            textView = itemView.findViewById(R.id.routineNames);
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
