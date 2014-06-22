package com.formation.usermanagement;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText emailEditText;
	private EditText passwordEditText;
	private Button sendButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		emailEditText = (EditText) findViewById(R.id.emailEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		sendButton = (Button) findViewById(R.id.sendButton);

		sendButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String email = emailEditText.getText().toString();
				String pwd = passwordEditText.getText().toString();

				String txt = String.format(
						"Votre email=%s\nVotre mot de passe=%s", email, pwd);

				Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG)
						.show();
			}
		});
	}
}
