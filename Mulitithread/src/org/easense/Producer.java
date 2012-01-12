package org.easense;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Producer implements Runnable {

	/**
	 * Store the produced productions;
	 */
	private List<String> productionLine;

	/**
	 * The maximum products limit
	 */
	private static int MAX_SIZE = 10;

	private static char[] CHAR_ARRAY = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
			'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };

	public Producer(List<String> productionLine) {
		this.productionLine = productionLine;
	}

	@Override
	public void run() {
		if (productionLine != null) {
			Random random = new Random();
			int arrayLength = CHAR_ARRAY.length;

			while (true) {
				synchronized (productionLine) {
					if (productionLine.size() < MAX_SIZE) {
						StringBuffer temp = new StringBuffer();
						for (int index = 0; index < 5; index++) {
							temp.append(CHAR_ARRAY[random.nextInt(arrayLength)]);
						}
						System.out.print(Thread.currentThread().getName() + " Producing : " + temp.toString());
						productionLine.add(temp.toString());
						System.out.println(", Current Productions in producer: " + productionLine);
					}
				}

				try {
					TimeUnit.MILLISECONDS.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
