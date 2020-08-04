package cn.compar.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * discardReadBytes方法如同NIO的buffer的compact方法
 *    ridx到widx区域被copy前移了，  widx到cap之间又有了7个字节可以被写入
 * @author hongzy
 *
 */
public class NBufferDiscard {
	 public static void main(final String[] args) {
	        // 初始化
	        final ByteBuf buf = Unpooled.buffer(16);

	        for (int i = 0; i < 16; i++) {
	            buf.writeByte(i);
	        }
	        final ByteBuf sub = buf.readBytes(7);
	        System.out.println("Buffer, " + buf);   //sub != buf ，这里创建了一个新的
	        final ByteBuf created = buf.discardReadBytes();  //buf == created ，是原始buf
	        System.out.println("Buffer, " + buf);
	        System.out.println("Sub, " + sub);
	        System.out.println("Discard, " + created);
	    }
}
