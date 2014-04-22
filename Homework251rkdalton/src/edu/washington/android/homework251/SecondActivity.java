package edu.washington.android.homework251;

import edu.washington.android.homework251.model.Homework251model;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

/**
 * SecondActivity class
 * @author rick
 *
 */
public class SecondActivity extends Activity
{

  TextView useridTxt;
  TextView passwdTxt;
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_second);
    
    /* get the model */
    Homework251model model = Homework251model.getInstance();
    
    /* get the TextViews in the layout */
    useridTxt = (TextView) findViewById(R.id.useridTxt);
    passwdTxt = (TextView) findViewById(R.id.passwdTxt);
    
    /* set the values from the model */
    useridTxt.setText(model.getUsername());
    passwdTxt.setText(model.getPassword());
    
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.second, menu);
    return true;
  }

}
