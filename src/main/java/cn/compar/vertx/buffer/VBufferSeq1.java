package cn.compar.vertx.buffer;

import io.vertx.core.buffer.Buffer;

public class VBufferSeq1 {
	 public static void main(final String[] args) {
	        /*
	         * 插入处理
	         */
	        final Buffer buffer = Buffer.buffer();
	        buffer.appendDouble(12.5);
	        buffer.appendString("Hello");
	        buffer.appendInt(33);         //4个字节：  0,0,0,33
	        buffer.appendByte((byte) 8);
	        final float floatValue = 12.2f;
	        buffer.appendFloat(floatValue);
	        buffer.appendIntLE(6);   //小端字节序， 也就是说高位在后，低位在前。  6,0,0,0  
	        buffer.appendString("end");
	        /*
	         * 读取数据
	         */
	        System.out.println(buffer.getDouble(0));
	        System.out.println(buffer.getString(8, 13));
	        System.out.println(buffer.getInt(13));
	        System.out.println(buffer.getByte(17));
	        System.out.println(buffer.getFloat(18));
	        System.out.println(buffer.getFloat(22));
	        System.out.println(buffer.getString(26, 29));
	    }
}
