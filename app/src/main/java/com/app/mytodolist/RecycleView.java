package com.app.mytodolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.app.mytodolist.model.ModelTask;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class RecycleView extends AppCompatActivity {

    private DatabaseHandler db;
    RecyclerView recycleTasks;
    static AdapterTask adapter;
    static List<ModelTask> taskListRec;
    FloatingActionButton fab;

    @Override
    public void onResume() {

        super.onResume();
        DatabaseHandler db = new DatabaseHandler(this);
        db.openDb();
        taskListRec = db.getAllTasks();
//        if (linearLayoutState != null) {
//            linearLayoutManager.onRestoreInstanceState(linearLayoutState);
//        }
        // Here comes the code to populate your data.
        // I'm not sure how you do this, so I just copy/paste your code
//        for (ModelTask m : taskListRec){
//            newList.add(new ListItemi(item));
//        }
        // Now instatiate and add the adapter to the RecyclerView
        adapter = new AdapterTask(taskListRec);
        recycleTasks.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview);

        getSupportActionBar().hide();

        db = new DatabaseHandler(this);
        db.openDb();

        taskListRec = db.getAllTasks();


        recycleTasks = findViewById(R.id.recycleTasks);
        fab=findViewById(R.id.fab);
        recycleTasks.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new AdapterTask(taskListRec);
        adapter = new AdapterTask(db,RecycleView.this);
        recycleTasks.setAdapter(adapter);
        adapter.setTasks(taskListRec);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new RecyclerItemTouchHelper(adapter));
        itemTouchHelper.attachToRecyclerView(recycleTasks);

//        if(taskListRec.isEmpty())
//            recycleTasks.setVisibility(View.GONE);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                starAddTask();
            }
        });
    }

    public void starAddTask()
    {
        db.openDb();

        BottomSheetDialog bottomSheet = new BottomSheetDialog(this);
        bottomSheet.setContentView(R.layout.addtask);
//        FrameLayout flayout = d.findViewById(R.id.design_bottom_sheet);
//        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        EditText editText = bottomSheet.findViewById(R.id.editNewTask);
        Button addButton = bottomSheet.findViewById(R.id.taskSaveButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editText.getText().toString();
                if(description.length()>0)
                {
                    db.insertask(new ModelTask(description));
                    adapter.setTasks(db.getAllTasks());
                    adapter.notifyItemInserted(db.getAllTasks().size()-1);

                    //to fix size
                    Intent intent = getIntent();
                    startActivity(intent);
                    finish();
//                    List<ModelTask> temp = db.getAllTasks();
//                    addNewTask(temp);
                    Toast.makeText(v.getContext(), "New task added, task size "+db.getAllTasks().size(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        bottomSheet.show();
//        Intent addtask = new Intent(this, AddTask.class);
//        startActivity(addtask);
    }

//    public static void addNewTask(List<ModelTask> ls)
//    {
////        System.out.println("=======================");
////        for(ModelTask m:ls)
////            System.out.println(m.getTaskDescription());
////        System.out.println("=======================");
//
//        taskListRec.add(ls.get(ls.size()-1));
//        adapter.notifyItemInserted(taskListRec.size()-1);
//    }

//    public void changeStatus(int position)
//    {
//        DatabaseHandler db = new DatabaseHandler(this);
//
//        ModelTask taskTemp = taskListRec.get(position);
//        db.openDb();
//        db.updateTask(taskTemp.getTaskDescription());
//        adapter.notifyItemChanged(position);
//
//    }
}