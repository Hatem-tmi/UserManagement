package com.formation.usermanagement.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.formation.usermanagement.R;

public class ThirdActivity extends BaseActivity {

	private Button logoutButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		logoutButton = (Button) findViewById(R.id.logoutButton);

		// Gérer le clique sur le bouton logout
		logoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// broadcast kill intent
				broadcastKillIntent();

				Intent intent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(intent);
			}
		});
	}
}
