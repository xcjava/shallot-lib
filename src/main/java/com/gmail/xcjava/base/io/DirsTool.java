package com.gmail.xcjava.base.io;

import java.io.File;
import java.io.IOException;

/**
 * <p>Title: 磁盘文件夹的操作</p>
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 */
public class DirsTool {

    /**
     * 创建目录，包括创建必需但不存在的父目录
     * @param dir String
     * @return boolean
     * @throws IOException
     */
    public static boolean create(String dir)
        throws IOException {

        boolean result = false;
        File d = new File(dir);
        if (!d.exists()) {
            result = d.mkdirs(); //创建目录
        }

        return result;
    }

    /**
     * 删除目录，连同目录下所有内容全部删除
     * @param file String
     * @return boolean
     * @throws IOException
     */
    public static boolean delete(String dir)
        throws IOException {

        boolean result = false;
        File d = new File(dir);
        if (d.exists() && d.isDirectory()) { //目录
            clear(d); //删除目录下所有内容
        }
        result = d.delete();

        return result;
    }

    /**
     * 删除目录下所有内容，但不包括目录本身
     * @param dir String
     * @return boolean
     * @throws IOException
     */
    public static boolean clear(String dir)
        throws IOException {

        boolean result = false;
        File f = new File(dir);
        File[] files = f.listFiles();
        for (int i = 0; i < files.length; i++) { //循环删除目录下所有文件（子目录除外）
            if (files[i].isDirectory()) {
                clear(files[i]);
            }
            result = files[i].delete(); //删除文件
        }

        return result;
    }

    /**
     * 删除目录下所有内容，但不包括目录本身
     * @param dir File
     * @return boolean
     * @throws IOException
     */
    public static boolean clear(File dir)
        throws IOException {

        boolean result = false;
        File[] files = dir.listFiles();
        for (int i = 0; i < files.length; i++) { //循环删除目录下所有文件（子目录除外）
            if (files[i].isDirectory()) {
                clear(files[i]);
            }
            result = files[i].delete(); //删除文件
        }

        return result;
    }

    /**
     * 重命名目录
     * @param file String 源文件
     * @param destFile String 目标文件
     * @return boolean
     * @throws IOException
     */
    public static boolean rename(String dir, String destDir)
        throws IOException {

        boolean result = false;
        File d = new File(dir);
        File dd = new File(destDir);
        if (d.exists() && d.isDirectory() && ! dd.exists()) {
            result = d.renameTo(dd);
        }

        return result;
    }
    
    /**
     * 获取类根目录
     * @return
     */
    public static String getClassRootPath(){
    	String path = DirsTool.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if ("\\".equals(File.separator)) {
			path = path.substring(1);
			path = path.replace("/", "\\");
		}
		//linux
		if ("/".equals(File.separator)) {
			path = path.substring(0);
			path = path.replace("\\", "/");
		}
		return path;
    }
    
    /**
     * 获取网站根目录
     * @return
     * @throws IOException
     */
    public static String getWebRootPath() throws IOException{
    	String path = DirsTool.class.getProtectionDomain().getCodeSource().getLocation().getPath();
    	if(path.indexOf("/WEB-INF/classes") == -1)
    		throw new IOException("无法找到网站根目录");
    	
		//windwos
		if ("\\".equals(File.separator)) {
			path = path.substring(1, path.indexOf("/WEB-INF/classes") + 1);
			path = path.replace("/", "\\");
		}
		//linux
		if ("/".equals(File.separator)) {
			path = path.substring(0, path.indexOf("/WEB-INF/classes") + 1);
			path = path.replace("\\", "/");
		}

		return path;
    }
    
}
