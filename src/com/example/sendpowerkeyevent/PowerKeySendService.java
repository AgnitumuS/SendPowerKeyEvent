package com.example.sendpowerkeyevent;

import android.app.Service;
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
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			if(msg.what == 1){
				sendKeyEvent(InputDevice.SOURCE_KEYBOARD, KeyEvent.KEYCODE_POWER);
				mHandler.sendEmptyMessageDelayed(1, 600000);
			}
		};
	};
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
		mHandler.sendEmptyMessageDelayed(1, 600000);
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
