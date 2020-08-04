package cn.compar.javabase.buffer;

import java.nio.ByteBuffer;

/**
 * mark方法可以记住position位置， 调用reset可以回到mark位置
 * @author hongzy
 *  
 */
public class BufferMark {
	public static void main(final String[] args) {
        final ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 'H').put((byte) 'e').put((byte) 'l').put((byte) 'l').put((byte) 'o');
        buffer.put(0, (byte) 'M').put((byte) 'w');
        // 主要关注这一行的调用
        buffer.position(2).mark().position(4);
        
        //调用reset后，position回到mark的位置，也就是2
        buffer.reset();
    }
}
