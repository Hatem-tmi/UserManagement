package com.formation.usermanagement.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.formation.usermanagement.model.User;

public class Utils {

	/**
	 * Save user logged in on sharedPreferences
	 * 
	 * @param context
	 */
	public static void setUserLoggedIn(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = prefs.edit();
		editor.putBoolean("userLoggedIn", true);
		editor.commit();
	}

	/**
	 * Check if user is logged-in from sharedPreferences
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isUserLoggedIn(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		return prefs.getBoolean("userLoggedIn", false);
	}

	/**
	 * Remove user logged-in flag from sharefPrefs
	 * 
	 * @param context
	 */
	public static void removeUserLoggedInFlag(Context context) {
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(context);

		SharedPreferences.Editor editor = prefs.edit();
		editor.remove("userLoggedIn");
		editor.commit();
	}

	/**
	 * Save user data on sharedPrefs
	 * 
	 * @param context
	 * @param user
	 */
	public static void saveUserData(Context context, User user) {
		SharedPreferences prefs = context.getSharedPreferences(
				"UserDataSharedPrefs", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		editor.putString("userNom", user.getNom());
		editor.putString("userPrenom", user.getPrenom());
		editor.putInt("userAge", user.getAge());
		editor.putString("userEmail", user.getEmail());
		editor.putString("userPassword", user.getPassword());

		editor.commit();
	}

	/**
	 * Load user data on sharedPrefs
	 * 
	 * @param context
	 */
	public static User loadUserData(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				"UserDataSharedPrefs", Context.MODE_PRIVATE);

		User user = new User();
		user.setNom(prefs.getString("userNom", ""));
		user.setPrenom(prefs.getString("userPrenom", ""));
		user.setAge(prefs.getInt("userAge", -1));
		user.setEmail(prefs.getString("userEmail", ""));
		user.setPassword(prefs.getString("userPassword", ""));

		return user;
	}

	/**
	 * Remove user data from sharedPrefs
	 * 
	 * @param context
	 */
	public static void removeUserData(Context context) {
		SharedPreferences prefs = context.getSharedPreferences(
				"UserDataSharedPrefs", Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = prefs.edit();

		editor.remove("userNom");
		editor.remove("userPrenom");
		editor.remove("userAge");
		editor.remove("userEmail");
		editor.remove("userPassword");

		editor.commit();
	}
}
