package com.hck.apptg.ui;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost.TabSpec;

import com.hck.apptg.R;
import com.hck.apptg.data.Constant;
import com.hck.apptg.util.MyPreferences;
import com.hck.apptg.view.BadgeView;

public class MainActivity extends Activity implements OnCheckedChangeListener {
	private static final String HOME = "home";
	private static final String BAODIAN = "baodian";
	private static final String FATIE = "fatie";
	private static final String XIAOXI = "xiaoxi";
	private static final String USER = "user";
	private TabHost tabHost; // tabhost对象
	private TabSpec tabSpec1, tabSpec2, tabSpec3, tabSpec4, tabSpec5; // 现象卡对象
	public RadioButton ziYuanButton, quDaoButton, fatieButton, messageButton,
			userButton;
	private RadioGroup radioGroup;
	private Activity activity;
	private BadgeView badgeView;
	private int oldCheckId;
	private View remindView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		setListener();
	}

	private void initView() {
		tabHost = (TabHost) findViewById(R.id.tabHost);
		radioGroup = (RadioGroup) findViewById(R.id.RadioG);
		ziYuanButton = (RadioButton) findViewById(R.id.mainZiYuan);
		quDaoButton = (RadioButton) findViewById(R.id.mainQuDao);
		fatieButton = (RadioButton) findViewById(R.id.mainFaTie);
		messageButton = (RadioButton) findViewById(R.id.mainMessage);
		userButton = (RadioButton) findViewById(R.id.mainUser);
		remindView = findViewById(R.id.view_remind);
		addSpec();
	}

	private void remind(String size) {
		badgeView = new BadgeView(this, remindView);
		if (remindView == null) {
			return;
		}
		badgeView.setText(size); // 需要显示的提醒类容
		badgeView.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);// 显示的位置.右上角,BadgeView.POSITION_BOTTOM_LEFT,下左，还有其他几个属性
		badgeView.setTextColor(Color.WHITE); // 文本颜色
		badgeView.setBadgeBackgroundColor(Color.RED); // 提醒信息的背景颜色，自己设置
		badgeView.setTextSize(8); // 文本大小
		badgeView.setBadgeMargin(23, 5);
		badgeView.show();// 只有显示

	}

	private void setListener() { // 设置点击监听事件
		radioGroup.setOnCheckedChangeListener(this);
	}

	private void addSpec() {
		tabSpec1 = tabHost.newTabSpec(HOME).setIndicator(HOME)
				.setContent(new Intent(this, ZiYuanActivity.class));
		tabHost.addTab(tabSpec1);
		tabSpec2 = tabHost.newTabSpec(BAODIAN).setIndicator(BAODIAN)
				.setContent(new Intent(this, QuDaoActivity.class));
		tabHost.addTab(tabSpec2);

		tabSpec3 = tabHost.newTabSpec(FATIE).setIndicator(FATIE)
				.setContent(new Intent(this, QuDaoActivity.class));
		tabHost.addTab(tabSpec3);

		tabSpec4 = tabHost.newTabSpec(XIAOXI).setIndicator(XIAOXI)
				.setContent(new Intent(this, MessageActivity.class));
		tabHost.addTab(tabSpec4);

		tabSpec5 = tabHost.newTabSpec(USER).setIndicator(USER)
				.setContent(new Intent(this, UserActivity.class));
		tabHost.addTab(tabSpec5);
		oldCheckId = R.id.mainZiYuan;

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) { // 点击按钮事件
		switch (checkedId) {
		case R.id.mainZiYuan: // 点击第一个按钮
			tabHost.setCurrentTab(0); // 显示第一个选项卡，即跳到MainLeft
			oldCheckId = R.id.mainZiYuan;
			break;
		case R.id.mainQuDao:
			tabHost.setCurrentTab(1);
			oldCheckId = R.id.mainQuDao;
			break;
		case R.id.mainFaTie:
			fatieButton.setChecked(false);
			oldCheckId = R.id.mainFaTie;
			break;
		case R.id.mainMessage:
			tabHost.setCurrentTab(3);
			oldCheckId = R.id.mainMessage;
			if (badgeView != null) {
				badgeView.hide();
			}
			break;
		case R.id.mainUser:
			tabHost.setCurrentTab(4);
			oldCheckId = R.id.mainUser;
			break;
		}

	}

}
