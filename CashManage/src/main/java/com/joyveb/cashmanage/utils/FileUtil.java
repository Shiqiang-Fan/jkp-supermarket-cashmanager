package com.joyveb.cashmanage.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
@Slf4j
public class FileUtil {
	private static final int BUFFER_SIZE = 100 * 1024;
	private static final String TMP = ".INPROGRESS";

	public static StringBuilder readFileByLines(String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		String tempString = null;
		List<String> list = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		try {
			System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				sb.append(tempString);
				list.add(tempString);
				// line++;
			}
			reader.close();
		} catch (IOException e) {
			log.warn("IO异常",e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
					log.warn("FileUtil IO Exception",e1);
				}
			}
		}
		return sb;
	}

	// 删除图片的方法(接收一个路径)

	/**
	 * 递归删除目录下的所有文件及子目录下所有文件
	 * 
	 * @param sPath
	 *            将要删除的文件目录
	 * @return boolean Returns "true" if all deletions were successful. If a
	 *         deletion fails, the method stops attempting to delete and returns
	 *         "false".
	 */
	public static boolean deleteDir(File sPath) {
		if (sPath.isDirectory()) {
			String[] children = sPath.list();
			// 递归删除目录中的子目录下
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDir(new File(sPath, children[i]));
				if (!success) {
					return false;
				}
			}
		}
		// 目录此时为空，可以删除
		return sPath.delete();
	}

	public boolean DeleteFolder(String sPath) {
		File file = new File(sPath);
		// 判断目录或文件是否存在
		if (!file.exists()) {
			// 不存在返回
			return false;
		} else {
			// 判断是否为文件
			if (file.isFile()) {
				// 为文件时调用删除文件方法
				return deleteFile(sPath);
			} else {
				// 为目录时调用删除目录方法
				File sPath1 = new File(sPath);
				return deleteDir(sPath1);
			}
		}
	}

	/**
	 * * 删除单个文件 *
	 * 
	 * @param sPath
	 *            被删除文件的文件名 *
	 * @return 单个文件删除成功返回true，否则返回false
	 * */
	public boolean deleteFile(String sPath) {
		boolean flag = false;
		File file = new File(sPath);
		// 路径为文件且不为空则进行删除
		if (file.isFile() && file.exists()) {
			file.delete();
			flag = true;
		}
		return flag;
	}

}
