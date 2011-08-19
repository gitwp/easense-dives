package org.easense;

import java.util.ArrayList;
import java.util.List;

public class Client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> productionLine = new ArrayList<String>();
		
		new Thread(new Producer(productionLine), "Producer").start();
		for (int i = 0; i < 9; i++) {
			new Thread(new Consumer(productionLine), "Consumer " + i).start();
		}
		
	}

}
