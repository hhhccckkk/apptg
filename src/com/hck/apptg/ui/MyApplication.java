package com.hck.apptg.ui;

import android.app.Application;
import android.graphics.Bitmap;

import com.baidu.android.pushservice.ADPushManager;
import com.baidu.android.pushservice.PushConstants;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.hck.apptg.R;
import com.hck.apptg.data.Constant;
import com.hck.apptg.data.MyData;
import com.hck.apptg.util.LogUtil;
import com.hck.apptg.util.MyLocation;
import com.hck.apptg.util.MyPreferences;
import com.hck.apptg.util.MyToast;
import com.hck.apptg.util.PushUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class MyApplication extends Application {
	private static final int memoryCacheSize = 1024 * 1024 * 5;
	public static MyApplication myApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		myApplication = this;
		startPush();
		LogUtil.isPrintLog = true;
		new MyPreferences(this);
		getLocation();
		initImagerLoder();
	}

	private void initImagerLoder() {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.cacheOnDisc(true).cacheInMemory(false)
				.showImageOnFail(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.bitmapConfig(Bitmap.Config.RGB_565).build();
		ImageLoaderConfiguration config2 = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCache(new WeakMemoryCache())
				.memoryCacheSize(memoryCacheSize)
				.discCacheSize(50 * 1024 * 1024).discCacheFileCount(100)
				.defaultDisplayImageOptions(options)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).enableLogging()
				.build();
		ImageLoader.getInstance().init(config2);

	}

	private void startPush() {
		if (!PushUtils.hasBind(getApplicationContext())) {
			ADPushManager.startWorkForAD(getApplicationContext(),
					PushConstants.LOGIN_TYPE_API_KEY, Constant.PUSH_KEY);
		}
	}

	private void getLocation() {
		MyLocation.startLocation(this, new BDLocationListener() {

			@Override
			public void onReceiveLocation(BDLocation arg0) {
				MyData.bdLocation = arg0;
				MyToast.showCustomerToast("地址: "+arg0.getCity());
			}

			@Override
			public void onConnectHotSpotMessage(String arg0, int arg1) {

			}
		});
	}

}
