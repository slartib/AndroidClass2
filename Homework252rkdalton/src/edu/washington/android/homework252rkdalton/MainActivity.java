package edu.washington.android.homework252rkdalton;

import edu.washington.android.homework252rkdalton.AddTaskDialogFragment.AddTaskDialogListener;
import edu.washington.android.homework252rkdalton.TaskFragment.OnFragmentInteractionListener;
import edu.washington.android.homework252rkdalton.model.TaskManager;
import edu.washington.android.homework252rkdalton.model.TaskSqliteDao;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity
  implements OnFragmentInteractionListener, 
  edu.washington.android.homework252rkdalton.DetailFragment.OnFragmentInteractionListener,
  AddTaskDialogListener
{
  TaskManager tm;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    tm = TaskManager.getInstance();
    tm.setDao(new TaskSqliteDao(this));
    
    setContentView(R.layout.activity_main);

  /*  if (savedInstanceState == null)
    {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new TaskFragment()).commit();
    }
    */
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {

    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();
    if (id == R.id.action_settings)
    {
      return true;
    }
    if (id == R.id.action_add)
    {
      AddTaskDialogFragment addTaskDialog = new AddTaskDialogFragment();
      addTaskDialog.show(getSupportFragmentManager(), "addTaskDialog");
    }
    if (id == R.id.action_delete)
    {
      
    }
    return super.onOptionsItemSelected(item);
  }
  
  

  /**
   * A placeholder fragment containing a simple view.
   */
  public static class PlaceholderFragment extends Fragment
  {

    public PlaceholderFragment()
    {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState)
    {
      View rootView = inflater
          .inflate(R.layout.fragment_main, container, false);
      return rootView;
    }
  }

  @Override
  public void onFragmentInteraction(String id)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onFragmentInteraction(Uri uri)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onDialogPositiveClick(AddTaskDialogFragment dialog)
  {
    String taskName = dialog.getTaskName();
    String taskText = dialog.getTaskText();
    if (taskName != null && taskName != "")
    {
      try
      {
        tm.addTask(taskName, taskText);
      }
      catch (Exception e)
      {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
  }

  @Override
  public void onDialogNegativeClick(AddTaskDialogFragment dialog)
  {
    // TODO Auto-generated method stub
    
  }

}
