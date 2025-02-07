package com.hck.apptg.liaotian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.easemob.chat.EMMessage;
import com.easemob.easeui.widget.chatrow.EaseCustomChatRowProvider;
import com.easemob.exceptions.EaseMobException;
import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.liaotian.EaseChatFragment.EaseChatFragmentHelper;
import com.hck.apptg.ui.BaseActivity;
import com.hck.apptg.util.LogUtil;

/**
 * 聊天页面，需要fragment的使用{@link #EaseChatFragment}
 * 
 */
public class ChatActivity extends BaseActivity {
	public static ChatActivity activityInstance;
	private EaseChatFragment chatFragment;
	String toChatUsername;
	private User user;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.em_activity_chat);
		user = UserCacheData.getUser();
		setContentView(R.layout.em_activity_chat);
		activityInstance = this;
		// 聊天人或群id
		toChatUsername = getIntent().getExtras().getString("userId");
		// 可以直接new EaseChatFratFragment使用
		chatFragment = new EaseChatFragment();
		chatFragment.setChatFragmentHelper(new EaseChatFragmentHelper() {

			@Override
			public void onSetMessageAttributes(EMMessage message) {

				new MsgHelper(ChatActivity.this).saveUser(message);

			}

			@Override
			public EaseCustomChatRowProvider onSetCustomChatRowProvider() {
				return null;
			}

			@Override
			public void onMessageBubbleLongClick(EMMessage message) {

			}

			@Override
			public boolean onMessageBubbleClick(EMMessage message) {
				return false;
			}

			@Override
			public boolean onExtendMenuItemClick(int itemId, View view) {
				return false;
			}

			@Override
			public void onEnterToChatDetails() {

			}

			@Override
			public void onAvatarClick(String username) {

			}
		});
		// 传入参数
		chatFragment.setArguments(getIntent().getExtras());
		getSupportFragmentManager().beginTransaction()
				.add(R.id.container, chatFragment).commit();
		initTitle(toChatUsername);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityInstance = null;
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// 点击notification bar进入聊天页面，保证只有一个聊天页面
		String username = intent.getStringExtra("userId");
		if (toChatUsername.equals(username))
			super.onNewIntent(intent);
		else {
			finish();
			startActivity(intent);
		}

	}

	@Override
	public void onBackPressed() {
		chatFragment.onBackPressed();
	}

	public String getToChatUsername() {
		return toChatUsername;
	}
}
