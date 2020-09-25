package compar.demo.vertxdemo;

import io.netty.util.internal.StringUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;

public class RestVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		System.out.println("RestVerticle start...");
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		
		router.get("/get/:param1/:param2,:param3").handler(this::handleGet);
		router.route("/assets/*").handler(StaticHandler.create("assets"));
		
		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
		System.out.println("RestVerticle started");
	}

	private void handleGet(RoutingContext context) {

		String param1 = context.request().getParam("param1");
		String param2 = context.request().getParam("param2");
		String param3 = context.request().getParam("param3");
		
		if(StringUtil.isNullOrEmpty(param1) || StringUtil.isNullOrEmpty(param2) ) {
			context.response().setStatusCode(400).end();
		}
		JsonObject obj = new JsonObject();
		obj.put("method", "get")
			.put("param1", param1)
			.put("param2", param2)
		.put("param3", param3);
		context.response().putHeader("content-type", "application/json").end(obj.encodePrettily());;
		
	}
	@Override
	public void stop() throws Exception {
		System.out.println("RestVerticle stop!");		
	}
}
