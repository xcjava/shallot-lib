package com.gmail.xcjava.base.image;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

//import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <p>
 * Title: Tianyi BBS
 * </p>
 *
 * <p>
 * Description: BBSCS
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2006
 * </p>
 *
 * <p>
 * Company: Laoer.com
 * </p>
 *
 * @author Gong Tianyi
 * @version 7.0
 */
public class ImgUtil {

	private static final Log logger = LogFactory.getLog(ImgUtil.class);

	public ImgUtil() {
	}

	/**
	 * 按比例压缩图片
	 * @param imgsrc 源文件路径
	 * @param imgdist 目标(绝对)路径，使用相对路径对抛出FileNotFoundException
	 * @param widthdist 目标文件宽度
	 * @param heightdist 目标文件高度
	 * @param benchmark 0,长宽哪个长，以哪个为标准；1，以宽为基准；2，以高为基准
	 * @return false，压缩失败；true，压缩成功图片保存到imgdist路径
	 */
	/*public synchronized static boolean reduceImg(String imgsrc, String imgdist, int widthdist, int heightdist, int benchmark) throws Exception{

		try {
			File srcfile = new File(imgsrc);
			if (!srcfile.exists()) {
				return false;
			}
			Image src = javax.imageio.ImageIO.read(srcfile);
			//获取源图片的宽、高
			int width = src.getWidth(null);
			int height = src.getHeight(null);
			
			if(widthdist <= 0){
				widthdist = width;
			}
			if(heightdist <= 0){
				heightdist = height;
			}
			
			if (width <= widthdist && height <= heightdist) {
				FileUtils.copyFile(new File(imgsrc), new File(imgdist));
				return true;
			}

			// 宽度除以高度的比例
			float wh = (float) width / (float) height;
			float tmp_width = 0.0f;
			float tmp_heigth = 0.0f;
//			double ratio = 0.0f;
			
			switch (benchmark) {
			case 1:
				tmp_heigth = (float) widthdist / wh;
				tmp_width = widthdist;
//				ratio = new Double(widthdist).doubleValue() / width;
				break;
			case 2:
				tmp_heigth = heightdist;
				tmp_width = (float) heightdist * wh;
//				ratio = new Double(heightdist).doubleValue() / height;
				break;
			case 0:
			default:
				if(wh > 1){
					tmp_heigth = (float) widthdist / wh;
					tmp_width = widthdist;
//					ratio = new Double(widthdist).doubleValue() / width;
				}else{
					tmp_heigth = heightdist;
					tmp_width = (float) heightdist * wh;
//					ratio = new Double(heightdist).doubleValue() / height;
				}
				break;
			}
			
//			Image itemp = src.getScaledInstance((int) tmp_width, (int) tmp_heigth, BufferedImage.SCALE_SMOOTH);
//			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
//			itemp = op.filter((BufferedImage) src, null);
			
			//使用Image.SCALE_SMOOTH 质量较好，但是处理速度相对慢
			
//			BufferedImage tag = new BufferedImage((int) tmp_width, (int) tmp_heigth, BufferedImage.TYPE_INT_RGB);
			BufferedImage tag = new BufferedImage((int) tmp_width, (int) tmp_heigth, BufferedImage.TYPE_3BYTE_BGR);
//			tag.getGraphics().drawImage(src, 0, 0, (int) tmp_width, (int) tmp_heigth, null);
			tag.getGraphics().drawImage(src.getScaledInstance((int) tmp_width, (int) tmp_heigth, Image.SCALE_SMOOTH), 0, 0, null);
			FileOutputStream out = new FileOutputStream(imgdist);
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
			encoder.encode(tag);
			out.close();
//			ImageIO.write((RenderedImage) itemp, "png", new File(imgdist));

		} catch (IOException ex) {
			System.out.println(ex);
			logger.error(ex);
			return false;
		}
		return true;
	}*/
	
	/**
	 * 
	 * @param pressImg	水印文件路径
	 * @param imgFile	目标文件
	 * @param x			水印所在目标文件的X坐标
	 * @param y			水印所在目标文件的Y坐标
	 * 当x = 1,y = -1 时水印在右下角
	 * 当x = -1,y = -1 时水印在左下角
	 */
    public final static void createMark(String pressImg, File imgFile,int x, int y) {
    	try {
    		//目标文件  
//    		File _file = new File(targetImg);  
    		Image src = javax.imageio.ImageIO.read(imgFile);  
    		int wideth = src.getWidth(null);//为原图过滤宽度
    		int height = src.getHeight(null);//为原图过滤高度
    		//构建画板
    		BufferedImage image = new BufferedImage(wideth, height,BufferedImage.TYPE_INT_RGB);  
//    		Graphics g = image.createGraphics();  
    		Graphics2D g = image.createGraphics();//得到画笔
    		g.drawImage(src, 0, 0, wideth, height, null);//把原图片写入画板
    		//水印文件  
    		File _filebiao = new File(pressImg);  
    		Image src_biao = javax.imageio.ImageIO.read(_filebiao);  
    		int wideth_biao = src_biao.getWidth(null);  
    		int height_biao = src_biao.getHeight(null);  
    		// 水印图象的路径 水印一般为gif或者png的，这样可设置透明度 
            ImageIcon imgIcon = new ImageIcon(pressImg); 
            Image img = imgIcon.getImage(); 
            float alpha = 1f; // 透明度 
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,alpha));
            //按比例缩放水印图片
            /*if(wideth < wideth_biao && height < height_biao){
            	if(wideth_biao - wideth > height_biao - height){
            		height_biao = (wideth/2*height_biao)/wideth_biao;
                	wideth_biao = wideth/2;
            	}else{
            		wideth_biao = (height_biao/2 * wideth)/height_biao;
                	height_biao = height_biao/2;
            	}
            }else if(wideth < wideth_biao){
            	height_biao = (wideth/2*height_biao)/wideth_biao;
            	wideth_biao = wideth/2;
            }else if(height < height_biao){
            	wideth_biao = (height_biao/2 * wideth)/height_biao;
            	height_biao = height_biao/2;
            }*/
            if(x == 1 && y == -1){
            	g.drawImage(img, (wideth - wideth_biao),(height - height_biao), wideth_biao, height_biao, null);  
            }else if(x == -1 && y == -1){
            	g.drawImage(img, 0,(height - height_biao), wideth_biao, height_biao, null);
            }else{
            	g.drawImage(img, x,y, wideth_biao, height_biao, null);  
            }
    		g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER)); 
    		//水印文件结束  
    		g.dispose();  
    		FileOutputStream out = new FileOutputStream(imgFile);  
    		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
    		encoder.encode(image);  
    		out.close();  
    	} catch (IOException e) {  
    		System.out.println(e);
			logger.error(e);
    	}  
    }  
    
}
