package org.easense.chapterone;


public class WeightedUnionFinder {

	private int[] ids;

	private int[] treeSize;

	/**
	 * component size
	 */
	private int count;

	public WeightedUnionFinder(int nodeSize) {
		ids = new int[nodeSize];
		treeSize = new int[nodeSize];
		count = nodeSize;
		for (int i = 0; i < nodeSize; i++) {
			ids[i] = i;
			treeSize[i] = 1;
		}
	}

	public int find(int nodeId) {
		while (nodeId != ids[nodeId]) {
			nodeId = ids[nodeId];
		}

		return nodeId;
	}

	public void union(int pId, int qId) {
		int pRoot = find(pId);
		int qRoot = find(qId);

		if (pRoot == qRoot) {
			return;
		}
		
		if (treeSize[pRoot] > treeSize[qRoot]) {
			ids[qRoot] = pRoot;
			treeSize[pRoot] += treeSize[qRoot];
		} else {
			ids[pRoot] = qRoot;
			treeSize[qRoot] += treeSize[pRoot];
		}
		count--;
	}

	public boolean connected(int pId, int qId) {
		return find(pId) == find(qId);
	}

	public int componentSize() {
		return this.count;
	}

}
