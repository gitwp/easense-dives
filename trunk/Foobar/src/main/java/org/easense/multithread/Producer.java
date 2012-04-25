package org.easense.multithread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	private static char[] CHAR_ARRAY = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	private static int MAX_CAPACITY = 20;

	private List<String> prodctionHolder;

	private boolean keepRunning = true;

	public Producer(List<String> prodctionHolder) {
		this.prodctionHolder = prodctionHolder;
	}

	@Override
	public void run() {
		while (keepRunning) {
			synchronized (prodctionHolder) {
				if (prodctionHolder != null && prodctionHolder.size() < MAX_CAPACITY) {
					prodctionHolder.add(genString());
				} else {
					try {
						TimeUnit.SECONDS.sleep(2);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	private String genString() {
		Random random = new Random();
		char[] array = new char[random.nextInt(10) + 2];
		for (int i = 0; i < array.length; i++) {
			array[i] = CHAR_ARRAY[random.nextInt(CHAR_ARRAY.length)];
			if (random.nextInt() % 2 == 0) {
				array[i] = Character.toUpperCase(array[i]);
			}
		}
		return new String(array);
	}

	public void stop() {
		this.keepRunning = false;
	}

	public List<String> getProdctionHolder() {
		return prodctionHolder;
	}

	public void setProdctionHolder(List<String> prodctionHolder) {
		this.prodctionHolder = prodctionHolder;
	}

	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		new Thread(new Producer(list)).start();
		new Thread(new Consumer(list)).start();
		new Thread(new Consumer(list)).start();
		new Thread(new Consumer(list)).start();
		new Thread(new Consumer(list)).start();
	}
}
