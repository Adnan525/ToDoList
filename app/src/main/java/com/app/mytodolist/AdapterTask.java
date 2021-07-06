package com.app.mytodolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.recyclerview.widget.RecyclerView;

import com.app.mytodolist.model.ModelTask;

import java.util.List;

public class AdapterTask extends RecyclerView.Adapter<AdapterTask.ViewHolder>{

    private List<ModelTask> toDoList;
    private RecycleView recycleView;
    private DatabaseHandler db;
    private RecycleView activity;

    public AdapterTask(List<ModelTask> paraList)
    {
        this.toDoList = paraList;
    }
    public AdapterTask(DatabaseHandler db, RecycleView activity) {
        this.db = db;
        this.activity = activity;
    }


    @Override
    public AdapterTask.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View taskView = LayoutInflater.from(parent.getContext()).inflate(R.layout.taskview, parent, false);
        return new ViewHolder(taskView);
    }



    //set the view attributes based on the data
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        ModelTask td = toDoList.get(position);
        holder.checkBox.setText(td.getTaskDescription());
        holder.checkBox.setChecked(td.getStatus()==0);

    }



    @Override
    public int getItemCount() {
        return this.toDoList.size();
    }

//    public void setTasks(List<ModelTask> temp)
//    {
//        this.toDoList = temp;
//        notifyDataSetChanged();
//    }

    public Context getContext() {
        return activity;
    }

    public void setTasks(List<ModelTask> todoList) {
        RecycleView.adapter.toDoList = todoList;
        RecycleView.adapter.notifyItemInserted(todoList.size()-1);
    }

    public void changeStatus(int position)
    {
        db.openDb();

        ModelTask item = toDoList.get(position);

        db.updateTask(item.getTaskDescription());
        RecycleView.adapter.setTasks(db.getAllTasks());
        RecycleView.adapter.notifyDataSetChanged();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;

        public ViewHolder(View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.taskViewCheckBox);
        }
    }
}
