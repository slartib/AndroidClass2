package edu.washington.android.homework253.services;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.provider.Settings;

public class BeepService extends Service
{
  private final IBinder binder = new BeepServiceBinder();
  private boolean beepOn = false;
  private boolean serviceOn = false;
  private Handler beepHandler = new Handler();
  private BeepRunnable beepRunnable = new BeepRunnable();
  private class BeepRunnable implements Runnable
  {
    private MediaPlayer player; 

    @Override
    public void run()
    {
      if (beepOn)
      {
        player = MediaPlayer.create(BeepService.this,  Settings.System.DEFAULT_NOTIFICATION_URI);
        player.start();
        beepHandler.postDelayed(this, 5000);
      }      
    }    
  }
  public BeepService()
  {
    
    super();
    // TODO Auto-generated constructor stub
  }
  
  public class BeepServiceBinder extends Binder 
  {
    public BeepService getService() 
    {
        // Return this instance of BeepService so clients can call public methods
        return BeepService.this;
    }
  }


  @Override
  public IBinder onBind(Intent intent)
  {
    return binder;
  }

  @Override
  public boolean onUnbind(Intent intent)
  {
    return super.onUnbind(intent);
  }


  @Override
  public int onStartCommand(Intent intent, int flags, int startId)
  {
    beepOn = false;
    return super.onStartCommand(intent, flags, startId);
  }

  public void startBeeping()
  {
    beepOn = true;
    beepHandler.post(beepRunnable);
  }
  
  public void stopBeeping()
  {
    beepOn = false;
  }

  public boolean isBeepOn()
  {
    return beepOn;
  }
}
