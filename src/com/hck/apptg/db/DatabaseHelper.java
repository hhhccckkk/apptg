package com.hck.apptg.db;

import java.sql.SQLException;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;

import com.hck.apptg.ui.MyApplication;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * 数据库DatabaseHelper
 * 
 * @author hck
 * @date 2017/01/17
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String TABLE_NAME = "apptg.db";
	private final static int VERSION_CODE = 1;
	@SuppressLint("StaticFieldLeak")
	private static DatabaseHelper sDatabaseHelper;

	private DatabaseHelper() {
		super(MyApplication.myApplication, TABLE_NAME, null, VERSION_CODE);
	}


	public static DatabaseHelper getHelper() {
		synchronized (DatabaseHelper.class) {
			if (sDatabaseHelper == null) {
				sDatabaseHelper = new DatabaseHelper();
			}
		}
		return sDatabaseHelper;
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqLiteDatabase,
			ConnectionSource connectionSource, int oldVersion, int newVersion) {

	}

	@Override
	public void onCreate(SQLiteDatabase sqLiteDatabase,
			ConnectionSource connectionSource) {
		try {
			TableUtils.createTable(connectionSource, MsgInviteBean.class);
			TableUtils.createTable(connectionSource, UserBeanDB.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public Dao getDao(Class clazz) throws SQLException {
		return super.getDao(clazz);
	}

	@Override
	public void close() {
		super.close();
		sDatabaseHelper = null;
	}
}
