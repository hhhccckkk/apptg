package com.hck.apptg.presenter;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.hck.apptg.R;
import com.hck.apptg.data.Constant;
import com.hck.apptg.ui.FaTieActivity;
import com.hck.apptg.ui.MainActivity;
import com.hck.apptg.util.AppManager;
import com.hck.apptg.view.PopupWindowView;
import com.hck.apptg.view.PopupWindowView.PopCallBack;

/**
 * mainactivity逻辑处理类
 * 
 * @author hck
 * 
 */
public class MainPresenter implements PopCallBack {
	private MainActivity mainActivity;
	private PopupWindowView pWindowView;

	public MainPresenter(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	/**
	 * 在相应的view弹出框
	 * 
	 * @param button
	 */
	public void showPopWindown(Button button) {
		if (pWindowView != null) {
			hidenPop();
		} else {
			pWindowView = new PopupWindowView();
			pWindowView.showFaTieView(button, mainActivity, this);
		}

	}

	/**
	 * 影藏弹窗
	 */
	public void hidenPop() {
		if (pWindowView != null && pWindowView.popupWindow != null) {
			pWindowView.popupWindow.dismiss();
			pWindowView.popupWindow = null;
		}
		pWindowView = null;

	}

	/**
	 * 开启动画
	 * 
	 * @param fatieButton
	 *            发帖按钮
	 */
	public void startAnimation(Button fatieButton) {
		if (pWindowView == null) {
			startLeftRoat(fatieButton);
		} else {
			startRightRoat(fatieButton);
		}

	}

	/**
	 * 开启左转动画
	 * 
	 * @param fatieButton
	 */
	private void startLeftRoat(Button fatieButton) {
		fatieButton.clearAnimation();
		Animation operatingAnim = AnimationUtils.loadAnimation(mainActivity,
				R.anim.btn_left_rotate);
		fatieButton.startAnimation(operatingAnim);
	}

	/**
	 * 开启右转动画
	 * 
	 * @param fatieButton
	 */
	private void startRightRoat(Button fatieButton) {
		fatieButton.clearAnimation();
		Animation operatingAnim = AnimationUtils.loadAnimation(mainActivity,
				R.anim.btn_right_rotate);
		fatieButton.startAnimation(operatingAnim);

	}

	/**
	 * 点击发资源帖子回调
	 */
	@Override
	public void fatieZiYuan() {
		hidenPop();
		Intent intent = new Intent();
		intent.putExtra("type", Constant.TIEZI_TYPE_ZIYUAN);
		intent.setClass(mainActivity, FaTieActivity.class);
		AppManager.getAppManager().startActivity(mainActivity, intent);
	}

	/**
	 * 点击发渠道信息回调
	 */
	@Override
	public void fatieQuDao() {
		hidenPop();
		Intent intent = new Intent();
		intent.putExtra("type", Constant.TIEZI_TYPE_ZIYUAN);
		AppManager.getAppManager().startActivity(mainActivity, intent);
	}
}
