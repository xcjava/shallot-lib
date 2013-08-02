package com.gmail.xcjava.base.image;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code128.EAN128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

public class BarcodeTool {

	/**
	 * 创建code128条形码
	 * @param filePath	存放目录
	 * @param fileName	文件名,不带扩展名
	 * @param code		条形码内容
	 * @return			条形码文件名,带扩展名
	 */
	public static String createCode128(String filePath, String fileName, String code, boolean enforced){
        
        File f = new File(filePath + fileName + ".jpg");
		if (f.exists() && !enforced) {
			return fileName + ".jpg"; 
		}
		
		File d = new File(filePath);
        if (!d.exists()) {
            d.mkdirs();
        }
		
		Code128Bean bean = new Code128Bean();
		final int dpi = 150;
		bean.doQuietZone(true);
		bean.setModuleWidth(UnitConv.in2mm(1.8f/dpi));
		bean.setHeight(15);
		bean.setQuietZone(2);
		
		try {
			File outputFile = new File(filePath + fileName + ".jpg");
	        OutputStream out;
			out = new FileOutputStream(outputFile);
			
	        try {
	            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	            bean.generateBarcode(canvas, code);
	            canvas.finish();
	            return fileName + ".jpg";
	        } catch (IOException ex) {
				ex.printStackTrace();
				return null;
			} finally {
	            try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createCode39(String filePath, String fileName, String code, boolean enforced){
		File f = new File(filePath + fileName + ".jpg");
		if (f.exists() && !enforced) {
			return fileName + ".jpg"; 
		}
		
		File d = new File(filePath);
        if (!d.exists()) {
            d.mkdirs();
        }
		
		Code39Bean bean = new Code39Bean();
		final int dpi = 150;
		bean.doQuietZone(true);
		bean.setModuleWidth(0.25);
		bean.setHeight(15);
		bean.setQuietZone(2);
		
		try {
			File outputFile = new File(filePath + fileName + ".jpg");
	        OutputStream out;
			out = new FileOutputStream(outputFile);
			
	        try {
	            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	            bean.generateBarcode(canvas, code);
	            canvas.finish();
	            return fileName + ".jpg";
	        } catch (IOException ex) {
				ex.printStackTrace();
				return null;
			} finally {
	            try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String createEan128(String filePath, String fileName, String code, boolean enforced){
		File f = new File(filePath + fileName + ".jpg");
		if (f.exists() && !enforced) {
			return fileName + ".jpg"; 
		}
		
		File d = new File(filePath);
        if (!d.exists()) {
            d.mkdirs();
        }
		
		EAN128Bean bean = new EAN128Bean();
		final int dpi = 150;
		bean.doQuietZone(true);
		bean.setModuleWidth(0.25);
		bean.setHeight(15);
		bean.setQuietZone(2);
		bean.setOmitBrackets(true);
		
		try {
			File outputFile = new File(filePath + fileName + ".jpg");
	        OutputStream out;
			out = new FileOutputStream(outputFile);
			
	        try {
	            BitmapCanvasProvider canvas = new BitmapCanvasProvider(out, "image/jpeg", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);
	            bean.generateBarcode(canvas, code);
	            canvas.finish();
	            return fileName + ".jpg";
	        } catch (IOException ex) {
				ex.printStackTrace();
				return null;
			} finally {
	            try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
    public static void main(String[] args) {
        createCode128("E:/", "code128", "032012013097131425524403", true);
        //createCode39("E:/", "code39", "032012013097131425524403", true);
        //createEan128("E:/", "ean128", "032012013097131425524403", true);
    }
}
