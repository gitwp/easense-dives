package org.easense;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.easense.chapterone.WeightedUnionFinder;
import org.junit.Ignore;
import org.junit.Test;

public class WeightedUnionFinderTest {

	private static final String TINY_FILE = "tinyUF.txt";
	private static final String MEDIUM_FILE = "mediumUF.txt";
	private static final String LARGE_FILE = "largeUF.txt";

	@Test
	@Ignore
	public void tinyFileSearch() throws FileNotFoundException {
		searchIn(TINY_FILE);
	}

	@Test
	@Ignore
	public void mediumFileSearch() throws FileNotFoundException {
		searchIn(MEDIUM_FILE);
	}

	@Test
//	@Ignore
	public void largeFileSearch() throws FileNotFoundException {
		searchIn(LARGE_FILE);
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
