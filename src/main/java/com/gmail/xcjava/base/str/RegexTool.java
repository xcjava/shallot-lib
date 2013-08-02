package com.gmail.xcjava.base.str;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * java正则表达式工具
 *
 * @author xcjava@gmail.com
 * @version 1.0
 */
public class RegexTool {

	/**
	 * 匹配手机
	 * 移动：134、135、136、137、138、139、150、151、157、158、159、182、187、188、147
	 * 联通：130、131、132、152、155、156、185、186 
	 * 电信：133、153、180、189
	 */
	public static final String REGEX_MOBILE = "^((13[0-9])|(15[^4,\\D])|(18[0-9])|(147))\\d{8}$";
	/**
	 * 匹配用户id
	 * 英文字母开头,内容包括大小写英文,数字和下划线,长度6-20
	 */
	public static final String REGEX_USERID = "[a-zA-Z][a-zA-Z_0-9]{5,19}";
	/**
	 * 匹配电子邮件
	 */
	public static final String REGEX_EMAIL = "(?:\\w[-._\\w]*\\w@\\w[-._\\w]*\\w\\.\\w{2,3}$)";
	
	/**
	 * 是否符合正则匹配
	 * @param source	源字符串
	 * @param regexp	正则表达式
	 * @return
	 */
	public static boolean isRegexValidate(String source, String regexp){
		Pattern p = Pattern.compile(regexp);
        Matcher m = p.matcher(source);
        return m.matches();
	}
}
