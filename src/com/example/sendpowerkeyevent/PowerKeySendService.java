package com.example.sendpowerkeyevent;

<<<<<<< HEAD
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
=======
import android.app.Service;
>>>>>>> origin/master
import android.content.Intent;
import android.hardware.input.InputManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.InputDevice;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;

public class PowerKeySendService extends Service {

	private static final String TAG = "PowerKeySendService";
	@Override
	public void onCreate() {
		Log.i(TAG, "onCreate");
		try{
			Intent intent = new Intent();
			intent.setClassName("com.ilogs.clientbeatsender", "com.ilogs.clientbeatsender.MainActivity");
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
			startActivity(intent);
		}catch (Exception e){
			//no handle
			e.printStackTrace();
		}
		AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
		PendingIntent pIntent = PendingIntent.getBroadcast(this, 0, new Intent("android.example.action.power_key"),  0);
		alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 10000, 10000, pIntent);
	}
	
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		sendKeyEvent(InputDevice.SOURCE_KEYBOARD, KeyEvent.KEYCODE_POWER);
		return Service.START_STICKY;
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	
	 private void sendKeyEvent(int inputSource, int keyCode) {
	        long now = SystemClock.uptimeMillis();
	        injectKeyEvent(new KeyEvent(now, now, KeyEvent.ACTION_DOWN, keyCode, 0, 0,
	                KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0, inputSource));
	        injectKeyEvent(new KeyEvent(now, now, KeyEvent.ACTION_UP, keyCode, 0, 0,
	                KeyCharacterMap.VIRTUAL_KEYBOARD, 0, 0, inputSource));
	    }

	 private void injectKeyEvent(KeyEvent event) {
	        Log.i(TAG, "injectKeyEvent: " + event);
	        InputManager.getInstance().injectInputEvent(event,
	                InputManager.INJECT_INPUT_EVENT_MODE_WAIT_FOR_FINISH);
	    }


}
