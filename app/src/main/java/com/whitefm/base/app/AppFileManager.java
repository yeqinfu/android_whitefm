package com.whitefm.base.app;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;

public class AppFileManager {
	private static AppFileManager	instance			= null;
	/** 如果没有sdcard，文件保存路径 */
	private static String			CACHE_APP_ROOT_DIR	= null;
	/** 文件在sdcard中保存路径 */
	private static String			SDCARD_APP_ROOT_DIR	= null;

	private static String			DATA_APP_ROOT_DIR	= null;

	public static AppFileManager getInstance(Context context) {
		if (instance == null) {
			synchronized (AppFileManager.class) {
				if (instance == null) {
					instance = new AppFileManager(context);
				}
			}
		}

		return instance;
	}

	private AppFileManager(Context context) {
		String appPkg = context.getPackageName();

		DATA_APP_ROOT_DIR = Environment.getDataDirectory() + File.separator + appPkg + File.separator;
		SDCARD_APP_ROOT_DIR = Environment.getExternalStorageDirectory() + File.separator //
				+ "data" + File.separator + appPkg + File.separator;
		CACHE_APP_ROOT_DIR = Environment.getDataDirectory() + File.separator //
				+ "data" + File.separator + appPkg + File.separator;
	}

	/**
	 * 判断sdcard是否可用
	 */
	public boolean hasSDCard() {
		String status = Environment.getExternalStorageState();
		if (!status.equals(Environment.MEDIA_MOUNTED)) {
			return false;
		}
		return false;
	}

	/**
	 * 创建项目文件放置的根路径
	 */
	public File createFileDir() {
		File goalFile = null;
		if (hasSDCard()) {
			goalFile = new File(SDCARD_APP_ROOT_DIR);
		} else {
			goalFile = new File(CACHE_APP_ROOT_DIR);
		}

		if (!goalFile.exists()) {
			goalFile.mkdirs();
		}
		return goalFile;
	}

	public File createFileDir(String fileDir) {
		File file = new File(createFileDir(), fileDir);
		if (!file.exists()) {
			file.mkdirs();
		}
		return file;
	}

	/**
	 * 创建具体的文件
	 * 
	 * @param fileDir
	 *            文件放置路径
	 * @param fileName
	 *            文件名
	 * @return 目标文件
	 */
	public File createFile(String fileDir, String fileName) {
		if (null == fileDir || TextUtils.isEmpty(fileDir)) {
			return new File(createFileDir(), fileName);
		}
		else {
			return new File(createFileDir(fileDir), fileName);
		}
	}

	public File createFile(String fileName) {
		return createFile(null, fileName);
	}

	public File getAppDir() {
		return createFileDir();
	}

	public static final int	MEDIA_TYPE_IMAGE	= 1;
	public static final int	MEDIA_TYPE_VIDEO	= 2;
	public static final int	MEDIA_TYPE_CONTACT	= 3;
	public static final int	MEDIA_TYPE_VOICE	= 4;

	public String getOutputMediaFileName(int type) {
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
		String fileName;
		if (type == MEDIA_TYPE_IMAGE) {
			fileName = "IMG_" + timeStamp + ".jpg";
		}
		else if (type == MEDIA_TYPE_VIDEO) {
			fileName = "VID_" + timeStamp + ".mp4";
		}
		else if (type == MEDIA_TYPE_CONTACT) {
			fileName = "CON_" + timeStamp + ".jpg";
		}
		else if (type == MEDIA_TYPE_VOICE) {
			fileName = "ARM_" + timeStamp + ".amr";
		}
		else {
			return null;
		}
		return fileName;
	}

	public String getOutputMediaFileUri(String fileDir, int type) {
		File file = new File(createFileDir(fileDir), getOutputMediaFileName(type));
		return file.getAbsolutePath();
	}

	private static final int ERROR = -1;

	/**
	 * 获取手机内部剩余存储空间
	 * 
	 * @return
	 */
	public long getAvailableInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();
		return availableBlocks * blockSize;
	}

	/**
	 * 获取手机内部总的存储空间
	 * 
	 * @return
	 */
	public long getTotalInternalMemorySize() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());
		long blockSize = stat.getBlockSize();
		long totalBlocks = stat.getBlockCount();
		return totalBlocks * blockSize;
	}

	/**
	 * 获取SDCARD剩余存储空间
	 * 
	 * @return
	 */
	public long getAvailableExternalMemorySize() {
		if (hasSDCard()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long availableBlocks = stat.getAvailableBlocks();
			return availableBlocks * blockSize;
		}
		else {
			return ERROR;
		}
	}

	/**
	 * 获取SDCARD总的存储空间
	 * 
	 * @return
	 */
	public long getTotalExternalMemorySize() {
		if (hasSDCard()) {
			File path = Environment.getExternalStorageDirectory();
			StatFs stat = new StatFs(path.getPath());
			long blockSize = stat.getBlockSize();
			long totalBlocks = stat.getBlockCount();
			return totalBlocks * blockSize;
		}
		else {
			return ERROR;
		}
	}
}
