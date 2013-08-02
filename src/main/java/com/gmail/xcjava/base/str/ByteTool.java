package com.gmail.xcjava.base.str;

/**
 * 字节转换工具
 * @author xcjava@gmail.com
 *
 */
public class ByteTool {

	/**
	 * 字节数组反转,高地位位置互换
	 * @param b
	 * @return
	 */
	public static byte[] reverse(byte b[]){
		byte[] result = new byte[b.length];
		for(int i = 0; i < b.length; i++){
			result[b.length - 1 - i] = b[i];
		}
		return result;
	}
	
	/**
	 * 将String转换成固定长度的byte数组
	 * @param src
	 * @param len
	 * @return
	 */
	public static byte[] getBytes(String src, int len)
	{
		byte[] result = new byte[len];
		byte[] temp = src.getBytes();
		System.arraycopy(temp, 0, result, 0, temp.length);
		return result;
	}
	
	/**
	 * 将String数组转换成固定长度的byte数组
	 * @param src
	 * @param len
	 * @return
	 */
	public static byte[] getBytes(String[] src,int len){
		byte[] result = new byte[len * src.length];
		for(int i = 0; i < src.length; i++){
			try{
				byte[] temp = src[i].getBytes("UTF-8");
				System.arraycopy(temp, 0, result, i * len, temp.length);
			}catch(Exception  e){
				System.out.println("将String数组转换成固定长度的byte数组错误:"+e);
			}
		}
		return result;
	}
	
	/**
	 * byte[] 转换成整型
	 * @param bytes
	 * @return
	 */	
	public static int byte2Int(byte b[]){
		if(b.length==4){
			int mask=0xff;
			int temp=0;
			int res=0;
			for(int i=0;i<4;i++){
				res<<=8;
				temp=b[i]&mask;
				res|=temp;
			}
			return res;
		}
		else if(b.length < 4)
		{
			int i1, i2, i3;
			try{
				i1 = (int)(b[0]); if (i1<0) i1=(int)(256+i1);
			}catch(Exception e){
				i1 = 0;
			}
			try{
				i2 = (int)(b[1]); if (i2<0) i2=(int)(256+i2);
			}catch(Exception e){
				i2 = 0;
			}
			try{
				i3 = (int)(b[2]); if (i3<0) i3=(int)(256+i3);
			}catch(Exception e){
				i3 = 0;
			}
			return (int)(65536*i3 + 256*i2 + i1);
		}
		else{
			return 0;
		}
	}
	
	/**
	 * 将指定长度的byte数组转换成整型
	 * @param b
	 * @param start
	 * @param len
	 * @return
	 */
	public static int byte2Int(byte[] b, int start, int len)
	{
		byte[] dat = new byte[len];
		System.arraycopy(b, start, dat, 0, len);
		return byte2Int(dat);
	}
	
	/**
	 * 整型转换成byte[]
	 * 高字节在前,低字节在后
	 * @param i
	 * @return
	 */
	public static byte[] int2Byte(int i)
	{
		byte abyte0[] = new byte[4];
        abyte0[3] = (byte)i;
        i >>>= 8;
        abyte0[2] = (byte)i;
        i >>>= 8;
        abyte0[1] = (byte)i;
        i >>>= 8;
        abyte0[0] = (byte)i;
        return abyte0;
	}
	
	/**
	 * 将int转为低字节在前，高字节在后的byte数组
	 * @param n
	 * @return
	 
	private static byte[] int2ByteArray(int n) {
        byte[] b = new byte[4];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        b[2] = (byte) (n >> 16 & 0xff);
        b[3] = (byte) (n >> 24 & 0xff);
        return b;
	}
	private static int byteArray2Int(byte[] b) {
        int iOutcome = 0;
        byte bLoop;
        for (int i = 0; i < 4; i++) {
                bLoop = b[i];
                iOutcome += (bLoop & 0xff) << (8 * i);
        }
        return iOutcome;
	}
	*/
	
	/*public static void main(String[] args) {  
		
		byte[] a = int2Byte(1234);
		byte[] a1 = int2Byte(1234);
		byte[] b = int2ByteArray(1234);
		
		for(int i = 0; i < a.length; i++){
			System.out.println((a[i] == a1[i]) + "----" + (a[i] == b[a.length-1-i]));
		}

		System.out.println(byteArray2Int(a));
		System.out.println(byteArray2Int(a1));
		System.out.println(byteArray2Int(b));
	}*/
	
	/**
	 * 将指定长度的整型转换成byte数组
	 * @param i
	 * @param len
	 * @return
	 */
	public static byte[] int2Byte(int i, int len)
	{
		byte abyte0[] = new byte[len];
        for(int j = 0;j < len;j++)
        {
          abyte0[len - j - 1] = (byte) i;
          i >>>= 8;
        }
        return abyte0;
	}
	
	
	/**
	 * 返回一个新的byte数组，索引从intStart开始，到intStart+length
	 * @param bytes
	 * @param intStart
	 * @param length
	 * @return
	 */
	public static byte[] subByte(byte[] bytes,int intStart,int length){
		byte[] result = new byte[length];
		for(int i=0;i<length;i++)
			result[i] = bytes[intStart+i];
		return result;
	}
	
}
