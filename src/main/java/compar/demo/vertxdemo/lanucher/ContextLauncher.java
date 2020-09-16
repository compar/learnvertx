package compar.demo.vertxdemo.lanucher;

import io.vertx.core.Context;

public class ContextLauncher {
	public static void main(final String[] args) {
        // 哪种模式，这里使用了非Cluster模式
        final boolean isClustered = false;
        final Launcher launcher = isClustered ? new ClusterLauncher() : new SingleLauncher();
        System.out.println(Thread.currentThread().getName() + ","
                + Thread.currentThread().getId());
        launcher.start(vertx -> {
            // 上下文
            final Context context = vertx.getOrCreateContext();
            System.out.println(context.isEventLoopContext());
            context.runOnContext(v -> {
                System.out.println(Thread.currentThread().getName() + ","
                        + Thread.currentThread().getId()
                        + ", This will be executed async -> " + v);
            });
        });
    }
}
