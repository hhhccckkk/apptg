package com.hck.apptg.ui;

import com.hck.apptg.R;

import android.os.Bundle;

public class UserActivity extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		initTitle("用户中心");
	}

}
