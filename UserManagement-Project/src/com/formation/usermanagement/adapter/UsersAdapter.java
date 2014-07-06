package com.formation.usermanagement.adapter;

import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.formation.usermanagement.R;
import com.formation.usermanagement.model.User;

public class UsersAdapter extends BaseAdapter {
	private static final String TAG = UsersAdapter.class.getSimpleName();

	private Context context;
	private List<User> listUsers;

	private LayoutInflater inflater;

	public UsersAdapter(Context ctx, List<User> data) {
		this.context = ctx;
		this.listUsers = data;

		this.inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return this.listUsers.size();
	}

	@Override
	public Object getItem(int position) {
		return this.listUsers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		if (convertView == null) { // Vérifier si le convertView (la vue de
									// l'item) a déjà été créée
			
			Log.d(TAG, "getView position " + position + ", creating item view");

			// On récupère la vue du layout
			convertView = inflater.inflate(R.layout.user_row, parent, false);
		}

		TextView userNom = (TextView) convertView.findViewById(R.id.userNom);
		TextView userPrenom = (TextView) convertView.findViewById(R.id.userPrenom);
		TextView userAge = (TextView) convertView.findViewById(R.id.userAge);
		
		// Récupèrer le user
		User user = listUsers.get(position);
		
		// Ajouter les valeurs du user aux textviews de l'item
		userNom.setText(user.getNom());
		userPrenom.setText(user.getPrenom());
		userAge.setText("" + user.getAge());

		return convertView;
	}

}
