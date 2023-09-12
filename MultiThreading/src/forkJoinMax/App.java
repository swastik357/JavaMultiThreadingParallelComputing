package forkJoinMax;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {
		long[] nums = createNumbers(30000000);
		ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
		ParallelMaxTask parallel = new ParallelMaxTask(nums,0,nums.length);
		long start = System.currentTimeMillis();
		System.out.println("Max: "+pool.invoke(parallel));
		System.out.println("Time: "+(System.currentTimeMillis()-start));
	}
	
	private static long[] createNumbers(int n) {
		Random random = new Random();
		long[] nums = new long[n];
		for(int i = 0;i<nums.length;++i)
			nums[i] = random.nextInt(1000);
		return nums;
	}

}
