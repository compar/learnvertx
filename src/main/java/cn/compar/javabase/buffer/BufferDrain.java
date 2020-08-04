package cn.compar.javabase.buffer;

import java.nio.CharBuffer;

/**
 * 演示读一行数据，消费一行数据。 
 * 用debug模式调试main， 观察buffer的limit和position的变化。
 * 
 * 由此看出jdk的buffer相当坑， 要在读写模式之间切换， 思考读写的position是不是应该分为两个。
 * 甚至是不是读写索引可以独立于BUffer类存在，比如使用 迭代器一类的设计独立出来， 有助于并发访问。 但似乎这么做有违OO
 * @author hongzy
 *
 */
public class BufferDrain {
	
	private static final
    String[] strings = {
            "A random string value",
            "The product of an infinite number of monkeys",
            "Hey hey we're the Monkees",
            "Opening act for the Monkees: Jimi Hendrix",
            "'Scuse me while I kiss this fly'",
            "Help Me! Help Me!"
    };
	
	private static int index = 0;
	
	private static void drainBuffer(final CharBuffer buffer) {
        while (buffer.hasRemaining()) {
            System.out.print(buffer.get());
        }
        System.out.println();
    }

    private static boolean fillBuffer(final CharBuffer buffer) {
        if (index >= strings.length) return false;
        final String string = strings[index++];
        for (int i = 0; i < string.length(); i++)
            buffer.put(string.charAt(i));
        return true;
    }
	public static void main(String[] args) {
		final CharBuffer buffer = CharBuffer.allocate(100);
        while (fillBuffer(buffer)) {
            buffer.flip();
            drainBuffer(buffer);
            buffer.clear();
        }
	}
}
