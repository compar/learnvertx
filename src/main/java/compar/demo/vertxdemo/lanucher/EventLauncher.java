package compar.demo.vertxdemo.lanucher;

import io.vertx.core.DeploymentOptions;


public class EventLauncher {
    public static void main(final String[] args) {
        // 哪种模式？
        final boolean isClustered = false;
        final Launcher launcher = isClustered ? new ClusterLauncher() :
                new SingleLauncher();
        System.out.println(Thread.currentThread().getName() + ","
                + Thread.currentThread().getId());
        launcher.start(vertx -> {
            // 发布Standard
            vertx.deployVerticle("compar.demo.vertxdemo.verticles.AcceptorVerticle",
                    new DeploymentOptions().setInstances(4));
            // 发布Worker
            vertx.deployVerticle("compar.demo.vertxdemo.verticles.WorkerVerticle",
                    new DeploymentOptions().setWorker(true).setInstances(16));
        });
    }
}
