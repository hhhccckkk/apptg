package com.hck.apptg.presenter;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.hck.apptg.R;
import com.hck.apptg.ui.MainActivity;
import com.hck.apptg.view.PopupWindowView;
import com.hck.apptg.view.PopupWindowView.PopCallBack;

public class MainPresenter implements PopCallBack {
	private MainActivity mainActivity;
	private PopupWindowView pWindowView;

	public MainPresenter(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public void showPopWindown(Button button) {
		if (pWindowView != null) {
			hidenPop();
		} else {
			pWindowView = new PopupWindowView();
			pWindowView.showFaTieView(button, mainActivity, this);
		}

	}

	private void hidenPop() {
		if (pWindowView != null && pWindowView.popupWindow != null) {
			pWindowView.popupWindow.dismiss();
			pWindowView.popupWindow = null;
		}
		pWindowView = null;

	}

	public void startAnimation(Button fatieButton) {
		if (pWindowView == null) {
			startLeftRoat(fatieButton);
		} else {
			startRightRoat(fatieButton);
		}

	}

	private void startLeftRoat(Button fatieButton) {
		fatieButton.clearAnimation();
		Animation operatingAnim = AnimationUtils.loadAnimation(mainActivity,
				R.anim.btn_left_rotate);
		fatieButton.startAnimation(operatingAnim);
	}

	private void startRightRoat(Button fatieButton) {
		fatieButton.clearAnimation();
		Animation operatingAnim = AnimationUtils.loadAnimation(mainActivity,
				R.anim.btn_right_rotate);
		fatieButton.startAnimation(operatingAnim);

	}

	@Override
	public void fatieZiYuan() {
		hidenPop();
	}

	@Override
	public void fatieQuDao() {
		hidenPop();
	}
}
