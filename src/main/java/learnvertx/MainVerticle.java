package learnvertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

	@Override
	public void start(Promise<Void> startPromise) throws Exception {
		vertx.createHttpServer().requestHandler(req -> {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.response().putHeader("content-type", "text/plain").end("Hello from Vert.x!");
		}).listen(8888, http -> {
			if (http.succeeded()) {
				startPromise.complete();
				System.out.println("cpu数量:" + Runtime.getRuntime().availableProcessors());
				System.out.println("freeMemory:" + Runtime.getRuntime().freeMemory());
				System.out.println("HTTP server started on port 8888");
			} else {
				startPromise.fail(http.cause());
			}
		});
	}
}
