package org.easense.chaptertwo;

import java.lang.reflect.Array;


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
				for (int j = i + 1; j < length; j++) {
					if (array[i].compareTo(array[j]) > 0 == ascend) {
						T temp = array[j];
						array[j] = array[i];
						array[i] = temp;
					}
				}
			}
		}
	}),

	/**
	 * Shell Sorting
	 */
	SHELL(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			int length = array.length;
			int gap = 1;

			// use the most next to length / 3 as the first gap
			while (gap < length / 3) {
				gap = gap * 3 + 1;
			}

			while (gap >= 1) {
				for (int i = gap; i < length; i++) {
					T next = array[i];
					int j = i;
					while (j >= gap && (array[j - gap].compareTo(next) > 0 == ascend)) {
						array[j] = array[j - gap];
						j -= gap;
					}
					if (j != i) {
						array[j] = next;
					}
				}
				gap /= 3;
			}

		}
	}),
	
	/**
	 * Merge sorting
	 */
	MERGE(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			this.sort(array, 0, array.length - 1, ascend);
		}
		

		private <T extends Comparable<T>> void sort(T[] array, int lo, int hi, boolean ascend) {
			// OPTIMIZE ONE
			// if the substring's length is less than 20,
			// use insertion sort to reduce recursive invocation
			if (hi - lo < 20) {
				for (int i = lo + 1; i <= hi; i++) {
					T toInsert = array[i];
					int j = i;
					for (; j > lo && (array[j - 1].compareTo(toInsert) > 0 == ascend); j--) {
						array[j] = array[j - 1];
					}

					array[j] = toInsert;
				}
				
				return;
			}
			
			int mid = lo + (hi - lo) / 2;
			sort(array, lo, mid, ascend);
			sort(array, mid + 1, hi, ascend);
			merge(array, lo, mid, hi, ascend);
		}
		
		private <T extends Comparable<T>> void merge(T[] array, int lo, int mid, int hi, boolean ascend) {
			// OPTIMIZE TWO
			// if it is already in right order, skip this merge
			// since there's no need to do so
			if (array[mid].compareTo(array[mid + 1]) < 0 == ascend) {
				return;
			}
			
			@SuppressWarnings("unchecked")
			T[] arrayCopy = (T[]) Array.newInstance(array.getClass().getComponentType(), hi - lo +  1);
			System.arraycopy(array, lo, arrayCopy, 0, arrayCopy.length);
			
			int lowIdx = 0;
			int highIdx = mid - lo + 1;
			
			for (int i = lo; i <= hi; i++) {
				if (lowIdx > mid - lo) { 
					// left sub array exhausted
					array[i] = arrayCopy[highIdx++];
				} else if (highIdx > hi - lo) { 
					// right sub array exhausted
					array[i] = arrayCopy[lowIdx++];
				} else if (arrayCopy[lowIdx].compareTo(arrayCopy[highIdx]) < 0 == ascend) {
					array[i] = arrayCopy[lowIdx++];
				} else {
					array[i] = arrayCopy[highIdx++];
				}
			}
		}
	}),
	
	/**
	 * Quick Sorting
	 */
	QUICK(new Sortable() {
		public <T extends Comparable<T>> void sort(T[] array, boolean ascend) {
			this.sort(array, 0, array.length - 1, ascend);
		}
		

		private <T extends Comparable<T>> void sort(T[] array, int lo, int hi, boolean ascend) {
			if (lo >= hi) {
				return;
			}
			
			int partitionIdx = partition(array, lo, hi, ascend);
			
			// partially sort left array and right array
			// no need to include the partitionIdx-th element
			// since it is already in its final position
			sort(array, lo, partitionIdx - 1, ascend);
			sort(array, partitionIdx + 1, hi, ascend);
		}
		
		private <T extends Comparable<T>> int partition(T[] array, int lo, int hi, boolean ascend) {
			int leftIdx = lo;
			int rightIdx = hi + 1;
			
			T toFinal = array[lo];
			
			while (true) {
				// search from left to right to locate the element placed 
				// in the wrong position which should be in the right
				while (array[++leftIdx].compareTo(toFinal) < 0 == ascend) {
					if (leftIdx >= hi) {
						break;
					}
				}
				
				// search from right to left to locate the element placed 
				// in the wrong position which should be in the left
				while (array[--rightIdx].compareTo(toFinal) > 0 == ascend) {
					if (rightIdx <= lo) {
						break;
					}
				}
				
				if (leftIdx >= rightIdx) {
					break;
				} else {
					exchange(array, leftIdx, rightIdx);
				}
			}
			
			exchange(array, lo, rightIdx);
			
			return rightIdx;
		}
		
		private <T extends Comparable<T>> void exchange(T[] array, int i, int j) {
			T temp = array[i];
			array[i] = array[j];
			array[j] = temp;
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
		if (array == null || array.length <= 2) {
			return;
		}
		sortAlgo.sort(array, ascend);
	}

}
