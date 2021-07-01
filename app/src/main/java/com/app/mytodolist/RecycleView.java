package com.app.mytodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity {

    RecyclerView recycleTasks;
    static AdapterTask adapter;
    static List<ModelTask> taskListRec;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        getSupportActionBar().hide();


        taskListRec = new ArrayList<>();
        for(int i=0; i<=2; i++)
            taskListRec.add(new ModelTask());


        recycleTasks = findViewById(R.id.recycleTasks);
        fab=findViewById(R.id.fab);
        recycleTasks.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AdapterTask(taskListRec);
        recycleTasks.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starAddTask();
            }
        });
    }

    public void starAddTask()
    {
        Intent addtask = new Intent(this, AddTask.class);
        startActivity(addtask);
    }

    public static void addNewTask(ModelTask newTask)
    {
        taskListRec.add(newTask);
        adapter.notifyItemInserted(taskListRec.size()-1);
    }
}