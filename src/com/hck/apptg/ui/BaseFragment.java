package com.hck.apptg.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hck.httpserver.RequestParams;

public class BaseFragment extends Fragment{
	public View mRootView;
	public RequestParams params;
   public void startShowOneUserActivity(Context context,String uid){
	   Intent intent =new Intent();
       intent.putExtra("uid", uid);
      // intent.setClass(context, ShowOneUserActivity.class);
       startActivity(intent);
   }
}
