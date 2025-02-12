package com.hck.apptg.util;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class AppManager {

	private static List<Activity> activityStack;
	private static AppManager instance;

	private AppManager() {
	}

	public static AppManager getAppManager() {
		if (instance == null) {
			instance = new AppManager();
		}
		return instance;
	}

	public void addActivity(Activity activity) {
		if (activityStack == null) {
			activityStack = new ArrayList<Activity>();
		}
		activityStack.add(activity);
	}

	public void AppExit() {
		try {

			for (int i = 0; i < activityStack.size(); i++) {
				((Activity) activityStack.get(i)).finish();
			}
			System.exit(0);
		} catch (Exception e) {
		}
	}

	public void startActivity(Activity context, Class activity) {
		context.startActivity(new Intent(context, activity));
	}

	public void startActivity(Context context, Intent intent) {
		context.startActivity(intent);
	}
}