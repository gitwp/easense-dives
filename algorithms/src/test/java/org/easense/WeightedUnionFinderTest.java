package org.easense;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.easense.chapterone.WeightedUnionFinder;
import org.junit.Ignore;
import org.junit.Test;

import edu.princeton.StdRandom;

public class WeightedUnionFinderTest {

	private static final String TINY_FILE = "tinyUF.txt";
	private static final String MEDIUM_FILE = "mediumUF.txt";

	@Test
	@Ignore
	public void tinyFileSearch() throws FileNotFoundException {
		searchIn(TINY_FILE);
	}

	@Test
//	@Ignore
	public void mediumFileSearch() throws FileNotFoundException {
		searchIn(MEDIUM_FILE);
	}

	@Test
	@Ignore
	public void largeAmountSearch() throws FileNotFoundException {
		int size = 1000000;
		WeightedUnionFinder finder = new WeightedUnionFinder(size);

		for (int i = 0; i < size * 1.85; i++) {
			int p = StdRandom.uniform(size);
			int q = StdRandom.uniform(size);

			if (finder.connected(p, q)) {
				continue;
			}

			finder.union(p, q);
			System.out.println(p + ", " + q);
		}

		System.out.println("Total: " + finder.componentSize()
				+ " components\n===============================================\n");
	}

	private void searchIn(String path) throws FileNotFoundException {
		WeightedUnionFinder finder;
		String filePath = this.getClass().getClassLoader().getResource("").getPath() + "data/" + path;
		Scanner scanner = new Scanner(new FileInputStream(filePath));

		finder = new WeightedUnionFinder(scanner.nextInt());

		while (scanner.hasNext()) {
			int pId = scanner.nextInt();
			int qId = scanner.nextInt();

			if (finder.connected(pId, qId)) {
				continue;
			}

			finder.union(pId, qId);
			System.out.println(pId + ", " + qId);
		}

		System.out.println("in file[" + path + "] Total: " + finder.componentSize()
				+ " components\n===============================================\n");
	}
}
