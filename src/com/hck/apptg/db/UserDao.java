package com.hck.apptg.db;

import java.sql.SQLException;
import java.util.List;

import com.hck.apptg.util.LogUtil;
import com.j256.ormlite.dao.Dao;

public class UserDao {
	private Dao<UserBeanDB, Long> mUserDao;

	@SuppressWarnings("unchecked")
	public UserDao() {
		try {
			mUserDao = DatabaseHelper.getHelper().getDao(UserBeanDB.class);
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.D("SQLException: " + e);
		}
	}

	public void saveUser(UserBeanDB userBeanDB) {
		if (mUserDao != null) {
			try {
				mUserDao.createOrUpdate(userBeanDB);
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.D("SQLException: saveUser" + e);
			}
		}
	}

	public UserBeanDB getUserByName(String name) {
		if (mUserDao != null) {
			try {
				List<UserBeanDB> userBeanDBs = mUserDao.queryForEq("userName",
						name);
				if (userBeanDBs != null && !userBeanDBs.isEmpty()) {
					return userBeanDBs.get(0);
				}
				return null;
			} catch (SQLException e) {
				e.printStackTrace();
				LogUtil.D("SQLException: saveUser" + e);
				return null;
			}
		}
		return null;
	}

}
