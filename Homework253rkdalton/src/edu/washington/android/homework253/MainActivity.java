package edu.washington.android.homework253;

import edu.washington.android.homework253.services.BeepService;
import edu.washington.android.homework253.services.BeepService.BeepServiceBinder;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.os.Build;
import android.provider.Settings;

public class MainActivity extends Activity implements OnClickListener
{
  private int NOTIFICATION_ID = 1234;
  private NotificationManager notificationManager;
  private Notification notification;

  private BeepService beepService;
  private BeepServiceBinder beepServiceBinder;
  private static Button startStopBtn;
  private boolean bound = false;
  private String startTxt;
  private String stopTxt;
  
  @SuppressLint("NewApi")
  @SuppressWarnings("deprecation")
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    if (savedInstanceState == null)
    {
      /*getFragmentManager().beginTransaction()
          .add(R.id.container, new PlaceholderFragment()).commit(); */
    }
    startStopBtn = (Button) findViewById(R.id.startStopBtn);
    startStopBtn.setOnClickListener(MainActivity.this);
    startTxt = getResources().getString(R.string.start_txt);
    stopTxt = getResources().getString(R.string.stop_txt);
    // Get the NotificationManager
    notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    
    // Create a PendingIntent so that the Notification knows who to call...
    //Intent mainIntent = new Intent(this.getApplicationContext(), MainActivity.class);
    Intent mainIntent = new Intent(Intent.ACTION_MAIN);
    mainIntent.setClass(getApplicationContext(), MainActivity.class);
    mainIntent.setAction(Intent.ACTION_MAIN);
    mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    //Intent mainIntent = new Intent(this, MainActivity.class);    
    //mainIntent.setAction(Intent.ACTION_MAIN);
    //mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    
    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
    PendingIntent mainPendingIntent = PendingIntent.getActivity(this, 0, mainIntent, 0);
    
    // NOTE This creates a problem we will have to address in the manifest
    
    // Create the Normal Notification
    notification =  new Notification.Builder(this)
      .setContentIntent(mainPendingIntent)
      .setContentTitle("BeepNotification")
      .setContentText("Beeping")
      .setSmallIcon(R.drawable.ic_launcher)
      .setOngoing(true)
      .getNotification();
    
  }
  
  private ServiceConnection connection = new ServiceConnection()
      {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service)
        {
          beepServiceBinder = (BeepServiceBinder) service;
          beepService = beepServiceBinder.getService();         
          bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name)
        {
          bound = false;          
        }
      };

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
    return super.onOptionsItemSelected(item);
  }

  /**
   * A placeholder fragment containing a simple view.
   */
  /*
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
*/
  @Override
  public void onClick(View v)
  {    
    String startBtnTxt = startStopBtn.getText().toString();
    if (bound)
    {
      if (startBtnTxt.equals(startTxt))
      {
        if (!beepService.isBeepOn())
        {
          beepService.startBeeping();
          startStopBtn.setText(R.string.stop_txt);
        }
        else
        {
          startStopBtn.setText(R.string.stop_txt);
        }
      }
      else
      {
        if (beepService.isBeepOn())
        {
          beepService.stopBeeping();
          startStopBtn.setText(R.string.start_txt);
        }
        else
        {
          startStopBtn.setText(R.string.start_txt);         
        }        
      }      
    }    
  }

  @Override
  protected void onStart()
  {
    super.onStart();
    Intent intent = new Intent(this, BeepService.class);
    bindService(intent, connection, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onPause()
  {
    // TODO Auto-generated method stub
    super.onPause();
    if (beepService.isBeepOn())
    {
      notificationManager.notify(NOTIFICATION_ID, notification);
    }
  }

  @Override
  protected void onResume()
  {
    notificationManager.cancel(NOTIFICATION_ID);
    super.onResume();
  }


}
