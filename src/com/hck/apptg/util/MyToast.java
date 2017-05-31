package com.hck.apptg.util;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hck.apptg.R;
import com.hck.apptg.ui.MyApplication;

public class MyToast {
	private static Toast sToast;

	public static void showCustomerToast(String textString) {
		View view = LayoutInflater.from(MyApplication.myApplication).inflate(
				R.layout.toast, null);
		TextView textView = (TextView) view.findViewById(R.id.toast_text);
		if (TextUtils.isEmpty(textString)) {
			textView.setText("网络异常 请检查您的网络");
		} else {
			textView.setText(textString);
		}
		if (sToast != null) {
			sToast.cancel();
			sToast = null;
		}
		sToast = new Toast(MyApplication.myApplication);
		sToast.setDuration(Toast.LENGTH_LONG);
		sToast.setGravity(Gravity.CENTER, 0, 0);
		sToast.setView(view);
		sToast.show();

	}

	public static void dissMissToast() {
		if (sToast != null) {
			sToast.cancel();
			sToast = null;
		}
	}

}
