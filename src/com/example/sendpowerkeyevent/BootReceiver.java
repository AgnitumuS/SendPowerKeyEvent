package com.example.sendpowerkeyevent;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
	private static final String TAG = "BootReceiver";
	@Override
	public void onReceive(Context arg0, Intent arg1) {
		Log.i(TAG, "BootReceiver");
		arg0.startService(new Intent(arg0, PowerKeySendService.class));
	}

}

