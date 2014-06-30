package com.formation.usermanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends Activity {
    private static final String TAG = "SecondActivity";

    private User user = null;
    private TextView userNomValue;
    private TextView userPrenomValue;
    private TextView userAgeValue;
    private TextView userEmailValue;
    private Button logoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
	Log.d(TAG, "onCreate");
	super.onCreate(savedInstanceState);
	setContentView(R.layout.second_activity);

	userNomValue = (TextView) findViewById(R.id.userNomValue);
	userPrenomValue = (TextView) findViewById(R.id.userPrenomValue);
	userAgeValue = (TextView) findViewById(R.id.userAgeValue);
	userEmailValue = (TextView) findViewById(R.id.userEmailValue);
	logoutButton = (Button) findViewById(R.id.logoutButton);

	// Gérer le click sur le logout button
	logoutButton.setOnClickListener(new OnClickListener() {
	    @Override
	    public void onClick(View v) {
		Intent intent = new Intent(getApplicationContext(),
			MainActivity.class);
		startActivity(intent);

		finish();
	    }
	});

	if (getIntent() != null && getIntent().hasExtra("USER")) {
	    user = (User) getIntent().getSerializableExtra("USER");

	    Log.d(TAG, user.toString());
	    userNomValue.setText(user.getNom());
	    userPrenomValue.setText(user.getPrenom());
	    userAgeValue.setText("" + user.getAge());
	    userEmailValue.setText(user.getEmail());
	}

    }

    @Override
    protected void onResume() {
	Log.d(TAG, "onResume");
	super.onResume();
    }

    @Override
    protected void onPause() {
	Log.d(TAG, "onPause");
	super.onPause();
    }

    @Override
    protected void onDestroy() {
	Log.d(TAG, "onDestroy");
	super.onDestroy();
    }
}
