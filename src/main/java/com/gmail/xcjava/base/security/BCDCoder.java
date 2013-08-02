package com.gmail.xcjava.base.security;

/**
 * BCD编码工具
 * @author xcjava@gmail.com
 *
 */
public class BCDCoder {
	
    public static void main(String[] args) {  
        byte[] b = str2Bcd("12345"); 
        for(int i=0; i<b.length; i++){
        	System.out.println(Byte.valueOf(b[i]).intValue());
        	//System.out.println(Integer.toHexString(Byte.valueOf(b[i]).intValue()));
        }
        System.out.println(bcd2Str(b, false));
        
        byte[] a = {0x01,0x23,0x45};
        for(int i=0; i<b.length; i++){
        	System.out.println(a[i]);
        }
        System.out.println(bcd2Str(a, false));
    }  
  
    /**
     * BCD码字节转为十进制字符串(阿拉伯数字) 
     * @param bytes			BCD码字节转
     * @param keepFormat	是否保留原行(前面是否保存高位的0)
     * @return				十进制字符串
     */
    public static String bcd2Str(byte[] bytes, boolean keepFormat) {  
        StringBuffer temp = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            temp.append((byte) ((bytes[i] & 0xf0) >>> 4));  
            temp.append((byte) (bytes[i] & 0x0f));  
        }  
        
        if(!keepFormat){
        	return temp.toString().substring(0, 1).equalsIgnoreCase("0") ? temp  
                .toString().substring(1) : temp.toString(); 
        }
        return temp.toString();
    }
    
    public static String bcd2Str(byte[] bytes){
    	return bcd2Str(bytes, false);
    }
  
    public static byte[] str2Bcd(String asc){
    	return str2Bcd(asc, 0);
    }
    
    /**
     * 10进制串转为BCD码字节 
     * @param asc		十进制字符串
     * @param length	BCD码字节转
     * @return
     */
    public static byte[] str2Bcd(String asc, int length) {
        int mod = asc.length() % 2;  
        if (mod != 0) {  
            asc = "0" + asc;
        }
        int len = asc.length() / 2; 
        
        if(len < length){
        	for(int i = 0; i < length - len; i++){
        		 asc = "00" + asc;
        	}
        	len = length;
        }
        
        byte abt[] = new byte[len];  
        /*if (len >= 2) {  
            len = len / 2;  
        }  */
        byte bbt[] = new byte[len];  
        abt = asc.getBytes();  
        int j, k;  
        for (int p = 0; p < asc.length() / 2; p++) {  
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {  
                j = abt[2 * p] - '0';  
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {  
                j = abt[2 * p] - 'a' + 0x0a;  
            } else {  
                j = abt[2 * p] - 'A' + 0x0a;  
            }  
            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {  
                k = abt[2 * p + 1] - '0';  
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {  
                k = abt[2 * p + 1] - 'a' + 0x0a;  
            } else {  
                k = abt[2 * p + 1] - 'A' + 0x0a;  
            }  
            int a = (j << 4) + k;  
            byte b = (byte) a;  
            bbt[p] = b;  
        }  
        return bbt;  
    }  
}
