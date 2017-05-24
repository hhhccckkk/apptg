package com.hck.apptg.view;

import android.content.Context;
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

		popupWindow.setTouchable(true);
		popupWindow.setOutsideTouchable(true);
		popupWindow.showAsDropDown(view);
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
