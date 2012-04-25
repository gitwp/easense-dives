package org.easense.multithread;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class PcThreadTest {

	/**
	 * This test always get green bar since JUnit will end thread
	 * at the end of a test.
	 */
	@Test
	public void testOne() {
		List<String> list = new ArrayList<String>();
		new Thread(new Producer(list)).start();
		new Thread(new Consumer(list)).start();
		new Thread(new Consumer(list)).start();
		new Thread(new Consumer(list)).start();
		new Thread(new Consumer(list)).start();
	}
}
