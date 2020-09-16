package compar.demo.vertxdemo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import compar.demo.vertxdemo.lanucher.Launcher;
import compar.demo.vertxdemo.lanucher.SingleLauncher;
import compar.demo.vertxdemo.verticle.LifeVerticle;
import io.vertx.core.DeploymentOptions;

/**
 * 观察生命周期， 当按下Ctrl-C会执行Stop
 * 
 * 另： 在eclipse的console中无法发送Ctrl-c,可以在term中运行如下：
 *  jps 查出pid
 *  kill -s INT <pid>
 *     
 * @author hongzy
 *
 */
public class LifeCycle {
	private static final ConcurrentMap<String, String> IDS = new ConcurrentHashMap<>();

	public static void main(final String[] args) {
		// 选择单点模式
		final Launcher launcher = new SingleLauncher();

		launcher.start(vertx -> {
			// 发布
			vertx.deployVerticle(LifeVerticle::new, new DeploymentOptions().setInstances(10), res -> {
				if (res.succeeded()) {
					IDS.put(res.result(), res.result());
				}
			});
			vertx.deployVerticle(LifeVerticle::new, new DeploymentOptions().setInstances(3), res -> {
				if (res.succeeded()) {
					IDS.put(res.result(), res.result());
				}
			});

			Runtime.getRuntime().addShutdownHook(new Thread(() -> {
				// 撤销
				System.out.println("...");
				IDS.keySet().forEach(item -> vertx.undeploy(item, res -> {
					System.out.println("Successfully undeploy the item: " + item);
				}));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}));
		});
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
}
