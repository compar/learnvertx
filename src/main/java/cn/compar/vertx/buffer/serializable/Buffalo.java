package cn.compar.vertx.buffer.serializable;

import java.nio.charset.Charset;

import io.netty.util.internal.StringUtil;
import io.vertx.core.buffer.Buffer;


/**
 * 序列化工具，  把字段写入buffer，第一个字节写入长度，然后再写入数据字段。
 * 读取的时候先读取地一个字节长度，然后再根据长度读取数据
 * @author hongzy
 *
 */
public class Buffalo {
	public static void write(final Buffer buffer, final String... data) {
		// 遍历数据
		for (final String item : data) {
			if (!StringUtil.isNullOrEmpty(item)) {
				// 字节数据
				final byte[] bytes = item.getBytes(Charset.defaultCharset());
				buffer.appendInt(bytes.length);
				buffer.appendBytes(bytes);
			}
		}
	}

	public static int read(final int start, final Buffer buffer, final String[] reference) {
		int pos = start;
		for (int idx = 0; idx < reference.length; idx++) {
			// 先读取长度信息
			final int len = buffer.getInt(pos);
			// 计算偏移量
			pos += 4;
			// 读取本身内容
			final byte[] bytes = buffer.getBytes(pos, pos + len);
			reference[idx] = new String(bytes, Charset.defaultCharset());
			pos += len;
		}
		return pos;
	}
}
