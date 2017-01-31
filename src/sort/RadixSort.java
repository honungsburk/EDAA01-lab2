package sort;
import queue.FifoQueue;


public class RadixSort {
	public static void radixSort(int[] a, int maxNbrOfDigits) {
		FifoQueue<Integer> numbers = new FifoQueue<Integer>();
		FifoQueue<Integer>[] queues = 
			(FifoQueue<Integer>[]) new FifoQueue[10];
		for (int i: a) {
			numbers.add(i);
		}

		for (int i = 0; i < 10; i++) {
			queues[i] = new FifoQueue<Integer>();
		}
		// sort ...
		for(int i = 0; i<maxNbrOfDigits; i++){

			while(numbers.size() > 0) {
				int diget = getDiget(numbers.peek(), i);
				queues[diget].offer(numbers.poll());
			}
			for (FifoQueue<Integer> q: queues) {
				numbers.append(q);
			}

		}

		// a = (int[]) numbers.toArray(new int[numbers.size()]);

		for(int i = 0; i<a.length; i++){
			a[i] = numbers.poll();
		}
	}

	private static int getDiget(int number, int i){
		int n = number/(int) Math.pow(10.0, (double) i);
		return n % 10;
	}
}
