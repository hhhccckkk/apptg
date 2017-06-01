package com.hck.apptg.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;
import java.lang.reflect.Field;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

/**
 * 崩溃日志信息处理工具
 * 
 * @author hck
 * @date 2017/05/22
 */
public class CrashMangerUtil implements UncaughtExceptionHandler {
	private static final String TAG = "CrashMangerUtil";
	/**
	 * CrashHandler实例
	 */
	@SuppressLint("StaticFieldLeak")
	private static CrashMangerUtil sInstance;
	/**
	 * 系统默认的UncaughtException处理类
	 */
	private UncaughtExceptionHandler mDefaultHandler;
	private Context mContext;
	private Map<String, String> mInfos = new HashMap<String, String>();
	@SuppressLint("SimpleDateFormat")
	private SimpleDateFormat mFormatter = new SimpleDateFormat(
			"yyyy-MM-dd-HH-mm-ss");
	private String mPath;

	private CrashMangerUtil() {
	}

	/**
	 * 获取CrashHandler实例 ,单例模式
	 */
	public static CrashMangerUtil getInstance() {
		if (sInstance == null) {
			sInstance = new CrashMangerUtil();
		}
		return sInstance;
	}

	/**
	 * 获取捕捉到的异常的字符串
	 */
	private static String getStackTraceString(Throwable tr) {
		if (tr == null) {
			return "";
		}
		Throwable t = tr;
		while (t != null) {
			if (t instanceof UnknownHostException) {
				return "";
			}
			t = t.getCause();
		}
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		tr.printStackTrace(pw);
		return sw.toString();
	}

	/**
	 * 初始化
	 * 
	 * @param context
	 *            上下文
	 * @param mPath
	 *            /fkh/fkhdriver/log/ 错误日志保存路径
	 */
	public void init(Context context, String mPath) {
		this.mPath = mPath;
		this.mContext = context;
		// 获取系统默认的UncaughtException处理器
		mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
		// 设置该CrashHandler为程序的默认处理器
		Thread.setDefaultUncaughtExceptionHandler(this);
	}

	/**
	 * 当UncaughtException发生时会转入该函数来处理
	 */
	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		handleException(ex);
		mDefaultHandler.uncaughtException(thread, ex);
	}

	/**
	 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
	 * 
	 * @param ex
	 *            异常信息
	 * @return true:如果处理了该异常信息;否则返回false.
	 */
	private boolean handleException(final Throwable ex) {
		if (ex == null) {
			return false;
		}
		// 收集设备参数信息
		collectDeviceInfo(mContext);
		// 保存日志文件
		String str = saveCrashInfo2File(ex);
		Log.e(TAG, str);
		return true;
	}

	/**
	 * 收集设备参数信息
	 * 
	 * @param ctx
	 *            上下文
	 */
	private void collectDeviceInfo(Context ctx) {
		try {
			PackageManager pm = ctx.getPackageManager();
			PackageInfo pi = pm.getPackageInfo(ctx.getPackageName(),
					PackageManager.GET_ACTIVITIES);
			if (pi != null) {
				String versionName = pi.versionName == null ? "null"
						: pi.versionName;
				String versionCode = pi.versionCode + "";
				mInfos.put("versionName", versionName);
				mInfos.put("versionCode", versionCode);
			}
		} catch (NameNotFoundException e) {

		}
		Field[] fields = Build.class.getDeclaredFields();

		for (Field field : fields) {
			try {
				field.setAccessible(true);
				mInfos.put(field.getName(), field.get(null).toString());
			} catch (Exception e) {

			}
		}
	}

	/**
	 * 保存错误信息到文件中
	 * 
	 * @param ex
	 *            异常信息
	 * @return 返回文件名称, 便于将文件传送到服务器
	 */
	private String saveCrashInfo2File(Throwable ex) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : mInfos.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			sb.append("[").append(key).append(", ").append(value).append("]\n");
		}
		sb.append("\n").append(getStackTraceString(ex));
		try {
			String time = mFormatter.format(new Date());
			String fileName = "Crash" + "_" + time + ".txt";
			File logFolder = new File(
					Environment.getExternalStorageDirectory(), mPath);
			if (!logFolder.exists()) {
				logFolder.mkdirs();
			}
			File filePath = new File(logFolder + File.separator + fileName);
			if (!filePath.exists()) {
				filePath.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(filePath);
			fos.write(sb.toString().getBytes());
			fos.close();
			return fileName;
		} catch (Exception e) {
			Log.e(TAG, "an error occured while writing file...", e);
		}
		return null;
	}
}
