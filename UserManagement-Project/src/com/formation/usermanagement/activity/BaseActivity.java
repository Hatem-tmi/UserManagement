package com.formation.usermanagement.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import com.formation.usermanagement.util.Utils;

/**
 * BaseActivity used to manage kill intents sent after logout-button click
 * 
 * @author acer
 */
public class BaseActivity extends Activity {
	private static final String TAG = BaseActivity.class.getSimpleName();
	private static final String KILL_ACTION = "com.formation.usermanagement.KILL_ACTION";

	private BroadcastReceiver killReceiver = new BroadcastReceiver() {

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO
			finish();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// register killReceiver
		IntentFilter intentFilter = new IntentFilter(KILL_ACTION);
		registerReceiver(killReceiver, intentFilter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

		// unregister killReceiver
		unregisterReceiver(killReceiver);
	}

	/**
	 * Broadcast Kill intent - user logged out
	 */
	protected void broadcastKillIntent() {
		Intent intent = new Intent();
		intent.setAction(KILL_ACTION);
		sendBroadcast(intent);

		// remove user logged-in flag from sharedPrefs
		Utils.removeUserLoggedInFlag(getApplicationContext());

		// remove user from sharedPrefs
		Utils.removeUserData(getApplicationContext());
	}
}
