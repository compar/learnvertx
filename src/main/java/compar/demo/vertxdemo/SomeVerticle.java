package compar.demo.vertxdemo;

import io.vertx.core.AbstractVerticle;

public class SomeVerticle extends AbstractVerticle{
	int i=0;
	@Override
	public void start() throws Exception {
		vertx.setPeriodic(1000, e->{
		      i++;
		      	System.out.println(Thread.currentThread().getName()+":"+i);			      
		    });

	}
}
