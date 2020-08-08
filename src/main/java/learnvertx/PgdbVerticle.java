package learnvertx;

import java.time.LocalDate;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class PgdbVerticle extends AbstractVerticle {
	@Override
	public void start(Promise<Void> startPromise) throws Exception {
			PgConnectOptions connectOptions = new PgConnectOptions()
					.setHost("127.0.0.1")
					.setPort(5432)
					.setDatabase("test")
					.setUser("hongzy")
					.setPassword("dragon");
			PoolOptions poolOptions = new PoolOptions()
					.setMaxSize(5);
			
			PgPool pgClient = PgPool.pool(vertx,connectOptions, poolOptions);
			
			pgClient.preparedQuery("SELECT id,name,birthday FROM t_people").execute(ar->{
				if(ar.succeeded()) {
					ar.result().forEach(row->{
						Integer id = row.getInteger("id");
						String name = row.getString("name");
						LocalDate birthday = row.getLocalDate("birthday");
						System.out.println("id="+id+",name="+name+",birthday="+birthday);
						
					});
				}else {
					ar.cause().printStackTrace();
					System.out.println("失败："+ar.cause().getMessage());
				}
				
			});
			

	}
}
