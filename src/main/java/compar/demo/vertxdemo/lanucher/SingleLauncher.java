package compar.demo.vertxdemo.lanucher;

import java.util.function.Consumer;

import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class SingleLauncher implements Launcher {

	@Override
	public void start(Consumer<Vertx> startConsumer) {
		final VertxOptions options = new VertxOptions();
		final Vertx vertx = Vertx.vertx(options);
		if (null != vertx) {
			startConsumer.accept(vertx);
		}
	}
}
	
