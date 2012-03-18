package org.easense.pc;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

	private List<String> productionLine;
	
	public Consumer(List<String> productionLine) {
		this.productionLine = productionLine;
	}
	
	@Override
	public void run() {
		boolean running = true;
		
		while (running) {
			synchronized (productionLine) {
				if (productionLine.size() > 0) {
					System.out.println();
					System.out.print(Thread.currentThread().getName() + " Consuming : " + productionLine.get(0));
					productionLine.remove(0);
					System.out.println(", Current Productions in consumer: " + productionLine);
				} else {
					System.err.println(Thread.currentThread().getName() + " End");
					running = false;
				}
			}
			try {
				TimeUnit.SECONDS.sleep(1);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
