package com.hck.apptg.liaotian;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.easemob.easeui.widget.EaseTitleBar;
import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.ui.BaseActivity;
import com.hck.apptg.ui.BaseFragment;

/**
 * 聊天页面，需要fragment的使用{@link #EaseChatFragment}
 * 
 */
public class ChatFragment extends BaseFragment {
	public static ChatFragment activityInstance;
	private EaseChatFragment chatFragment;
	private EaseTitleBar easeTitleBar;
	private User userBean;
	String toChatUsername;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_chat, null);
		activityInstance = this;
		toChatUsername = getActivity().getIntent().getExtras()
				.getString(EaseConstant.EXTRA_USER_ID);
		chatFragment = new EaseChatFragment();
		chatFragment.setArguments(getActivity().getIntent().getExtras());
		getActivity().getSupportFragmentManager().beginTransaction()
				.add(R.id.container, chatFragment).commit();
		return view;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		activityInstance = null;
	}

}
