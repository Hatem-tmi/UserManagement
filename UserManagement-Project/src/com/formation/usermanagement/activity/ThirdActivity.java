package com.formation.usermanagement.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.formation.usermanagement.R;
import com.formation.usermanagement.adapter.UsersAdapter;
import com.formation.usermanagement.model.User;

public class ThirdActivity extends BaseActivity implements OnClickListener,
		OnItemClickListener, OnItemLongClickListener {

	private Button addButton;
	private Button logoutButton;
	private ListView listData;

	private UsersAdapter usersAdapter;
	private List<User> listUsers = new ArrayList<User>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_third);

		addButton = (Button) findViewById(R.id.addButton);
		logoutButton = (Button) findViewById(R.id.logoutButton);
		listData = (ListView) findViewById(R.id.listData);

		logoutButton.setOnClickListener(this);

		// Gérer le clique sur le bouton logout
		addButton.setOnClickListener(this);

		// Initialize listView (listData) with its adapter
		initData();
		usersAdapter = new UsersAdapter(getApplicationContext(), listUsers);
		listData.setAdapter(usersAdapter);

		// Gérer le click et le long click sur les items de la listView
		listData.setOnItemClickListener(this);
		listData.setOnItemLongClickListener(this);
	}

	/**
	 * Initiliaze data
	 */
	private void initData() {
		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setNom("User" + i);
			user.setPrenom("Prenom" + i);
			user.setAge(20);
			user.setEmail("email" + i);
			user.setPassword("pwd" + i);

			listUsers.add(user);
		}
	}

	/**
	 * Show Remove Dialog
	 * 
	 * @param user
	 */
	private void showRemoveConfirmationDialog(final User user) {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		alertDialogBuilder.setTitle("Suppression d'un Utilisateur")
				.setMessage("Voulez-vous vraiment supprimer?")
				.setCancelable(false);

		alertDialogBuilder.setPositiveButton("Oui",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {

						// remove user from listView
						listUsers.remove(user);
						usersAdapter.notifyDataSetChanged();
					}
				});

		alertDialogBuilder.setNegativeButton("Non", null);

		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.logoutButton:
			// broadcast kill intent
			broadcastKillIntent();

			Intent intent = new Intent(getApplicationContext(),
					MainActivity.class);
			startActivity(intent);
			break;
		case R.id.addButton:

			int s = listUsers.size();

			User user = new User();
			user.setNom("Nom" + s);
			user.setPrenom("Prenom" + s);
			user.setAge(21);
			user.setEmail("email" + s);
			user.setPassword("pwd" + s);
			listUsers.add(0, user);

			usersAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		Toast.makeText(getApplicationContext(),
				"Clique de l'item: " + listUsers.get(position).toString(),
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view,
			int position, long id) {

		User user = listUsers.get(position);

		showRemoveConfirmationDialog(user);

		return true;
	}
}
