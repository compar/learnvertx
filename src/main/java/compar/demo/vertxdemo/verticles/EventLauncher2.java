package compar.demo.vertxdemo.verticles;

import compar.demo.vertxdemo.lanucher.ClusterLauncher;
import compar.demo.vertxdemo.lanucher.Launcher;
import compar.demo.vertxdemo.lanucher.SingleLauncher;
import io.vertx.core.DeploymentOptions;


public class EventLauncher2 {
    public static void main(final String[] args) {
        // 哪种模式？
        final boolean isClustered = true;
        final Launcher launcher = isClustered ? new ClusterLauncher() :
                new SingleLauncher();
        System.out.println(Thread.currentThread().getName() + ","
                + Thread.currentThread().getId());
        launcher.start(vertx -> {
//            // 发布Standard
//            vertx.deployVerticle("compar.demo.vertxdemo.verticles.AcceptorVerticle",
//                    new DeploymentOptions().setInstances(4));
            // 发布Worker
            vertx.deployVerticle("compar.demo.vertxdemo.verticles.WorkerVerticle",
                    new DeploymentOptions().setWorker(true).setInstances(16));
        });
    }
}
