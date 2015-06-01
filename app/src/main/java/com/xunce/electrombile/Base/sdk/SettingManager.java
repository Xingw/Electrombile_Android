/**
 * Project Name:XPGSdkV4AppBase
 * File Name:SettingManager.java
 * Package Name:com.gizwits.framework.sdk
 * Date:2015-1-27 14:47:24
 * Copyright (c) 2014~2015 Xtreme Programming Group, Inc.
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), 
 * to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, 
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.xunce.electrombile.Base.sdk;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// TODO: Auto-generated Javadoc

/**
 * SharePreference处理类.
 *
 * @author Sunny Ding
 */
public class SettingManager {

	/** The spf. */
	SharedPreferences spf;
	
	/** The c. */
	private Context c;

	// =================================================================
	//
	// SharePreference文件中的变量名字列表
	//
	// =================================================================

	// Sharepreference文件的名字
	/** The share preferences. */
	private final String SHARE_PREFERENCES = "set";
	// 用户名
	/** The user name. */
	private final String USER_NAME = "username";
	// 手机号码
	/** The phone num. */
	private final String PHONE_NUM = "phonenumber";
	// 密码
	/** The password. */
	private final String PASSWORD = "password";
	// 用户名
	/** The token. */
	private final String TOKEN = "token";
	// 用户ID
	/** The uid. */
	private final String UID = "uid";

	/** The unit. */
	private final String UNIT = "unit";

	/** The filter. */
	static String filter = "=====";

    //did
    private final String IMEI = "imie";

    //TracksData
    private final String TRACKSDATA = "tracksData";

    public final  String  ALARMFLAG = "alarmFlag";

	//添加SOS管理员
	public final String SOS = "sos";



	/**
	 * Instantiates a new setting manager.
	 *
	 * @param c the c
	 */
	public SettingManager(Context c) {
		this.c = c;
		spf = c.getSharedPreferences(SHARE_PREFERENCES, Context.MODE_PRIVATE);
	}

	/**
	 * SharePreference clean.
	 */
	public void cleanAll() {
		setUid("");
		setToken("");
		setPhoneNumber("");
		setPassword("");
		setUserName("");
        setIMEI("");
        setAlarmFlag(false);
		setSOS("");
	}
    public void cleanDevice() {
        setIMEI("");
    }

	/**
	 * Sets the user name.
	 *
	 * @param name the new user name
	 */
	public void setUserName(String name) {
		spf.edit().putString(USER_NAME, name).commit();

	}

    public void setAlarmFlag(boolean alarmFlag) {
        spf.edit().putBoolean(ALARMFLAG, alarmFlag).commit();
    }
    public boolean getAlarmFlag() {
        return spf.getBoolean(ALARMFLAG, false);
    }


	//添加sos管理员
	public void setSOS(String list){
		spf.edit().putString(SOS, list).commit();
	}
	public String getSOS(){
		return spf.getString(SOS,"");
	}
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return spf.getString(USER_NAME, "");
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		spf.edit().putString(PHONE_NUM, phoneNumber).commit();
	}


	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return spf.getString(PHONE_NUM, "");
	}

	/**
	 * Sets the password.
	 *
	 * @param psw the new password
	 */
	public void setPassword(String psw) {
		spf.edit().putString(PASSWORD, psw).commit();
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return spf.getString(PASSWORD, "");
	}


	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		spf.edit().putString(TOKEN, token).commit();
	}

	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return spf.getString(TOKEN, "");
	}

	/**
	 * Sets the uid.
	 *
	 * @param uid the new uid
	 */
	public void setUid(String uid) {
		spf.edit().putString(UID, uid).commit();
	}

	/**
	 * Gets the uid.
	 *
	 * @return the uid
	 */
	public String getUid() {
		return spf.getString(UID, "");
	}

    public void setIMEI(String did){spf.edit().putString(IMEI,did).commit();}
    public String getIMEI(){return spf.getString(IMEI,"");}
	/**
	 * Sets the unit.
	 *
	 * @param isC the new unit
	 */
	public void setUnit(boolean isC) {
		spf.edit().putBoolean(UNIT, isC).commit();
	}

	/**
	 * Gets the unit.
	 *
	 * @return the unit
	 */
	public boolean getUnit() {
		return spf.getBoolean(UNIT, true);
	}
}
