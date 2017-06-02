package com.hck.apptg.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.easemob.easeui.controller.EaseUI;
import com.easemob.easeui.controller.EaseUI.EaseUserProfileProvider;
import com.easemob.easeui.domain.EaseUser;
import com.hck.apptg.util.AppManager;
import com.hck.apptg.util.MyPreferences;
import com.hck.apptg.view.CustomAlertDialog;
import com.hck.apptg.view.TitleBar;

public class BaseActivity extends FragmentActivity {
	public TitleBar mTitleBar;
	public TextView leftTextView, centerTextView, rightTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void setContentView(int layout) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		AppManager.getAppManager().addActivity(this);
		initTitleBar();
		ViewGroup root = getRootView();
		View paramView = getLayoutInflater().inflate(layout, null);
		root.addView(mTitleBar, LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		root.addView(paramView, LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		setBackBtnListener();
		super.setContentView(root);
	}

	public void setContentView(View view) {
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		initTitleBar();
		ViewGroup root = getRootView();
		View paramView = view;
		root.addView(mTitleBar, LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		root.addView(paramView, LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT);
		super.setContentView(root);
	}

	private ViewGroup getRootView() {
		LinearLayout localLinearLayout = new LinearLayout(this);
		localLinearLayout.setOrientation(LinearLayout.VERTICAL);
		return localLinearLayout;
	}

	private void initTitleBar() {
		mTitleBar = new TitleBar(this);
		leftTextView = mTitleBar.getLeftTextView();
		centerTextView = mTitleBar.getCenterTextView();
		rightTextView = mTitleBar.getRightTextView();
	}

	private void setBackBtnListener() {
		mTitleBar.getBack().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				clickBackBtn();
			}
		});
	}

	public void clickBackBtn() {
		finish();
	}

	public void initTitle(String title) {
		mTitleBar.setCenterText(title);
	}

	public TitleBar getTitleBar() {
		return mTitleBar;
	}

	public String getStringData(int id) {
		return getResources().getString(id);
	}

	public void alertExitD() {
		CustomAlertDialog alertDialog = new CustomAlertDialog(this);
		alertDialog.setCancelable(true);
		alertDialog.setCanceledOnTouchOutside(true);
		alertDialog.setLeftText("退出");
		alertDialog.setRightText("好评");
		alertDialog.setTitle("提示");
		alertDialog.setMessage("确定要退出吗?");
		alertDialog.setOnLeftListener(new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				exit();
			}
		});

		alertDialog.setOnRightListener(new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				haoPing();
			}
		});
		alertDialog.show();

	}

	private void haoPing() {
		Uri uri = Uri.parse("market://details?id=" + getPackageName());
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);

	}

	private void exit() {
		MyPreferences.saveString("user", null);
		AppManager.getAppManager().AppExit();
		finish();
	}

	public void hiddenBackBtn() {
		mTitleBar.hiddenBackBtn();
	}

	public String getText(EditText editText) {
		return editText.getText().toString();
	}

	protected <T extends View> T getViewById(int id) {
		return (T) findViewById(id);
	}

}
