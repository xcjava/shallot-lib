package com.gmail.xcjava.base.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;
/*import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;*/

/**
 * <p>FreeMaker生成工具</p>
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 */
public class FreeMarkerUtil {

	/**
	 * 根据模板创建文件
	 * @param contentMap		填充内容
	 * @param strTemplatePath	模板路径
	 * @param strTemplateName	模板名称
	 * @param strFilePath		生成文件路径
	 * @param strFileName		生成文件名称
	 * @param encoding			模板文件编码
	 */
	/*public static void create(Map<String,Object> contentMap, String strTemplatePath, String strTemplateName, String strFilePath, String strFileName, String tplEncoding, String outEncoding) {
		Configuration cfg = new Configuration();
		
		String strSavePath = strFilePath + strFileName;
		File FileDir = new File(strFilePath);
		
		if (!FileDir.exists()) {
			FileDir.mkdirs();
		}
		
		try {
			cfg.setDirectoryForTemplateLoading(new File(strTemplatePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template newsTemplate = cfg.getTemplate(strTemplateName);
			newsTemplate.setEncoding(tplEncoding);

			Writer out = new OutputStreamWriter(new FileOutputStream(strSavePath), outEncoding);
			try {
				newsTemplate.process(contentMap, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
	/**
	 * 根据模板返回生成结果
	 * @param contentMap		填充内容
	 * @param strTemplatePath	模板路径
	 * @param strTemplateName	模板名称
	 * @param encoding			模板文件编码
	 * @return
	 */
	/*public static String createString(Map<String,Object> contentMap, String strTemplatePath, String strTemplateName, String encoding) {
		Configuration cfg = new Configuration();
		
		try {
			cfg.setDirectoryForTemplateLoading(new File(strTemplatePath));
			cfg.setObjectWrapper(new DefaultObjectWrapper());
			Template newsTemplate = cfg.getTemplate(strTemplateName);
			newsTemplate.setEncoding(encoding);

			Writer out = new StringWriter();
			try {
				newsTemplate.process(contentMap, out);
			} catch (TemplateException e) {
				e.printStackTrace();
			}
			out.flush();
			out.close();
			return out.toString();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}*/
}
