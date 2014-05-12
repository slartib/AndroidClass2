package edu.washington.android.homework252rkdalton;

import edu.washington.android.homework252rkdalton.model.TaskManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class AddTaskDialogFragment extends DialogFragment
{
  public interface AddTaskDialogListener 
  {
    public void onDialogPositiveClick(AddTaskDialogFragment dialog);
    public void onDialogNegativeClick(AddTaskDialogFragment dialog);
  }

  private EditText taskNameText;
  private EditText taskTextText;
  private String taskName = "";
  private String taskText = "";
  private TaskManager tm = TaskManager.getInstance();
  private AddTaskDialogListener listener;

  public AddTaskDialogFragment()
  {
    // TODO Auto-generated constructor stub
  }
  

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    LayoutInflater inflater = getActivity().getLayoutInflater();
    View myView = inflater.inflate(R.layout.add_task_dialog, null);
    
    taskNameText = (EditText) myView.findViewById(R.id.taskNameText);
    taskTextText = (EditText) myView.findViewById(R.id.taskTextText);
    builder.setTitle(R.string.add_task_dialog_title);
    builder.setView(myView);
    builder.setPositiveButton(R.string.ok_button, 
                              new DialogInterface.OnClickListener() 
                              { 
                                public void onClick(DialogInterface dialog, int id)
                                {
                                    taskName = taskNameText.getText().toString();
                                    taskText = taskTextText.getText().toString();
                                    listener.onDialogPositiveClick(AddTaskDialogFragment.this);
                                    dialog.dismiss();
                                }
                              });
    builder.setNegativeButton(R.string.cancel_button, 
        new DialogInterface.OnClickListener() 
        { 
          public void onClick(DialogInterface dialog, int id)
          {
            listener.onDialogNegativeClick(AddTaskDialogFragment.this);
            dialog.cancel();
          }
        });
                            
    
    
    return builder.create();
  }


  public String getTaskName()
  {
    return taskName;
  }

  public String getTaskText()
  {
    return taskText;
  }


  @Override
  public void onAttach(Activity activity)
  {
    super.onAttach(activity);
    try 
    {
      // Instantiate the AddTaskDialogListener so we can send events to the host
      listener = (AddTaskDialogListener) activity;
    } 
    catch (ClassCastException e) 
    {
      // The activity doesn't implement the interface, throw exception
      throw new ClassCastException(activity.toString()
              + " must implement NoticeDialogListener");
    }
  }

}
