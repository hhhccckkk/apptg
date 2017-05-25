package com.hck.apptg.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hck.apptg.R;

/**
 * 公用title.
 */
public class TitleBar extends LinearLayout {
	private LinearLayout mLeftBackBtn; // 左边返回按钮
	private TextView mCenterTextV; 
	private TextView mLeftTextV; 
	private TextView mRightTextV;
	private Context mContext;

	public TitleBar(Context context) {
		super(context);
		mContext = context;
		init(context);
	}

	public TitleBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init(context);
	}

	/**
	 * 初始化view.
	 * 
	 * @param context
	 */
	private void init(Context context) {
		LayoutInflater.from(context).inflate(R.layout.title_bar, this);
		mLeftBackBtn = (LinearLayout) findViewById(R.id.left_btn);
		mCenterTextV = (TextView) findViewById(R.id.home_title_center);
		mRightTextV = (TextView) findViewById(R.id.home_title_right);

	}

	public void hiddenBackBtn() {
		mLeftBackBtn.setVisibility(View.GONE);
	}

	public void setTitleLeftContent(String content) {
		mCenterTextV.setText(content);
	}

	public TextView getLeftTextView() {
		return mLeftTextV;
	}

	public TextView getCenterTextView() {
		return mCenterTextV;
	}

	public LinearLayout getBack() {
		return mLeftBackBtn;
	}

	public TextView getRightTextView() {
		return mRightTextV;
	}

	public void setLeftText(String textString) {
		mLeftTextV.setText(textString);
	}

	public void setCenterText(String textString) {
		mCenterTextV.setText(textString);
	}

	public void setRightText(String textString) {
		mRightTextV.setText(textString);
	}

	public void setLeftTextColor(int color) {
		mLeftTextV.setTextColor(color);
	}

	public void setCentrTextColor(int color) {
		mCenterTextV.setTextColor(color);
	}

	public void setRightTextColor(int color) {
		mRightTextV.setTextColor(color);
	}

}
