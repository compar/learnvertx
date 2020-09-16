package compar.demo.vertxdemo;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import io.vertx.core.Vertx;

public class VertxStart {
	
    private static final ConcurrentMap<String, String> IDS = new ConcurrentHashMap<>();
    
	public static void main(String[] args) {
		   Vertx vert = Vertx.vertx();
		   vert.deployVerticle(new RestVerticle());


	}

}
