package cn.compar.vertx.buffer.serializable;

import io.vertx.core.buffer.Buffer;
import io.vertx.core.shareddata.impl.ClusterSerializable;

public class BasicUser implements ClusterSerializable {
	private transient String id;
	private transient String username;
	private transient String password;

	@Override
	public void writeToBuffer(Buffer buffer) {
		/* 写入id，用户名和token **/
		Buffalo.write(buffer, this.id, this.username, this.password);
	}

	@Override
	public int readFromBuffer(int pos, Buffer buffer) {
		/* 读取信息 **/
		final String[] reference = new String[3];
		pos = Buffalo.read(pos, buffer, reference);

		/* 从引用中读取数据 **/
		this.id = reference[0];
		this.username = reference[1];
		this.password = reference[2];
		return pos;
	}
	
	public static void main(String[] args) {
		BasicUser user = new BasicUser("1", "compar", "666666");

		Buffer buffer = Buffer.buffer();
		user.writeToBuffer(buffer);
		
		BasicUser copy = new BasicUser();
		copy.readFromBuffer(0, buffer);
		
		System.out.println(copy);
	}
	
	public BasicUser() {
		
	}

	public BasicUser(String id, String username, String password) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User{" +
                "username='" + this.username + '\'' +
                ", password='" + this.password + '\'' +
                '}';
	}
}
