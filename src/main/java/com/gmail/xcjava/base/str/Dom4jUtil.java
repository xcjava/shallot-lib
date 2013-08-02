package com.gmail.xcjava.base.str;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

/**
 * <p>Title: Dom4jUtil 操作XML文件</p>
 *
 * @author xiaocong
 * @email xcjava@gmail.com
 */
public class Dom4jUtil {
 
	private Document document=null;
 
	public static boolean fileExist(String fileName){
		java.io.File objFile = new java.io.File(fileName);
		if (objFile.exists()){
			return true;
		} else {
			return false;
		}
	}
 
	public void createXMLFile(String XMLFileName, String rootName) {
		if (!fileExist(XMLFileName)) {
			this.document = DocumentHelper.createDocument();
			Element element = this.document.addElement(rootName);
			// 加入注释 element.addComment(String)
			// 加入节点 element.addElement(String);
			// 加入属性内容 element.addAttribute(NAME,VALUE);
			// 设置内容 element.setText(String);
			//System.out.println("File created!");
			saveXMLFile(XMLFileName);
		} else {
			//System.out.println("File Exist!");
		}
	}
 
	/**
	 * 添加子节点
	 * @param fatherPath 父节点路径
	 * @param childName 新增子节点名称
	 * @param childValue 新增子节点值
	 */
	public void addChild(String fatherPath,String childName, String childValue) {   
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, add err!");
			return;
		}
		List list = this.document.selectNodes(fatherPath);
		Iterator iter = list.iterator();
		if (iter.hasNext()) {
			Element element = (Element) iter.next();
			Element childelement = element.addElement(childName);
			childelement.setText(childValue);   
		} else {
			//System.out.println("Father node does not exist!Add error!");
		}
	}
	
	/**
	 * 新增一个元素
	 * @param fatherPath
	 * @param e
	 */
	public void addChild(String fatherPath,Element e){
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, add err!");
			return;
		}
		List list = this.document.selectNodes(fatherPath);
		Iterator iter = list.iterator();
		if (iter.hasNext()) {
			Element element = (Element) iter.next();
			element.add(e);  
		} else {
			//System.out.println("Father node does not exist!Add error!");
		}
	}
 
	/**
	 * 修改节点元素的值
	 * @param nodePath 要修改的节点路径
	 * @param nodeName 节点元素的名称
	 * @param nodeValue 节点元素的旧值，传入null不进行校验
	 * @param newValue 新的值
	 */
	public void modifyNode(String nodePath,String nodeName,String nodeValue, String newValue){
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, modify err!");
			return;
		}
		List list = this.document.selectNodes(nodePath);
		Iterator iter = list.iterator();
		boolean nodeExist = false;
		if(nodeValue != null){
			while (iter.hasNext()) {
				Element element = (Element) iter.next();
				if (element.elementText(nodeName).equals(nodeValue)) {
					element.element(nodeName).setText(newValue);
					nodeExist = true;
				}   
			}
		}else{
			while (iter.hasNext()) {
				Element element = (Element) iter.next();
				element.element(nodeName).setText(newValue);
				nodeExist = true; 
			}
		}
		if (!nodeExist) {
			//System.out.println("Target node does not exist!Modify error!");
		}
	}
 
	/**
	 * 保存文件，用于写文件之后
	 * @param XMLFileName 文件的路径+名称
	 */
	public void saveXMLFile(String XMLFileName) {
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, save err!");
			return;
		}
		try {
			/** 将document中的内容写入文件中 */
			/*XMLWriter writer = new XMLWriter(new FileWriter(new File(XMLFileName)));
			writer.write(this.document);
			writer.close();*/
			FileOutputStream fos = new FileOutputStream(new File(XMLFileName));
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding("UTF-8");
			
			XMLWriter xw = new XMLWriter(fos, format);

			xw.write(this.document);
			xw.close();
		} catch (Exception ex) {
			//System.out.println(ex.getMessage());
		}
	}
 
	/**
	 * 读取xml文件
	 * @param XMLFileName 文件的路径+名称
	 */
	public void read(String XMLFileName){
		if (fileExist(XMLFileName)) {
			SAXReader reader = new SAXReader();
		try {
			this.document = reader.read(new File(XMLFileName));
		}catch(Exception e){} 
		/*catch (MalformedURLException e) {
			System.out.println(e.getMessage());
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}*/
		} else {
			//System.out.println("XML file does not exist,read error!");
			System.exit(0);
		}  
	}
 
	/**
	 * 获得根元素
	 * @return
	 */
	public Element getRootElement() {
		if (this.document==null)
		{
			System.out.println("Has not get XML file, get root element err!");
			return null;
		}
		return this.document.getRootElement();
	}
 
	/**
	 * 获得元素集合
	 * @param nodePath
	 * @return
	 */
	public List getNodes(String nodePath){
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, get node value err!");
			return null;
		}
		List list = this.document.selectNodes(nodePath);
		return list;
	}
	
	/**
	 * 删除节点
	 * @param nodePath
	 */
	public void deleteNode(String nodePath){
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, get node value err!");
			return;
		}
		List list = this.document.selectNodes(nodePath);
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			element.detach(); 
		}
	}
	
	/**
	 * 获得元素
	 * @param nodePath
	 * @return
	 */
	public Element getElement(String nodePath){
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, get node value err!");
			return null;
		}
		List list = this.document.selectNodes(nodePath);
		Iterator iter = list.iterator();
		while (iter.hasNext()) {
			Element element = (Element) iter.next();
			return element;
		}
		return null;
	}
	
	/**
	 * 获得节点的元素值
	 * @param nodePath
	 * @return
	 */
	public String getNodeValue(String nodePath){
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, get node value err!");
			return null;
		}
		List list = this.document.selectNodes(nodePath);
		Iterator iter = list.iterator();
		boolean nodeExist = false;
		String nodeValue = null;
		if (iter.hasNext()) {
			Element element = (Element) iter.next();
			nodeValue = element.getText();
			return nodeValue;
		} else {
			//System.out.println("Target node does not exist!Read node error!");
			System.exit(0);
		}
		return null;
	}
 
	/**
	 * 关闭，用于读写完成之后
	 */
	public void close()
	{
		if (this.document==null)
		{
			//System.out.println("Has not get XML file, close err!");
			return;
		}
		this.document=null;
	}
}