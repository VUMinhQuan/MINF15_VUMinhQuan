package heapsort;

import java.util.Collections;
import java.util.List;

public class HeapSort {
	// Write methode head sort
	public static void Heapify(List<EmailClass> myListEmail, int n, int vt) {
		while (vt <= n / 2 - 1) {
			int child1 = 2 * vt + 1;
			int child2 = 2 * vt + 2;
			int lc = child1;
			if (child2 < n
					&& !Compare(myListEmail.get(child2), myListEmail.get(lc))) {
				lc = child2;
			}
			if (Compare(myListEmail.get(vt), myListEmail.get(lc)))
				Collections.swap(myListEmail, vt, lc);
			vt = lc;
		}
	}

	public static void BuildHeap(List<EmailClass> myListEmail, int n) {
		for (int i = n / 2 - 1; i >= 0; i--) {
			Heapify(myListEmail, n, i);
		}
	}

	public static boolean Compare(EmailClass email01, EmailClass email02) {
		if (email01.getColor() == email02.getColor()) {
			if (email01.getTimeSpan().compareTo(email02.getTimeSpan()) >= 0) {
				return true;
			} else {
				return false;
			}
		} else if (email01.getColor() <= email02.getColor())
			return true;
		else
			return false;
	}

	public static void Sort(List<EmailClass> myListEmail, int n) {
		BuildHeap(myListEmail, n);
		for (int i = n - 1; i >= 0; i--) {
			Collections.swap(myListEmail, 0, i);
			Heapify(myListEmail, i, 0);
		}
	}
}
