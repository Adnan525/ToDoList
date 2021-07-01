package com.app.mytodolist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

public class AddTask extends AppCompatActivity {

    EditText editText;
    Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addtask);

        editText = findViewById(R.id.editNewTask);
        addButton = findViewById(R.id.taskSaveButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String description = editText.getText().toString();
                RecycleView.addNewTask(new ModelTask(description));
                Toast.makeText(v.getContext(), "New task added", Toast.LENGTH_SHORT).show();
                finish();

            }
        });

    }
}