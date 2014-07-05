package com.formation.usermanagement.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class BootReceiver extends BroadcastReceiver {
	private static final String TAG = BootReceiver.class.getSimpleName();

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.e(TAG, "onReceive");

		Toast.makeText(context, "Boot Completed", Toast.LENGTH_LONG).show();
	}
}
