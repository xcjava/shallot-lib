package com.gmail.xcjava.base.str;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

public class StringTool {
	
	/**
	 * 生成随机字符串
	 * @param length 随机字符串长度
	 * @return
	 */
     public static String getRandStr(int length){
		String strRanDomNum = "";
		java.util.Random random =new java.util.Random();
		
		for(int i=0;i<length;i++)
		{
			strRanDomNum += String.valueOf(random.nextInt(10));
		}
		return strRanDomNum;
     }
     public static String getRandStr(int length, char[] codeSequence){
    	 
    	 String strRanDomNum = "";
    	 java.util.Random random =new java.util.Random();
    	 
    	 for(int i=0;i<length;i++)
    	 {
 			strRanDomNum += String.valueOf(codeSequence[random.nextInt(codeSequence.length)]); 
    	 }
    	 return strRanDomNum;
     }
     
     /**
      * 格式化数字
      * @param num		需格式化的数字
      * @param length	格式化后的字符串长度,长度不够时前面补0,超出返回原数字的字符串
      * @return
      */
     public static String fromatNum(int num,int length){
    	 String numStr = String.valueOf(num);
    	 int numLength = numStr.length();
    	 if(numStr.length() >= length)
    		 return numStr;
    	 for(int i=0; i < length - numLength;i++)
    		 numStr = "0" + numStr;
    	 return numStr;
     }
     
     public static String fromatNum(String num,int length){
    	 int numLength = num.length();
    	 if(num.length() >= length)
    		 return num;
    	 for(int i=0; i < length - numLength; i++)
    		 num = "0" + num;
    	 return num;
     }
     
     
     public static String mergeList(List list, String tag){
    	 if(list == null || list.size() < 1)
    		 return null;
    	 
    	 String str = "";
    	 for(Object obj : list){
    		 str += tag + obj + tag;
    	 }
    	 return str;
     }
     
     public static List<String> split(String str, String tag){
    	 if(str == null || tag == null)
    		 return null;
    	 
    	 String[] array = str.split("[" + tag +"]{1,2}");
    	 List<String> list = new ArrayList<String>();
    	 for(String item : array){
    		 if(!StringUtils.isEmpty(item)){
    			 list.add(item);
    		 }
    	 }
    	 return list;
     }
     
     public static String addLine(String str, String newLine){
    	 if(newLine == null){
    		 return str;
    	 }
    	 
    	 if(str == null){
    		 return newLine;
    	 }
    	 
    	 if(!str.endsWith("\r\n")){
    		 str += "\r\n";
    	 }
    	 
    	 str += newLine;
    	 return str;
     }
     
     /**
 	 * 清除html标签
 	 * @param strHtmlStream				原文
 	 * @param translatingCharacters		是否转换特殊字符,如&amp;转&等
 	 * @return							原文清除html后的结果
 	 */
     public static String clearHtml(String strHtmlStream, boolean translatingCharacters){   
 		String str = strHtmlStream;
 		String regExHtml = "<[^>]+>"; 
		java.util.regex.Pattern phtml;
		java.util.regex.Matcher mhtml;  
		phtml = Pattern.compile(regExHtml,Pattern.CASE_INSENSITIVE);    
		mhtml = phtml.matcher(strHtmlStream);    
		str = mhtml.replaceAll("");
 		
		if(translatingCharacters){
			str = str.replace("&acute;", 	"´");
			str = str.replace("&copy;", 	"©");
			str = str.replace("&gt;", 		">");
			str = str.replace("&micro;", 	"µ");
			str = str.replace("&reg;", 		"®");
			str = str.replace("&amp;", 		"&");
			str = str.replace("&deg;", 		"°");
			str = str.replace("&iexcl;", 	"¡");
			str = str.replace("&nbsp;", 	"　");
			str = str.replace("&raquo;", 	"»");
			str = str.replace("&brvbar;", 	"¦");
			str = str.replace("&divide;", 	"÷");
			str = str.replace("&iquest;", 	"¿");
			str = str.replace("&not;", 		"¬");
			str = str.replace("&sect;", 	"§");
			str = str.replace("&bull;", 	"•");
			str = str.replace("&frac12;", 	"½");
			str = str.replace("&laquo;", 	"«");
			str = str.replace("&para;", 	"¶");
			str = str.replace("&uml;", 		"¨");
			str = str.replace("&cedil;", 	"¸");
			str = str.replace("&frac14;", 	"¼");
			str = str.replace("&lt;", 		"<");
			str = str.replace("&plusmn;", 	"±");
			str = str.replace("&times;", 	"×");
			str = str.replace("&cent;", 	"¢");
			str = str.replace("&frac34;", 	"¾");
			str = str.replace("&macr;", 	"¯");
			str = str.replace("&quot;", 	"\"");
			str = str.replace("&trade;", 	"™");
			str = str.replace("&euro;", 	"€");
			str = str.replace("&pound;", 	"£");
			str = str.replace("&yen;", 		"¥");
			str = str.replace("&bdquo;", 	"„");
			str = str.replace("&hellip;", 	"…");
			str = str.replace("&middot;", 	"·");
			str = str.replace("&rsaquo;", 	"›");
			str = str.replace("&ordf;", 	"ª");
			str = str.replace("&circ;", 	"ˆ");
			str = str.replace("&ldquo;", 	"“");
			str = str.replace("&mdash;", 	"—");
			str = str.replace("&rsquo;", 	"’");
			str = str.replace("&ordm;", 	"º");
			str = str.replace("&dagger;", 	"†");
			str = str.replace("&lsaquo;", 	"‹");
			str = str.replace("&ndash;", 	"–");
			str = str.replace("&sbquo;", 	"‚");
			str = str.replace("&rdquo;", 	"”");
			str = str.replace("&Dagger;", 	"‡");
			str = str.replace("&lsquo;", 	"‘");
			str = str.replace("&permil;", 	"‰");
			str = str.replace("&shy;", 		"­");
			str = str.replace("&tilde;", 	"˜");
			str = str.replace("&asymp;", 	"≈");
			str = str.replace("&frasl;", 	"⁄");
			str = str.replace("&larr;", 	"←");
			str = str.replace("&part;", 	"∂");
			str = str.replace("&spades;", 	"♠");
			str = str.replace("&cap;", 		"∩");
			str = str.replace("&ge;", 		"≥");
			str = str.replace("&le;", 		"≤");
			str = str.replace("&Prime;", 	"″");
			str = str.replace("&sum;", 		"∑");
			str = str.replace("&clubs;", 	"♣");
			str = str.replace("&harr;", 	"↔");
			str = str.replace("&loz;", 		"◊");
			str = str.replace("&prime;", 	"′");
			str = str.replace("&uarr;", 	"↑");
			str = str.replace("&darr;", 	"↓");
			str = str.replace("&hearts;", 	"♥");
			str = str.replace("&minus;", 	"−");
			str = str.replace("&prod;", 	"∏");
			str = str.replace("&diams;", 	"♦");
			str = str.replace("&infin;", 	"∞");
			str = str.replace("&ne;", 		"≠");
			str = str.replace("&radic;", 	"√");
			str = str.replace("&equiv;", 	"≡");
			str = str.replace("&int;", 		"∫");
			str = str.replace("&oline;", 	"‾");
			str = str.replace("&rarr;", 	"→");
			str = str.replace("&alpha;", 	"α");
			str = str.replace("&eta;", 		"η");
			str = str.replace("&mu;", 		"μ");
			str = str.replace("&pi;", 		"π");
			str = str.replace("&theta;", 	"θ");
			str = str.replace("&beta;", 	"β");
			str = str.replace("&gamma;", 	"γ");
			str = str.replace("&nu;", 		"ν");
			str = str.replace("&psi;", 		"ψ");
			str = str.replace("&upsilon;", 	"υ");
			str = str.replace("&chi;", 		"χ");
			str = str.replace("&iota;", 	"ι");
			str = str.replace("&omega;", 	"ω");
			str = str.replace("&rho;", 		"ρ");
			str = str.replace("&xi;", 		"ξ");
			str = str.replace("&delta;", 	"δ");
			str = str.replace("&kappa;", 	"κ");
			str = str.replace("&omicron;", 	"ο");
			str = str.replace("&sigma;", 	"σ");
			str = str.replace("&zeta;", 	"ζ");
			str = str.replace("&epsilon;", 	"ε");
			str = str.replace("&lambda;", 	"λ");
			str = str.replace("&phi;", 		"φ");
			str = str.replace("&tau;", 		"τ");
		}
 		return  str;
 	}
     
     /**
      * 清除XSS跨站脚本攻击
      * @param value
      * @return
      */
     public static String cleanXss(String value) {   
        /* value = value.toLowerCase().replaceAll("<", "&lt;").replaceAll(">", "&gt;");
         value = value.toLowerCase().replaceAll("(", "&#40;").replaceAll(")", "&#41;");
         value = value.toLowerCase().replaceAll("'", "&#39;");*/
    	 if(StringUtils.isEmpty(value))return value;
    	 value = value.toLowerCase().replaceAll("[<]", "").replaceAll("[>]", "");
         value = value.toLowerCase().replaceAll("[(]", "").replaceAll("[)]", "");
         value = value.toLowerCase().replaceAll("'", "");
         value = value.toLowerCase().replaceAll(";", "");
         value = value.toLowerCase().replaceAll("/", "");
         value = value.toLowerCase().replaceAll("-", "");
         value = value.toLowerCase().replaceAll("script", "");
         value = value.toLowerCase().replaceAll("frame", "");
         return value;
     }
     
     public static void main(String[] args) {
    	 //System.out.println(cleanXss("--'abcd);alert('1111');//001<script>alert('111111');</script>"));
    	 System.out.println(cleanXss("sold';alert(42873);'"));
    	 
    	 List<Long> list = new ArrayList<Long>();
    	 list.add(Long.valueOf("111"));
    	 list.add(Long.valueOf("222"));
    	 list.add(Long.valueOf("333"));
    	 list.add(Long.valueOf("444"));
    	 
    	 System.out.println(mergeList(list, "|"));
    	 System.out.println(split(mergeList(list, "|"), "|").size() + "");
    	 System.out.println(addLine("", "hello"));
     }
}
