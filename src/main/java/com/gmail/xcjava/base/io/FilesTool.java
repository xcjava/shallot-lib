package com.gmail.xcjava.base.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.NumberFormats;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 * <p>Title: 用来对磁盘文件进行读写操作</p>
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 */
public class FilesTool {

    /**
     * 创建文件，如果文件已存在则保留原文件
     * @param file String
     * @return boolean
     * @throws IOException
     */
    public static boolean create(String file)
        throws IOException {

        boolean result = false;
        File f = new File(file);
        if (! f.exists()) {
            result = f.createNewFile(); //创建文件
        }

        return result;
    }

    /**
     * 创建文件，如果文件已存在则根据标识（reserve）判断是否保留原文件
     * @param file String
     * @param reserve boolean
     * @return boolean
     * @throws IOException
     */
    public static boolean create(String file, boolean reserve)
        throws IOException {

        boolean result = false;
        File f = new File(file);
        if (! f.exists()) {
            result = f.createNewFile(); //创建文件
        } else {
            if (! reserve) { //不保留原文件
                f.delete(); //删除原文件
                result = f.createNewFile(); //创建文件
            }
        }

        return result;
    }
    
    /**
     * 有编码方式的文件创建
     * @param filePathAndName 文本文件完整绝对路径及文件名
     * @param fileContent 文本文件内容
     * @param encoding 编码方式 例如 GBK 或者 UTF-8
     * @return
     */
    public static void createFile(String filePathAndName, String fileContent, String encoding) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            PrintWriter myFile = new PrintWriter(myFilePath,encoding);
            String strContent = fileContent;
            myFile.println(strContent);
            myFile.close();
        }
        catch (Exception e) {}
    }
    
    /**
     * 复制单个文件
     * @param oldPathFile 准备复制的文件源
     * @param newPathFile 拷贝到新绝对路径带文件名
     * @return
     */
    public void copyFile(String oldPathFile, String newPathFile) {
        try {
            int bytesum = 0;
            int byteread = 0;
            File oldfile = new File(oldPathFile);
            if (oldfile.exists()) { //文件存在时
                InputStream inStream = new FileInputStream(oldPathFile); //读入原文件
                FileOutputStream fs = new FileOutputStream(newPathFile);
                byte[] buffer = new byte[1444];
                while((byteread = inStream.read(buffer)) != -1){
                    bytesum += byteread; //字节数 文件大小
                    //System.out.println(bytesum);
                    fs.write(buffer, 0, byteread);
                }
                inStream.close();
            }
        }catch (Exception e) {}
    }

    /**
     * 移动文件
     * @param oldPath
     * @param newPath
     * @return
     * @throws IOException 
     */
    public void moveFile(String oldPath, String newPath) throws IOException {
        copyFile(oldPath, newPath);
        delete(oldPath);
    }

    
    /**
     * 删除文件，如果文件为目录则删除目录下所有文件（子目录除外）
     * @param file String
     * @return boolean
     * @throws IOException
     */
    public static boolean delete(String file)
        throws IOException {

        boolean result = false;
        File f = new File(file);
        if (f.exists() && f.isFile()) { //文件
            result = f.delete();
        } else if (f.exists() && f.isDirectory()) { //目录
            File[] files = f.listFiles();
            for (int i = 0; i < files.length; i++) { //循环删除目录下所有文件（子目录除外）
                if (files[i].isFile()) {
                    files[i].delete(); //删除文件
                }
            }
            result = true;
        }

        return result;
    }

    /**
     * 重命名文件
     * @param file String 源文件
     * @param destFile String 目标文件
     * @return boolean
     * @throws IOException
     */
    public static boolean rename(String file, String destFile)
        throws IOException {

        boolean result = false;
        File f = new File(file);
        File df = new File(destFile);
        if (f.exists() && f.isFile() && ! df.exists()) {
            result = f.renameTo(df);
        }

        return result;
    }
    
    /**
     * 判断文件是否存在
     * @param file 文件名
     * @return 文件是否存在 
     * true - 文件存在 
     * false - 文件不存在
     */
    public static boolean isExist(String file) {
		File f = new File(file);
		if (f.exists()) {
			//System.out.println("File is Exist!");
			return true;
		} else {
			//System.out.println("File isn't Exist!");
			return false;
		}
	}

    /**
     * 判断文件是否为空
     * @param file 文件名
     * @return 文件是否为空
     * true - 文件为空 
     * false - 文件不为空
     * @throws IO异常
     */
	public static boolean isEmpty(String file) throws IOException {
		FileReader fr = new FileReader(file);
		if (fr.read() == -1) {
			//System.out.println("File is Empty!");
			return false;
		} else {
			//System.out.println("File isn't Empty!");
			return true;
		}
	}
	
	/**
	 * 获取文件扩展名
	 * @param file 文件名
	 * @return 扩展名
	 */
	public static String getExtension(String file){
		int index = file.lastIndexOf(".");
		return file.substring(index);
	}
	
	/**
	 * 从文件夹中读取文件名列表
	 * @param dirPath			文件夹路径
	 * @param containSubDir		是否包含子文件夹
	 * @return					文件名列表
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static List<String> readFileName(String dirPath, boolean containSubDir) throws FileNotFoundException, IOException {   
		List<String> fileNameList = new ArrayList<String>();   
		try {   
			File file = new File(dirPath);
			if (!file.isDirectory()) {   
				fileNameList.add(file.getName());   
			} else {   
				String[] filelist = file.list();   
				for (int i = 0; i < filelist.length; i++) {   
					File readfile = new File(dirPath + File.separator + filelist[i]);   
					if (!readfile.isDirectory()) {   
						fileNameList.add(readfile.getName());   
					} else {
						if(containSubDir)
							readFileName(dirPath + File.separator + filelist[i], true);   
					}   
				}   
		
			}   
		} catch (FileNotFoundException e) {   
			e.printStackTrace();   
			System.out.println("readfile()   Exception:" + e.getMessage());   
		} 
		
		return fileNameList;   
	}
	
	/**
	 * 从文件夹中读取文件夹名列表
	 * @param dirPath			文件夹路径
	 * @return                  文件夹名列表
	 */
	public static List<String> readDirectoryName(String dirPath){
		List<String> directoryNameList = new ArrayList<String>();
		File file = new File(dirPath);
		if(file == null)return null;
		if(file.isDirectory()){
			String[] fileList = file.list();
			for(String s : fileList){
				File readFile = new File(dirPath + File.separator + s);
				if(readFile.isDirectory())directoryNameList.add(readFile.getName());
			}
		}
		return directoryNameList;
	}
	
	/**
	 * 将文件流输出到客户端
	 * @param linkedHashMap
	 * @param response
	 * @throws IOException 
	 * @throws WriteException 
	 */
	public static void writeToExcel(LinkedHashMap<String,LinkedHashMap<String,String>> linkedHashMap, LinkedHashMap<Integer,String> numCellMap, HttpServletResponse response) throws IOException, WriteException{
		if(linkedHashMap != null){
			ServletOutputStream sos = response.getOutputStream();  
			WritableWorkbook wwb = null; 
			wwb = Workbook.createWorkbook(sos);
	        // 创建Excel工作表   
	        WritableSheet ws = wwb.createSheet("sheet0", 0);// 创建sheet
	        LinkedHashMap<String,String> mapLabel = linkedHashMap.get("0");
	        int argumentsCount = linkedHashMap.size();
	        List<String> titleList = new ArrayList<String>();
	        for(Map.Entry<String, String> entry:mapLabel.entrySet()){
	        	titleList.add(entry.getKey());
	        }
			//第一行表头
	        for(int i=0;i<titleList.size();i++){
	        	Label label = new Label(i, 0, titleList.get(i));
	        	try {
					ws.addCell(label);
				} catch (RowsExceededException e) {
					e.printStackTrace();
				} 
	        }
	        //开始填充内容
	        for(int k = 0;k<argumentsCount;k++){
	        	LinkedHashMap<String,String> lhm = linkedHashMap.get(java.lang.String.valueOf(k));
	        	List<String> contentList = new ArrayList<String>();
	        	for(Map.Entry<String, String> entry:lhm.entrySet()){
	        		contentList.add(entry.getValue());
	            }
	        	for(int i=0;i<contentList.size();i++){
	        		
	            	try {
	            		if(numCellMap != null && numCellMap.size() > 0 && !StringUtils.isEmpty(numCellMap.get(i))){
	            			jxl.write.Number numb = null;
	            			if("float".equals(numCellMap.get(i)))
	            				numb = new jxl.write.Number(i, k+1, Double.parseDouble(contentList.get(i)));
	            			if("int".equals(numCellMap.get(i)))
	            				numb = new jxl.write.Number(i, k+1, Integer.parseInt(contentList.get(i)));
	            			ws.addCell(numb);
	            		}else{
	            			WritableCellFormat cellFormat = new WritableCellFormat(NumberFormats.TEXT); 
	            			Label label = new Label(i, k+1, contentList.get(i),cellFormat);
	            			ws.addCell(label);
	            		}
	    			} catch (RowsExceededException e) {
	    				e.printStackTrace();
	    			} 
	            }
	        }
	        response.setContentType("UTF-8");
	        response.setHeader("Content-type","application/x-msexcel");
	    	// 输出流   
	        wwb.write();  
	        // 关闭流   
	        wwb.close();
		}
	}
}
