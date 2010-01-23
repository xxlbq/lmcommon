package com.lm.common.util.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Add one sentence class summary here. Add class description here.
 * 
 * @author JHF Team <jhf@bestwiz.cn>
 * @copyright 2008, BestWiz(Dalian) Co.,Ltd
 */
public class FileTool
{
	/**
	 * create dir
	 * 
	 * @param path
	 */
	public static void createDir(String path)
	{
		File dir = new File(path);
		if (!dir.exists())
			dir.mkdir();
	}

	/**
	 * create file
	 * 
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	public static void createFile(String path, String filename)
			throws IOException
	{
		File file = new File(path + filename);
		if (!file.exists())
			file.createNewFile();
	}

	/**
	 * delete file
	 * 
	 * @param path
	 * @param filename
	 */
	public static void delFile(String path, String filename)
	{
		File file = new File(path + "/" + filename);
		if (file.exists() && file.isFile())
			file.delete();
	}

	/**
	 * recursive delete dir
	 * 
	 * @param path
	 */
	public static void delDir(String path)
	{
		File dir = new File(path);
		if (dir.exists())
		{
			File[] tmp = dir.listFiles();
			for (int i = 0; i < tmp.length; i++)
			{
				if (tmp[i].isDirectory())
				{
					delDir(path + "/" + tmp[i].getName());
				} else
				{
					tmp[i].delete();
				}
			}
			dir.delete();
		}
	}

	/**
	 * change file dir
	 * 
	 * @param filename
	 * @param oldpath
	 * @param newpath
	 * @param cover
	 */
	public static void changeDirectory(String filename, String oldpath,
			String newpath, boolean cover)
	{
		if (!oldpath.equals(newpath))
		{
			File oldfile = new File(oldpath + "/" + filename);
			File newfile = new File(newpath + "/" + filename);
			if (newfile.exists())
			{
				if (cover)
					oldfile.renameTo(newfile);
			} else
			{
				oldfile.renameTo(newfile);
			}
		}
	}

	/**
	 * file rename
	 * 
	 * @param path
	 * @param oldname
	 * @param newname
	 */
	public static void renameFile(String path, String oldname, String newname)
	{
		if (!oldname.equals(newname))
		{
			File oldfile = new File(path + "/" + oldname);
			File newfile = new File(path + "/" + newname);
			if (!newfile.exists())
				oldfile.renameTo(newfile);
		}
	}

	/**
	 * copy file
	 * 
	 * @param src
	 * @param dest
	 * @throws IOException
	 */
	public static void copyFile(String src, String dest) throws IOException
	{
		FileInputStream in = new FileInputStream(src);
		File file = new File(dest);
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file);
		int c;
		byte buffer[] = new byte[1024];
		while ((c = in.read(buffer)) != -1)
		{
			for (int i = 0; i < c; i++)
				out.write(buffer[i]);
		}
		in.close();
		out.close();
	}
}
