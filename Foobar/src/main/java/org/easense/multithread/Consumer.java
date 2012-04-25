package org.easense.multithread;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

	private List<String> prodctionHolder;

	private boolean keepRunning = true;

	public Consumer(List<String> prodctionHolder) {
		this.prodctionHolder = prodctionHolder;
	}

	@Override
	public void run() {
		while (keepRunning) {
			synchronized (prodctionHolder) {
				if (prodctionHolder.size() > 0) {
					System.out.println(Thread.currentThread().getName() + " cosuming " + prodctionHolder.remove(0)
							+ ",and current production Line: " + prodctionHolder);
				}
			}
			// rest for a while
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void stop() {
		this.keepRunning = false;
	}

}
