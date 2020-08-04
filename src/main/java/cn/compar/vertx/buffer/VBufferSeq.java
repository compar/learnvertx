package cn.compar.vertx.buffer;

import io.vertx.core.buffer.Buffer;

/**
 * 使用get方法访问buffer需要对java的类型大小十分了解	
 * @author hongzy
 *
 */
public class VBufferSeq {
	public static void main(final String[] args) {
        /*
         * 插入处理
         */
        final Buffer buffer = Buffer.buffer();
        buffer.appendDouble(12.5);
        buffer.appendString("Hello");
        buffer.appendInt(33);
        /*
         * 读取数据
         */
        System.out.println(buffer.getDouble(0));  //double占8个byte
        System.out.println(buffer.getString(8, 13));  //hello占5个byte
        System.out.println(buffer.getInt(13)); //int  占4个byte
    }
}
