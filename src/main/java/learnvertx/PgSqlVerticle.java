package learnvertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.pgclient.PgConnectOptions;
import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.PoolOptions;

public class PgSqlVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		
		PgConnectOptions connectOptions = new PgConnectOptions()
				.setHost("127.0.0.1")
				.setPort(5432)
				.setDatabase("test")
				.setUser("hongzy")
				.setPassword("dragon");
				
		PoolOptions poolOptions = new PoolOptions().setMaxSize(5);
		
		PgPool client = PgPool.pool(vertx,connectOptions,poolOptions);
		

		
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create()); // 可以处理Body

		
		router.route("/test/db").handler(req -> {
			
			JsonObject page = req.getBodyAsJson();

			client.preparedQuery("SELECT id,name from t_test order by name")
			.execute(ar->{
				if(ar.succeeded()) {
					JsonArray  ja = new JsonArray();
					ar.result().forEach(row->{				
						JsonObject jo = new JsonObject();
					
						ja.add(jo);
						jo.put("id",row.getInteger("id"));
						jo.put("name",row.getString("name"));
						
					});
					req.response().putHeader("content-type", "text/plain").end(ja.toString());
				}else {
					req.response().putHeader("content-type", "text/plain").end(ar.cause().getMessage());
				}
			});
			
		});
		
		vertx.createHttpServer().requestHandler(router).listen(8888, http -> {
		      if (http.succeeded()) {
		        startPromise.complete();
		        System.out.println("HTTP server started on port 8888");
		      } else {
		        startPromise.fail(http.cause());
		      }
	    });
	}
}
