package compar.demo.vertxdemo;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * 测试vertx的多线程
 * @author hongzy
 *
 */
public class VertxThread {
	public static void main(String[] args) {
		   Vertx vert = Vertx.vertx();
		   vert.deployVerticle("compar.demo.vertxdemo.SomeVerticle",new  DeploymentOptions().setInstances(20));
	}

}
