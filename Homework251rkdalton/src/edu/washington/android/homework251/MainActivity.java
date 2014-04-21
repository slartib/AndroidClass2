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

public class MainActivity extends Activity
{
  EditText useridTxt;
  EditText passwdTxt;
  Button submitBtn;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    useridTxt = (EditText) findViewById(R.id.userid);
    passwdTxt = (EditText) findViewById(R.id.passwd);
    submitBtn = (Button) findViewById(R.id.submitBtn);
    submitBtn.setOnClickListener(new OnClickListener()
    {
      public void onClick(View v)
      {
        String userid = useridTxt.getText().toString();
        String password = passwdTxt.getText().toString();
        boolean isValid = true;
        if (userid.length() < 1)
        {
          useridTxt.setError("Userid is a required field, please enter a value");
          isValid = false;
        }
        else
        {
          if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userid).matches())
          {
            useridTxt.setError("The entered value is not a valid email address format, please enter a value of the form name@domain");
            isValid = false;
          }
        }
        if (password.length() < 1)
        {
          passwdTxt.setError("Password is a required field, please enter a value");
          isValid = false;
        }
        if (isValid)
        {
          Homework251model model = Homework251model.getInstance();
          model.setUsername(userid);
          model.setPassword(password);
          Intent intent = new Intent(MainActivity.this, SecondActivity.class);
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
