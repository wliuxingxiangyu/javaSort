package classEight;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaxMinDValue {

	public static class WindowElement {
		public int value;
		public int time;

		public WindowElement(int value, int time) {
			this.value = value;
			this.time = time;
		}
	}

	public static int[] getMaxMinDValueWindow(int[] arr, int windowSize) {
		if (arr == null || windowSize == 0 || windowSize > arr.length) {
			return null;
		}
		int[] minWindow = getMinWindow(arr, windowSize);
		int[] maxWindow = getMaxWindow(arr, windowSize);
		int[] result = new int[maxWindow.length];
		for (int i = 0; i != result.length; i++) {
			result[i] = maxWindow[i] - minWindow[i];
		}
		return result;
	}

	public static int[] getMinWindow(int[] arr, int windowSize) {
		int[] result = new int[arr.length - windowSize + 1];
		Deque<WindowElement> maxHold = new LinkedList<WindowElement>();
		for (int i = 0; i != windowSize; i++) {
			pushAtEndForMin(maxHold, arr[i], i);
		}
		result[0] = peek(maxHold);
		for (int i = windowSize; i != arr.length; i++) {
			pushAtEndForMin(maxHold, arr[i], i);
			popAtStart(maxHold, i - windowSize);
			result[i - windowSize + 1] = peek(maxHold);
		}
		return result;
	}

	public static int[] getMaxWindow(int[] arr, int windowSize) {
		int[] result = new int[arr.length - windowSize + 1];
		Deque<WindowElement> maxHold = new LinkedList<WindowElement>();
		for (int i = 0; i != windowSize; i++) {
			pushAtEndForMax(maxHold, arr[i], i);
		}
		result[0] = peek(maxHold);
		for (int i = windowSize; i != arr.length; i++) {
			pushAtEndForMax(maxHold, arr[i], i);
			popAtStart(maxHold, i - windowSize);
			result[i - windowSize + 1] = peek(maxHold);
		}
		return result;
	}

	public static int peek(Deque<WindowElement> window) {
		return window.peekFirst().value;
	}

	public static void popAtStart(Deque<WindowElement> window, int deadline) {
		if (window.peek().time <= deadline) {
			window.pollFirst();
		}
	}

	public static void pushAtEndForMin(Deque<WindowElement> window, int value,
			int time) {
		while (!window.isEmpty() && value < window.peekLast().value) {
			window.pollLast();
		}
		window.offerLast(new WindowElement(value, time));
	}

	public static void pushAtEndForMax(Deque<WindowElement> window, int value,
			int time) {
		while (!window.isEmpty() && value > window.peekLast().value) {
			window.pollLast();
		}
		window.offerLast(new WindowElement(value, time));
	}

	public static void printArray(int[] arr) {
		for (int i = 0; i != arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int[] arr = { 1, 3, -1, -3, 5, 3, 6, 7 };
		printArray(getMaxMinDValueWindow(arr, 3));
	}
}
