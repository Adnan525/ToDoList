//singleton


package com.app.mytodolist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.Display;

import com.app.mytodolist.model.ModelTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHandler extends SQLiteOpenHelper {

    private SQLiteDatabase db;
    private static DatabaseHandler dbHandler;

    static final int version = 1;
    static final String dbName = "ToDoList";
    static final String tableName = "todo";
     //columns
    static final String description = "taskDescription";
    static final String status = "status";
    static final String startDate = "startDate";
    static final String finishDate = "finishDate";

    //query to make table
    static final String createTable = "create table "+tableName+ "("+description+" text, "
                                                                    +status+ " boolean, "
                                                                    +startDate+ " text, "
                                                                    +finishDate+ " text);";
    static final String dropTable = "drop table if exists "+tableName;

    public DatabaseHandler(Context context)
    {
        super(context, dbName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table todo (taskDescription text primary key, status int, startDate text, finishDate text);");
        System.out.println("=========================>"+createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(dropTable);
        onCreate(db);
    }
    public static DatabaseHandler getDbHandler(Context context)
    {
        return (dbHandler==null) ? new DatabaseHandler(context) : dbHandler;
    }

    public void openDb()
    {
        db = this.getWritableDatabase();
    }

    public void insertask(ModelTask task)
    {
        ContentValues cv = new ContentValues();
        cv.put(description, task.getTaskDescription());
        cv.put(status, task.getStatus());
        cv.put(startDate, task.getStartDate());
        cv.put(finishDate, task.getFinishDate());
        db.insert(tableName, null, cv);
    }

    public List<ModelTask> getAllTasks()
    {
        this.openDb();
        List<ModelTask> taskList = new ArrayList<>();
        db.beginTransaction();
        Cursor cur = null;
        try {
            cur = db.query(tableName, null,null, null, null,null,null,null);
            if(cur.moveToFirst())
            {
                do{
                    ModelTask temp = new ModelTask(cur.getString(cur.getColumnIndex(description)),
                            cur.getInt(cur.getColumnIndex(status)),
                            cur.getString(cur.getColumnIndex(startDate)),
                            cur.getString(cur.getColumnIndex(finishDate))
                    );
                    taskList.add(temp);
                } while (cur.moveToNext());

            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        db.endTransaction();
        cur.close();

        return taskList;
    }

    public void updateTask(String desc)
    {
        ContentValues cv = new ContentValues();
        cv.put(description, desc);
        cv.put(status, 0); //complete
        cv.put(finishDate, new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date()));
        db.update(tableName, cv, description+ "=?", new String[]{desc});
    }

    public void deleteTask()
    {
        //nothing
    }
}
