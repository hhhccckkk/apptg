package com.hck.apptg.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

import com.hck.apptg.R;
import com.hck.apptg.util.MyTools;

public class PopupWindowView implements OnClickListener {
	public PopupWindow popupWindow;
	private PopCallBack popCallBack;

	public interface PopCallBack {
		void fatieZiYuan();

		void fatieQuDao();

	}

	public void showFaTieView(View view, Context context,
			PopCallBack popCallBack) {
		this.popCallBack = popCallBack;
		View pView = LayoutInflater.from(context).inflate(R.layout.pop_fatie,
				null);
		popupWindow = new PopupWindow(pView, MyTools.getScreenWidth(context),
				MyTools.getScreenHeight(context));

		popupWindow.setTouchable(false);
		popupWindow.setOutsideTouchable(true);

		int[] location = new int[2];
		view.getLocationOnScreen(location);
		// 获取自身的长宽高
		pView.measure(View.MeasureSpec.UNSPECIFIED,
				View.MeasureSpec.UNSPECIFIED);
		int popupHeight = pView.getMeasuredHeight();
		int popupWidth = pView.getMeasuredWidth();

		// 在控件上方显示
		popupWindow.showAtLocation(view, Gravity.NO_GRAVITY,
				(location[0] + view.getWidth() / 2) - popupWidth / 2, location[1]
						- popupHeight);

		setListener(pView);

	}

	private void setListener(View view) {
		view.findViewById(R.id.fatie_norm).setOnClickListener(this);
		view.findViewById(R.id.fatie_sale).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fatie_norm:
			if (popCallBack != null) {
				popCallBack.fatieZiYuan();
			}
			break;
		case R.id.fatie_sale:
			if (popCallBack != null) {
				popCallBack.fatieQuDao();
			}
			break;
		default:
			break;
		}
	}
}
