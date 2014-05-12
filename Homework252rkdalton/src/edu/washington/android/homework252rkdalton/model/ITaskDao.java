package edu.washington.android.homework252rkdalton.model;

import java.util.List;

public interface ITaskDao
{
  void open() throws Exception;
  void close() throws Exception;
  long addTask(String taskName, String taskText) throws Exception;
  void deleteTask(long taskId) throws Exception;
  Task getTask(long taskId) throws Exception;
  Task findTaskByName(String taskName) throws Exception;
  List<Task> getAllTasks() throws Exception;
  void updateTask(int taskId, String taskName, String taskText) throws Exception;
}
