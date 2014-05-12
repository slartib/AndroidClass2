package edu.washington.android.homework252rkdalton.model;

public class Task
{
  private long taskId;
  private String taskName;
  private String taskText;
  
  public Task()
  {
    
  }
  
  public Task(long id, String name, String text)
  {
    taskId = id;
    taskName = name;
    taskText = text;
  }
  
  public long getTaskId()
  {
    return taskId;
  }
  public void setTaskId(int taskId)
  {
    this.taskId = taskId;
  }
  public String getTaskName()
  {
    return taskName;
  }
  public void setTaskName(String taskName)
  {
    this.taskName = taskName;
  }
  public String getTaskText()
  {
    return taskText;
  }
  public void setTaskText(String taskText)
  {
    this.taskText = taskText;
  }

  @Override
  public String toString()
  {
    // TODO Auto-generated method stub
    return taskName;
  }
  
}
