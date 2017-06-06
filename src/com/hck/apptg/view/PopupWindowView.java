package com.hck.apptg.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;

import com.hck.apptg.R;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyTools;

public class PopupWindowView implements OnClickListener {
	public PopupWindow popupWindow;
	private PopCallBack popCallBack;

	public interface PopCallBack {
		void fatieZiYuan();

		void fatieQuDao();

		void dissMis();

	}

	public void showFaTieView(View view, Context context,
			PopCallBack popCallBack) {
		this.popCallBack = popCallBack;
		View pView = LayoutInflater.from(context).inflate(R.layout.pop_fatie,
				null);
		popupWindow=new PopupWindow(pView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//popupWindow.setBackgroundDrawable(new ColorDrawable());
		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);

		int[] location = new int[2];
		view.getLocationOnScreen(location);
		// 获取自身的长宽高
		pView.measure(View.MeasureSpec.UNSPECIFIED,
				View.MeasureSpec.UNSPECIFIED);
		int popupHeight = pView.getMeasuredHeight();
		int popupWidth = pView.getMeasuredWidth();
		// 在控件上方显示
		int x = (location[0] + view.getWidth() / 2) - popupWidth / 2;
		int y = location[1] - popupHeight;
		popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, x, y);
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
