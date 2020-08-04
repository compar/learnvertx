package cn.compar.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 *   ByteBuf 的 wirteX和 readX会改变索引 ridx和widx
 *   这是有状态的读写
 * @author hongzy
 *
 */
public class NBufferIndex {
	public static void main(final String[] args) {
        // 初始化
        final ByteBuf buf = Unpooled.buffer(16);

        for (int i = 0; i < 16; i++) {
            buf.writeByte(i);
        }
        final ByteBuf result = buf.readBytes(5);
        System.out.println(result);
        
        			//如果改了buf，会影响结果吗？  并不会，所以readBytes是创建了一个新的ByteBuf
        //	buf.setByte(2, 22);
        
        for (int idx = 0; idx < 5; idx++) {
            System.out.print(result.getByte(idx) + ",");
        }
        System.out.println();
        System.out.println(result);
        System.out.println(buf.readerIndex() + ", " + buf.writerIndex());
    }
}
