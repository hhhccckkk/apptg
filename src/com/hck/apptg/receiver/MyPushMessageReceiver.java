package com.hck.apptg.receiver;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;

import com.baidu.android.pushservice.PushMessageReceiver;
import com.hck.apptg.R;
import com.hck.apptg.bean.User;
import com.hck.apptg.data.UserCacheData;
import com.hck.apptg.model.UserModel;
import com.hck.apptg.ui.LoginActivity;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyPreferences;
import com.hck.apptg.util.PushUtils;

/*
 * Push消息处理receiver。请编写您需要的回调函数， 一般来说： onBind是必须的，用来处理startWork返回值；
 *onMessage用来接收透传消息； onSetTags、onDelTags、onListTags是tag相关操作的回调；
 *onNotificationClicked在通知被点击时回调； onUnbind是stopWork接口的返回值回调

 * 返回值中的errorCode，解释如下：
 *0 - Success 
 *10001 - Network Problem
 *30600 - Internal Server Error 
 *30601 - Method Not Allowed 
 *30602 - Request Params Not Valid
 *30603 - Authentication Failed 
 *30604 - Quota Use Up Payment Required
 *30605 -Data Required Not Found 
 *30606 - Request Time Expires Timeout 
 *30607 - Channel Token Timeout 
 *30608 - Bind Relation Not Found 
 *30609 - Bind Number Too Many

 * 当您遇到以上返回错误时，如果解释不了您的问题，请用同一请求的返回值requestId和errorCode联系我们追查问题。
 *
 */

public class MyPushMessageReceiver extends PushMessageReceiver {
	/** TAG to Log */
	public static final String TAG = MyPushMessageReceiver.class
			.getSimpleName();

	/**
	 * 在手机顶部显示通知.
	 * 
	 * @param context
	 * @param tickerText
	 * @param titleText
	 * @param contentText
	 * @param targetIntent
	 * @param type
	 */
	public void sendNotification(Context context, String tickerText,
			String titleText, String contentText, Intent targetIntent, int type) {
		try {
			clearAllNotification(context);
			NotificationManager manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			NotificationCompat.Builder builder = new NotificationCompat.Builder(
					context);
			PendingIntent pendingIntent = PendingIntent.getActivity(context,
					type, targetIntent, PendingIntent.FLAG_UPDATE_CURRENT);
			Notification notification = builder
					.setSmallIcon(R.drawable.ic_launcher)
					.setContentText(contentText)
					.setTicker(tickerText)
					.setContentTitle(titleText)
					.setContentIntent(pendingIntent)
					.setAutoCancel(true)
					.setDefaults(
							Notification.DEFAULT_SOUND
									| Notification.DEFAULT_VIBRATE).build();
			manager.notify(type, notification);
		} catch (Exception e) {
		}

	}

	/**
	 * 清除所有通知.
	 * 
	 * @param context
	 */

	public static void clearNotification(Context context, int type) {
		try {
			NotificationManager manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			manager.cancel(type);
		} catch (Exception e) {
		}

	}

	public static void clearAllNotification(Context context) {
		try {
			NotificationManager manager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);
			manager.cancelAll();

		} catch (Exception e) {
		}

	}

	/**
	 * 调用PushManager.startWork后，sdk将对push
	 * server发起绑定请求，这个过程是异步的。绑定请求的结果通过onBind返回。 如果您需要用单播推送，需要把这里获取的channel
	 * id和user id上传到应用server中，再调用server接口用channel id和user id给单个手机或者用户推送。
	 * 
	 * @param context
	 *            BroadcastReceiver的执行Context
	 * @param errorCode
	 *            绑定接口返回值，0 - 成功
	 * @param appid
	 *            应用id。errorCode非0时为null
	 * @param userId
	 *            应用user id。errorCode非0时为null
	 * @param channelId
	 *            应用channel id。errorCode非0时为null
	 * @param requestId
	 *            向服务端发起的请求id。在追查问题时有用；
	 * @return none
	 */
	@Override
	public void onBind(Context context, int errorCode, String appid,
			String userId, String channelId, String requestId) {
		// 绑定成功，设置已绑定flag，可以有效的减少不必要的绑定请求
		if (errorCode == 0) {
			PushUtils.setBind(context, true);
			PushUtils.setUC(context, userId, channelId);
			User user=UserCacheData.getUser();
			user.setPushid(channelId);
			new UserModel(context).updateUserPushId(user, false, null);
		}

	}

	/**
	 * 接收透传消息的函数。
	 * 
	 * @param context
	 *            上下文
	 * @param message
	 *            推送的消息
	 * @param customContentString
	 *            自定义内容,为空或者json字符串
	 */
	@Override
	public void onMessage(Context context, String message,
			String customContentString) {
		String messageString = "透传消息 message=\"" + message
				+ "\" customContentString=" + customContentString;
		Log.d(TAG, messageString);

		// 自定义内容获取方式，mykey和myvalue对应透传消息推送时自定义内容中设置的键和值
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LogUtil.D(" onMessage: " + messageString);
		try {
			JSONObject object = new JSONObject(message);
			Intent intent = new Intent(context, LoginActivity.class);
			if (object.getInt("open_type") == 1) {
				intent.putExtra("hasMsg", true);
			}
			String title = object.getString("title");
			String msg = object.getString("description");
			sendNotification(context, title, title, msg, intent, 1);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 接收通知点击的函数。注：推送通知被用户点击前，应用无法通过接口获取通知的内容。
	 * 
	 * @param context
	 *            上下文
	 * @param title
	 *            推送的通知的标题
	 * @param description
	 *            推送的通知的描述
	 * @param customContentString
	 *            自定义内容，为空或者json字符串
	 */
	@Override
	public void onNotificationClicked(Context context, String title,
			String description, String customContentString) {
		String notifyString = "通知点击 title=\"" + title + "\" description=\""
				+ description + "\" customContent=" + customContentString;
		Log.d(TAG, notifyString);

		// 自定义内容获取方式，mykey和myvalue对应通知推送时自定义内容中设置的键和值
		if (!TextUtils.isEmpty(customContentString)) {
			JSONObject customJson = null;
			try {
				customJson = new JSONObject(customContentString);
				String myvalue = null;
				if (customJson.isNull("mykey")) {
					myvalue = customJson.getString("mykey");
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		LogUtil.D(" onNotificationClicked: " + notifyString);

	}

	/**
	 * setTags() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示某些tag已经设置成功；非0表示所有tag的设置均失败。
	 * @param successTags
	 *            设置成功的tag
	 * @param failTags
	 *            设置失败的tag
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onSetTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onSetTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);

	}

	/**
	 * delTags() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示某些tag已经删除成功；非0表示所有tag均删除失败。
	 * @param successTags
	 *            成功删除的tag
	 * @param failTags
	 *            删除失败的tag
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onDelTags(Context context, int errorCode,
			List<String> sucessTags, List<String> failTags, String requestId) {
		String responseString = "onDelTags errorCode=" + errorCode
				+ " sucessTags=" + sucessTags + " failTags=" + failTags
				+ " requestId=" + requestId;
		Log.d(TAG, responseString);

	}

	/**
	 * listTags() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示列举tag成功；非0表示失败。
	 * @param tags
	 *            当前应用设置的所有tag。
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onListTags(Context context, int errorCode, List<String> tags,
			String requestId) {
		String responseString = "onListTags errorCode=" + errorCode + " tags="
				+ tags;

	}

	/**
	 * PushManager.stopWork() 的回调函数。
	 * 
	 * @param context
	 *            上下文
	 * @param errorCode
	 *            错误码。0表示从云推送解绑定成功；非0表示失败。
	 * @param requestId
	 *            分配给对云推送的请求的id
	 */
	@Override
	public void onUnbind(Context context, int errorCode, String requestId) {
		String responseString = "onUnbind errorCode=" + errorCode
				+ " requestId = " + requestId;
		Log.d(TAG, responseString);

	}

	@Override
	public void onNotificationArrived(Context context, String title,
			String description, String customContentString) {

		String notifyString = "onNotificationArrived  title=\"" + title
				+ "\" description=\"" + description + "\" customContent="
				+ customContentString;
		LogUtil.D(" onNotificationArrived: " + notifyString);
	}

}
