package com.hck.apptg.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

import com.easemob.chat.EMChat;
import com.hck.apptg.R;
import com.hck.apptg.presenter.MainPresenter;
import com.hck.apptg.view.BadgeView;

public class MainActivity extends TabActivity implements
		OnCheckedChangeListener {
	private static final String ZIYUAN = "ziyuan";
	private static final String QUDAO = "qudao";
	private static final String FATIE = "fatie";
	private static final String XIAOXI = "xiaoxi";
	private static final String USER = "user";
	private TabHost tabHost; // tabhost对象
	private TabSpec tabSpec1, tabSpec2, tabSpec3, tabSpec4, tabSpec5; // 现象卡对象
	public RadioButton ziYuanButton, quDaoButton, fatieButton, messageButton,
			userButton;
	private RadioGroup radioGroup;
	private BadgeView badgeView;
	private int oldCheckId;
	private View remindView;
	private MainPresenter mMainPresenter;

	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		EMChat.getInstance().setAppInited();
		mMainPresenter = new MainPresenter(this);
		initView();
		setListener();
		//mMainPresenter.startPush();
	}

	@SuppressWarnings("deprecation")
	private void initView() {
		tabHost = getTabHost();
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
		tabSpec1 = tabHost.newTabSpec(ZIYUAN).setIndicator(ZIYUAN)
				.setContent(new Intent(this, ZiYuanActivity.class));
		tabHost.addTab(tabSpec1);
		tabSpec2 = tabHost.newTabSpec(QUDAO).setIndicator(QUDAO)
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
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.mainZiYuan:
			tabHost.setCurrentTab(0);
			oldCheckId = R.id.mainZiYuan;
			mMainPresenter.hidenPop();
			break;
		case R.id.mainQuDao:
			tabHost.setCurrentTab(1);
			oldCheckId = R.id.mainQuDao;
			mMainPresenter.hidenPop();
			break;
		case R.id.mainFaTie:
			fatieButton.setChecked(false);
			mMainPresenter.startAnimation(fatieButton);
			mMainPresenter.showPopWindown(fatieButton);
			oldCheckId = R.id.mainFaTie;
			break;
		case R.id.mainMessage:
			tabHost.setCurrentTab(3);
			oldCheckId = R.id.mainMessage;
			if (badgeView != null) {
				badgeView.hide();
			}
			mMainPresenter.hidenPop();
			break;
		case R.id.mainUser:
			tabHost.setCurrentTab(4);
			oldCheckId = R.id.mainUser;
			mMainPresenter.hidenPop();
			break;
		}

	}

}
