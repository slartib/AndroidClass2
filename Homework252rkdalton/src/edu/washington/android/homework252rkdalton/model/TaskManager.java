package edu.washington.android.homework252rkdalton.model;

//import java.util.ArrayList;
import java.util.List;

public class TaskManager
{
  private static TaskManager instance;
  private ITaskDao dao;
  long currentTaskId;
  
  
  private TaskManager()
  {
    currentTaskId = -1;
  }
  
  public static TaskManager getInstance()
  {
    if (instance == null)
    {
      instance = new TaskManager();
    }
    return instance;
  }
  
  public List<Task> getTasks() throws Exception
  {
    //ArrayList<Task> tasks = new ArrayList<Task>();
   
    return dao.getAllTasks();
  }
  
  public long addTask(String taskName, String taskText) throws Exception
  {
    return dao.addTask(taskName, taskText);
  }
  
  public void deleteTask(long taskId) throws Exception
  {
    dao.deleteTask(taskId);
  }

  public ITaskDao getDao()
  {
    return dao;
  }

  public void setDao(ITaskDao dao)
  {
    this.dao = dao;
  }

  public long getCurrentTaskId()
  {
    return currentTaskId;
  }

  public void setCurrentTaskId(long currentTaskId)
  {
    this.currentTaskId = currentTaskId;
  }
  
  
}
