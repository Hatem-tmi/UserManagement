package com.formation.usermanagement.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.formation.usermanagement.R;
import com.formation.usermanagement.model.User;
import com.formation.usermanagement.util.Utils;

public class MainActivity extends Activity {
	private static final String TAG = MainActivity.class.getSimpleName();
	private static final String SUCCESS_EMAIL = "email1";
	private static final String SUCCESS_PWD = "pwd1";

	private EditText emailEditText;
	private EditText passwordEditText;
	private Button sendButton;
	private TextView infoTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");

		// Check if user is logged in
		boolean isUserLoggedIn = Utils.isUserLoggedIn(getApplicationContext());
		if (isUserLoggedIn == true) {
			// user is logged in

			// load user data from sharedPrefs
			User user = Utils.loadUserData(getApplicationContext());

			// Go to SecondActivity
			goToSecondActivity(user);

		} else {
			// user is logged out

			setContentView(R.layout.activity_main);

			emailEditText = (EditText) findViewById(R.id.emailEditText);
			passwordEditText = (EditText) findViewById(R.id.passwordEditText);
			sendButton = (Button) findViewById(R.id.sendButton);
			infoTextView = (TextView) findViewById(R.id.infoTextView);

			sendButton.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Log.d(TAG, "sendButton onClick");

					String email = emailEditText.getText().toString();
					String pwd = passwordEditText.getText().toString();

					String txt = null;
					if (email.equals(SUCCESS_EMAIL) && pwd.equals(SUCCESS_PWD)) {
						Log.i(TAG, "Authentification success");

						// affichage du message de success
						infoTextView
								.setText(getString(R.string.loginSuccessMessage));
						infoTextView.setTextColor(getResources().getColor(
								R.color.colorSuccess));

						txt = String.format(
								"Votre email=%s - Votre mot de passe=%s",
								email, pwd);

						// Set user logged-in in sharedPrefs
						Utils.setUserLoggedIn(getApplicationContext());

						// Création de l'objet user
						User user = new User();
						user.setNom("Nom_user1");
						user.setPrenom("Prenom_user1");
						user.setAge(20);
						user.setEmail(SUCCESS_EMAIL);
						user.setPassword(SUCCESS_PWD);

						// Save user data in sharedPrefs
						Utils.saveUserData(getApplicationContext(), user);

						// Go to SecondActivity
						goToSecondActivity(user);
					} else {
						Log.i(TAG, "Authentification failure");

						// affichage du message de failure
						infoTextView
								.setText(getString(R.string.loginFailureMessage));
						infoTextView.setTextColor(getResources().getColor(
								R.color.colorFailure));

						txt = "Echec d'authentification";
					}

					Toast.makeText(getApplicationContext(), txt,
							Toast.LENGTH_LONG).show();
				}
			});
		}
	}

	private void goToSecondActivity(User user) {

		Intent intent = new Intent(MainActivity.this, SecondActivity.class);
		intent.putExtra("USER", user);
		startActivity(intent);

		finish();
	}
}
