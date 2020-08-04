package cn.compar.vertx.buffer;

import io.vertx.core.buffer.Buffer;

/**
 * netty的buf的set操作是不改变widx的。
 * 但是vertx的set方法会进行智能扩容， 它有副作用。
 * @author hongzy
 *
 */
public class VBufferSet {
	public static void main(final String[] args) {
        final Buffer buff = Buffer.buffer();

        buff.appendString("hello\n");
        buff.setString(3, "X");
        System.out.println(buff.toString() + ", " + buff.length());
        buff.setString(8, "X");
        System.out.println(buff.toString() + ", " + buff.length());
    }
}
