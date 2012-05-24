package org.easense.chaptertwo;

public enum SortType {
	/**
	 * Selection Sorting
	 */
	SELECTION(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int len = array.length;
			for (int i = 0; i < len; i++) {
				int selected = i;
				for (int j = i + 1; j < len; j++) {
					if (array[j].compareTo(array[selected]) < 0 == ascend) {
						selected = j;
					}
				}

				if (i != selected) {
					T temp = array[i];
					array[i] = array[selected];
					array[selected] = temp;
				}
			}
		}
	}),
	
	/**
	 * Insertion Sorting
	 */
	INSERTION(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int len = array.length;
			for (int i = 1; i < len; i++) {
				T toInsert = array[i];
				int j = i;
				for (; j > 0 && (array[j - 1].compareTo(toInsert) > 0 == ascend); j--) {
					array[j] = array[j - 1];
				}
				
				array[j] = toInsert;
			}
		}
	}),
	
	/**
	 * Bubble Sorting, it's very similar with Insertion Sorting
	 */
	BUBBLE(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int length = array.length;
			for (int i = 0; i < length; i++) {
				for (int j = i + 1; j < length - 1; j++) {
					if (array[i].compareTo(array[j]) > 0 == ascend) {
						T temp = array[j];
						array[j] = array[i];
						array[i] = temp;
					}
				}
			}
		}
	})
	
	;

	private SortType(Sortable sortAlgo) {
		this.sortAlgo = sortAlgo;
	}

	private Sortable sortAlgo;

	public <T extends Comparable<T>> void sort(T[] array) {
		sortAlgo.sort(array, true);
	}

	public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
		if (array == null || array.length < 2) {
			return;
		}
		sortAlgo.sort(array, ascend);
	}

}
