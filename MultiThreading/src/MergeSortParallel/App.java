package MergeSortParallel;

import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		int numOfThreads = Runtime.getRuntime().availableProcessors();
		int[] numbers1 = createArray(100000000);
		int[] numbers2 = new int[numbers1.length];
		
		
		for(int t = 1;t<=8;t++) {
			for(int i = 0;i<numbers1.length;i++)
				numbers2[i] = numbers1[i];
			ParallelMergeSort parallelSorter = new ParallelMergeSort(numbers2);
			long startTime = System.currentTimeMillis();
			parallelSorter.parallelMergeSort(0,numbers2.length-1,t);
			long endTime = System.currentTimeMillis();
			System.out.printf("Time taken with %d processors: %6d ms\n",t,endTime-startTime);
		}
//		for(int i = 0;i<numbers1.length;i++)
//			numbers2[i] = numbers1[i];
//		
		
		
//		ParallelMergeSort parallelSorter = new ParallelMergeSort(numbers1);
//		ParallelMergeSort sequentialSorter = new ParallelMergeSort(numbers2);
//		
//		long startTime = System.currentTimeMillis();
//		parallelSorter.parallelMergeSort(0,numbers1.length-1,numOfThreads);
//		long endTime = System.currentTimeMillis();
//		System.out.printf("Time taken with parallel: %6d ms\n",endTime-startTime);
//		
//		startTime = System.currentTimeMillis();
//		sequentialSorter.parallelMergeSort(0,numbers2.length-1,1);
//		endTime = System.currentTimeMillis();
//		System.out.printf("Time taken with sequential: %6d ms\n",endTime-startTime);
		

	}
	
	private static int[] createArray(int n) {
		Random random = new Random();
		int[] a = new int[n];
		for(int i = 0;i<n;++i)
			a[i] = random.nextInt(n);
		return a;
	}

}
