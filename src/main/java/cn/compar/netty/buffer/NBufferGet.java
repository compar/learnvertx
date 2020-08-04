package cn.compar.netty.buffer;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * ByteBuf 的  getX和setX方法不会修改索引，ridx和widx一直都是0
 * 可以理解为无状态读写
 * @author hongzy
 *
 */
public class NBufferGet {
    public static void main(final String[] args) {
        // 初始化
        final ByteBuf buf = Unpooled.buffer(16);

        for (int i = 0; i < 16; i++) {
            buf.setByte(i, i);
        }

        System.out.print(buf.getByte(5) + ", ");
        System.out.println(buf.readerIndex() + ", " + buf.writerIndex());
        // 输出： 5, 0, 0
    }
}
