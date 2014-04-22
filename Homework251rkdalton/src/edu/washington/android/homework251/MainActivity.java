package edu.washington.android.homework251;

import edu.washington.android.homework251.model.Homework251model;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

/**
 * Main Activity for Homework251
 * @author rick
 *
 */
public class MainActivity extends Activity
{
  EditText useridEditTxt;
  EditText passwdEditTxt;
  Button submitBtn;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    /* get interface views */
    useridEditTxt = (EditText) findViewById(R.id.useridEditTxt);
    passwdEditTxt = (EditText) findViewById(R.id.passwdEditTxt);
    submitBtn = (Button) findViewById(R.id.submitBtn);
    /* set the submit button onClick listener with an anonymous class */
    submitBtn.setOnClickListener(new OnClickListener()
    {
      public void onClick(View v)
      {
        /* get the values from the EditText views */ 
        String userid = useridEditTxt.getText().toString();
        String password = passwdEditTxt.getText().toString();
        
        boolean isValid = true;
        /* userid is a required field if it is empty set an error */
        if (userid.length() < 1)
        {
          useridEditTxt.setError("Userid is a required field, please enter a value");
          isValid = false;
        }
        else
        {
          if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userid).matches())
          {
            /* userid must be in the format of an email address, if it is not set an error */
            useridEditTxt.setError("The entered value is not a valid email address format, please enter a value of the form name@domain");
            isValid = false;
          }
        }
        if (password.length() < 1)
        {
          /* password is a required field, set an error if it is empty */
          passwdEditTxt.setError("Password is a required field, please enter a value");
          isValid = false;
        }
        if (isValid)
        {
          /* The input is valid, load the model with the values */
          Homework251model model = Homework251model.getInstance();
          model.setUsername(userid);
          model.setPassword(password);
          
          /* create an intent to move to the second activity */
          Intent intent = new Intent(MainActivity.this, SecondActivity.class);
          /* start the second activity */
          startActivity(intent);
        }
        
      }
    });
   }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

}
