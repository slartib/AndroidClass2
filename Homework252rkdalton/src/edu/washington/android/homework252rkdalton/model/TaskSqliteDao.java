package edu.washington.android.homework252rkdalton.model;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class TaskSqliteDao extends SQLiteOpenHelper implements ITaskDao
{
  private static final int DATABASE_VERSION = 1;
  
  private static final String DATABASE_NAME = "taskDB";
  
  private static final String TABLE_TASKS = "tasks";
  
  private static final String COL_ID = "id";
  private static final String COL_NAME = "name";
  private static final String COL_TEXT = "text";

  public TaskSqliteDao(Context context)
  {
    super(context, DATABASE_NAME, null, DATABASE_VERSION);
    // TODO Auto-generated constructor stub
  }
  
  @Override
  public void onCreate(SQLiteDatabase db)
  {
    String CREATE_TASKS_TABLE = "CREATE TABLE " + TABLE_TASKS + "(" 
                                  + COL_ID + " INTEGER PRIMARY KEY, " 
                                  + COL_NAME + " TEXT, " 
                                  + COL_TEXT + " TEXT)";
    db.execSQL(CREATE_TASKS_TABLE);                                  
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
  {
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
    
    onCreate(db);
  }


  @Override
  public void open() throws Exception
  {
    // TODO Auto-generated method stub

  }

  @Override
  public long addTask(String taskName, String taskText) throws Exception
  {
    long taskId = -1;
    SQLiteDatabase db = this.getWritableDatabase();
    
    ContentValues values = new ContentValues();
    values.put(COL_NAME, taskName);
    values.put(COL_TEXT, taskText);
    taskId = db.insert(TABLE_TASKS, null, values);
    db.close();
    return taskId;
  }

  @Override
  public void deleteTask(long taskId) throws Exception
  {
    SQLiteDatabase db = this.getWritableDatabase();
    String[] whereValue = {String.valueOf(taskId)};
    db.delete(TABLE_TASKS, COL_ID + " = ?", whereValue);
    db.close();
  }

  @Override
  public Task getTask(long taskId) throws Exception
  {
    Task task = null;
    SQLiteDatabase db = this.getWritableDatabase();
    String[] columns = {COL_ID, COL_NAME, COL_TEXT};
    String[] whereValue = {String.valueOf(taskId)};
    
    Cursor cursor = db.query(TABLE_TASKS, columns, COL_ID + " = ?", whereValue, null, null, null, null);
    if (cursor != null);
    {
      cursor.moveToFirst();
      task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }
    return task;
  }

  @Override
  public Task findTaskByName(String taskName) throws Exception
  {
    Task task = null;
    SQLiteDatabase db = this.getWritableDatabase();
    String[] columns = {COL_ID, COL_NAME, COL_TEXT};
    String[] whereValue = {String.valueOf(taskName)};
    
    Cursor cursor = db.query(TABLE_TASKS, columns, COL_NAME + " = ?", whereValue, null, null, null, null);
    if (cursor != null);
    {
      cursor.moveToFirst();
      task = new Task(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }
    return task;
  }

  @Override
  public List<Task> getAllTasks() throws Exception
  {
    List<Task> taskList = new ArrayList<Task>();
    // Select All Query
    String selectQuery = "SELECT  * FROM " + TABLE_TASKS;
 
    SQLiteDatabase db = this.getWritableDatabase();
    Cursor cursor = db.rawQuery(selectQuery, null);
 
    // looping through all rows and adding to list
    if (cursor.moveToFirst()) {
        do {
            Task Task = new Task();
            Task.setTaskId(cursor.getInt(0));
            Task.setTaskName(cursor.getString(1));
            Task.setTaskText(cursor.getString(2));
            // Adding Task to list
            taskList.add(Task);
        } while (cursor.moveToNext());
    }
    db.close();
    // return Task list
    return taskList;
  }

  @Override
  public void updateTask(int taskId, String taskName, String taskText)
      throws Exception
  {
    SQLiteDatabase db = this.getWritableDatabase();
    String[] whereValue = {String.valueOf(taskId)};
    
    ContentValues values = new ContentValues();
    values.put(COL_NAME, taskName);
    values.put(COL_TEXT, taskText);
    db.update(TABLE_TASKS, values, COL_ID + " = ?", whereValue);
    db.close();
  }


}
