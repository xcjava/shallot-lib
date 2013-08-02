package com.gmail.xcjava.base.web;

import javax.servlet.jsp.tagext.*;

/**
 * 分页标签
 * @author xcjava@gmail.com
 * @version 2.0
 * 
 * --------------使用说明--------------
 * <%@ taglib prefix="gdcct" uri="http://www.xiaocong.net/gdcct/tags"%>
 * <gdcct:pager id="hxcPager1" pageStaticMax="0" pageIndex="${pageIndex}" recordCount="${recordCount}" pageSize="${pageSize}"
 *     	pageFirstURL="/testPager.jsp" pageDynamicURLFormat="/testPager.jsp?p={0}&code=0001" 
 *     	fontPageCSS="fontPage1" otherPageCSS="otherPage1"></gdcct:pager>
 * ---------------------------------------   
 */
public class PagerTag extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 总记录数
     */
    private String recordCount="1";
    
    /**
     * 当前页码
     */
    private String pageIndex = "1";
    
    /**
     * 总页数
     */
    private String pageCount="1";
    
    /**
     * 每页的信息数量
     */
    private String pageSize="1";
    
    /**
     * 首页地址
     */
    private String pageFirstURL="http://www.gdcct.gov.cn";
    
    /**
     * 静态页数量
     */ 
    private String pageStaticMax="0";
    
    /**
     * 静态页链界格式
     */
    private String pageStaticURLFormat="http://www.gdcct.gov.cn/Static{0}";
    
    /**
     * 动态页链接格式
     */
    private String pageDynamicURLFormat="http://www.gdcct.gov.cn/Dynamic?p={0}";
    
    /**
     * 当前页CSS样式
     */
    private String fontPageCSS="font_page";
    
    /**
     * 其他页CSS样式
     */
    private String otherPageCSS="other_page";
    
    /**
     * 标签id
     */
    private String id;
    
    
    
	
	public String getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(String recordCount) {
		this.recordCount = recordCount;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

	public String getPageCount() {
		return pageCount;
	}

	public void setPageCount(String pageCount) {
		this.pageCount = pageCount;
	}

	public String getPageSize() {
		return pageSize;
	}

	public void setPageSize(String pageSize) {
		this.pageSize = pageSize;
	}

	public String getPageFirstURL() {
		return pageFirstURL;
	}

	public void setPageFirstURL(String pageFirstURL) {
		this.pageFirstURL = pageFirstURL;
	}

	public String getPageStaticMax() {
		return pageStaticMax;
	}

	public void setPageStaticMax(String pageStaticMax) {
		this.pageStaticMax = pageStaticMax;
	}

	public String getPageStaticURLFormat() {
		return pageStaticURLFormat;
	}

	public void setPageStaticURLFormat(String pageStaticURLFormat) {
		this.pageStaticURLFormat = pageStaticURLFormat;
	}

	public String getPageDynamicURLFormat() {
		return pageDynamicURLFormat;
	}

	public void setPageDynamicURLFormat(String pageDynamicURLFormat) {
		this.pageDynamicURLFormat = pageDynamicURLFormat;
	}

	public String getFontPageCSS() {
		return fontPageCSS;
	}

	public void setFontPageCSS(String fontPageCSS) {
		this.fontPageCSS = fontPageCSS;
	}

	public String getOtherPageCSS() {
		return otherPageCSS;
	}

	public void setOtherPageCSS(String otherPageCSS) {
		this.otherPageCSS = otherPageCSS;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String creatPaper()
    {
		StringBuilder strPaper 	= new StringBuilder();
		StringBuilder sb 		= new StringBuilder();
		String strInfoNum 		= "";
		
		int intRecordCount 		= toInt(this.recordCount,0);
		int intPageIndex 		= toInt(this.pageIndex,0);
		int intPageSize 		= toInt(this.pageSize,0);
		int intPageStaticMax 	= toInt(this.pageStaticMax,0);
		int intPageCount 		= intRecordCount%intPageSize==0?intRecordCount/intPageSize:intRecordCount/intPageSize+1;
		this.pageCount 			= Integer.toString(intPageCount);
		
        if (intPageIndex > 1){
            if ((intPageIndex - 1) <= intPageStaticMax){
                strPaper.append("<a class=" + this.otherPageCSS + " href=" + pageStaticURLFormat.replace("{0}", Integer.toString(intPageIndex - 1)) + ">上一页</a>");
            }else{
                strPaper.append("<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(intPageIndex - 1)) + ">上一页</a>");
            }
        }

        if (intPageCount < 10){
            for (int i = 1; i <= intPageCount; i++){
                if (i <= intPageStaticMax){
                    if (i == intPageIndex){
                        strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                    }else{
                        strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageStaticURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "</a>]");
                    }
                }else{
                    if (i == intPageIndex){
                        strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                    }else{
                        strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "</a>]");
                    }
                }
            }
        }else{
            if (intPageIndex < 5){
                for (int i = 1; i <= 8; i++){
                    if (i <= intPageStaticMax){
                        if (i == intPageIndex){
                            strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                        }else{
                            strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageStaticURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "</a>]");
                        }
                    }else{
                        if (i == intPageIndex){
                            strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                        }else{
                            strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "</a>]");
                        }
                    }
                }
                strPaper.append("...[<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(intPageCount)) + ">" + Integer.toString(intPageCount) + "</a>]");
            }else if (intPageIndex > intPageCount - 4){
                strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageFirstURL + ">1</a>]...");
                for (int i = intPageCount - 8; i <= intPageCount; i++){
                    if (i <= intPageStaticMax){
                        if (i == intPageIndex){
                            strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                        }else{
                            strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageStaticURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "</a>]");
                        }
                    }else{
                        if (i == intPageIndex){
                            strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                        }else{
                            strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "</a>]");
                        }
                    }
                }
            }else{
                strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageFirstURL + ">1</a>]...");
                for (int i = intPageIndex - 3; i < intPageIndex + 4; i++){
                    if (i <= intPageStaticMax){
                        if (i == intPageIndex){
                            strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                        }else{
                            strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageStaticURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "<a>]");
                        }
                    }else{
                        if (i == intPageIndex){
                            strPaper.append("[<span class=" + this.fontPageCSS + ">" + Integer.toString(intPageIndex) + "</span>]");
                        }else{
                            strPaper.append("[<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(i)) + ">" + Integer.toString(i) + "<a>]");
                        }
                    }
                }
                strPaper.append("...[<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(intPageCount)) + ">" + Integer.toString(intPageCount) + "</a>]");
            }
        }

        if (intPageIndex < intPageCount){
            if ((intPageIndex + 1) <= intPageStaticMax){
                strPaper.append("<a class=" + this.otherPageCSS + " href=" + pageStaticURLFormat.replace("{0}", Integer.toString((intPageIndex + 1))) + ">下一页</a>");
            }else{
                strPaper.append("<a class=" + this.otherPageCSS + " href=" + pageDynamicURLFormat.replace("{0}", Integer.toString(intPageIndex + 1)) + ">下一页</a>");
            }
        }
        
        if (intPageIndex == intPageCount){
        	strInfoNum = Integer.toString(intPageIndex * intPageSize - intPageSize + 1) + "- " + recordCount;
        }else{
        	strInfoNum = Integer.toString(intPageIndex * intPageSize - intPageSize + 1) + "-" + Integer.toString(intPageIndex * intPageSize);
        }
        

        sb.append("<script language='javascript'>																														\r\n");
		sb.append("	function tagLib_" + this.id + "_btnGoPage(inputPageId)																								\r\n");
		sb.append("	{																																					\r\n");
		sb.append("		var strPage=document.getElementById(inputPageId).value;																							\r\n");
		sb.append("		if(isNaN(strPage) || strPage=='')																																\r\n");
		sb.append("		{																																				\r\n");
		sb.append("			alert('请输入页码！');																														\r\n");
		sb.append("			return false;																																\r\n");
		sb.append("		}																																				\r\n");
		sb.append("		else																																			\r\n");
		sb.append("		{																																				\r\n");
		sb.append("			var ip = parseInt(strPage);																													\r\n");
		sb.append("			if(ip > "+pageCount+" || ip < 1)																											\r\n");
		sb.append("			{																																			\r\n");
		sb.append("				alert('请输入1—"+pageCount+"范围内的页码！');																								\r\n");
		sb.append("				return false;																															\r\n");
		sb.append("			}																																			\r\n");
		sb.append("			else																																		\r\n");
		sb.append("			{																																			\r\n");
		sb.append("				var strURL='';																															\r\n");
		sb.append("				if(ip==1)																																\r\n");
		sb.append("				{																																		\r\n");
		sb.append("			    	strURL='"+pageFirstURL+"';																											\r\n");
		sb.append("				}																																		\r\n");
		sb.append("				else																																	\r\n");
		sb.append("				{																																		\r\n");
		sb.append("					if(ip<"+pageStaticMax+")																											\r\n");
		sb.append("					{																																	\r\n");
		sb.append("						strURL='"+pageStaticURLFormat+"';																								\r\n");
		sb.append("					}																																	\r\n");
		sb.append("					else																																\r\n");
		sb.append("					{																																	\r\n");
		sb.append("						strURL='"+pageDynamicURLFormat+"';																								\r\n");
		sb.append("					}																																	\r\n");
		sb.append("					strURL=strURL.replace('{0}',ip);																									\r\n");
		sb.append("				}																																		\r\n");
		sb.append("				document.location.href=strURL																											\r\n");
		sb.append("			}																																			\r\n");
		sb.append("		}																																				\r\n");
		sb.append("	}																																					\r\n");
		sb.append("	function tagLib_" + this.id + "_tbInputPageOnkeydown(inputPageId){																					\r\n");
		sb.append("		if (event.keyCode == 13){																														\r\n");
		sb.append("			tagLib_" + this.id + "_btnGoPage(inputPageId);																								\r\n");
		sb.append("		}																																				\r\n");
		sb.append("	}																																					\r\n");
		sb.append("</script>																																			\r\n");
    
		
        sb.append("    <table width=\"100%\" border=\"0\" align=\"cente\" cellpadding=\"0\" cellspacing=\"2\" style=\"font-size:14px\">																						\r\n");
        sb.append("        <tr><td height=\"25\" align=\"center\">																																							\r\n");
        sb.append("            "+strPaper.toString()+"																																										\r\n");
        sb.append("        </td></tr>																																														\r\n");
        sb.append("        <tr><td heigyh=\"25\" align=\"center\">																																							\r\n");
        sb.append("            总共<font color=red>"+recordCount+"</font>条记录，																																			\r\n");
        sb.append("            当前显示第"+strInfoNum+"条记录。跳转到																																							\r\n");
        sb.append("            <input id=\"" + this.id + "_tbPager\" type=\"text\" style=\"width:40px\" value=\"\" onkeydown=\"javascript:tagLib_" + this.id + "_tbInputPageOnkeydown('" + this.id + "_tbPager')\" />页		\r\n");
        sb.append("            <input type=\"button\" value=\"确定\" onclick=\"javascript:tagLib_" + this.id + "_btnGoPage('" + this.id + "_tbPager')\" />																	\r\n");
        sb.append("        </td></tr>																																														\r\n");
        sb.append("    </table>																																																\r\n");
        

        return sb.toString();
    }
	
	public int doEndTag()
	{
		try{
			pageContext.getOut().println(creatPaper());
		}catch(Exception e){
			System.err.println("分页标签出错！");
		}
		return EVAL_PAGE;
	}
	
	private int toInt(String str,int intDefault){
		if(str==null)
			return intDefault;
		if(str.trim().length()==0)
			return intDefault;
		try{
			return Integer.parseInt(str.trim());
		}catch(Exception e){
			return intDefault;
		}
	}
}
