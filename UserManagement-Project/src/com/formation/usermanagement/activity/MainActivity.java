package com.formation.usermanagement.activity;

import com.formation.usermanagement.R;
import com.formation.usermanagement.R.color;
import com.formation.usermanagement.R.id;
import com.formation.usermanagement.R.layout;
import com.formation.usermanagement.R.string;
import com.formation.usermanagement.model.User;

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
							"Votre email=%s - Votre mot de passe=%s", email,
							pwd);

					// Création de l'objet user
					User user = new User();
					user.setNom("Nom_user1");
					user.setPrenom("Prenom_user1");
					user.setAge(20);
					user.setEmail(SUCCESS_EMAIL);
					user.setPassword(SUCCESS_PWD);

					// Go to SecondActivity
					Intent intent = new Intent(MainActivity.this,
							SecondActivity.class);
					intent.putExtra("USER", user);
					startActivity(intent);

					finish();
				} else {
					Log.i(TAG, "Authentification failure");

					// affichage du message de failure
					infoTextView
							.setText(getString(R.string.loginFailureMessage));
					infoTextView.setTextColor(getResources().getColor(
							R.color.colorFailure));

					txt = "Echec d'authentification";
				}

				Toast.makeText(getApplicationContext(), txt, Toast.LENGTH_LONG)
						.show();
			}
		});
	}
}
