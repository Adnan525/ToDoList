//package com.app.mytodolist;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.EditText;
//import android.widget.Button;
//import android.widget.Toast;
//
//import com.app.mytodolist.model.ModelTask;
//import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
//
//import java.util.List;
//import java.util.Objects;
//
//public class AddTask extends BottomSheetDialogFragment {
//
//    final String TAG= "ActionBottomDialog";
//    EditText editText;
//    Button addButton;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setStyle(STYLE_NORMAL, R.style.DialogStyle);
//    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//
//        DatabaseHandler db = DatabaseHandler.getDbHandler(getActivity());
//
//        View view = inflater.inflate(R.layout.addtask, container, false);
//        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        editText = getView().findViewById(R.id.editNewTask);
//        addButton = getView().findViewById(R.id.taskSaveButton);
//        addButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(v.getContext(), "New task added", Toast.LENGTH_SHORT).show();
//                String description = editText.getText().toString();
//                System.out.println("========================> adding "+description);
//                if(description.length()>0)
//                {
//                    db.insertask(new ModelTask(description));
//                    List<ModelTask> temp = db.getAllTasks();
//                    RecycleView.addNewTask(temp);
//                    Toast.makeText(v.getContext(), "New task added", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        });
//
//        return view;
//    }
//
////    @Override
////    public void onViewCreated(View view, Bundle savedInstanceState)
////    {
////        super.onViewCreated(view, savedInstanceState);
////
////        DatabaseHandler db = DatabaseHandler.getDbHandler(getActivity());
////        addButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                Toast.makeText(v.getContext(), "New task added", Toast.LENGTH_SHORT).show();
////                String description = editText.getText().toString();
////                System.out.println("========================> adding "+description);
////                if(description.length()>0)
////                {
////                    db.insertask(new ModelTask(description));
////                    List<ModelTask> temp = db.getAllTasks();
////                    RecycleView.addNewTask(temp);
////                    Toast.makeText(v.getContext(), "New task added", Toast.LENGTH_SHORT).show();
////                }
////
////            }
////        });
////
////
////    }
//}
