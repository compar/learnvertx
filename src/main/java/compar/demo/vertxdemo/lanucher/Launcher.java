package compar.demo.vertxdemo.lanucher;

import java.util.function.Consumer;

import io.vertx.core.Vertx;

public interface Launcher {
	void start(Consumer<Vertx> startConsumer);
}
