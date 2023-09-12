package ParallelSum;

import java.util.Random;

public class App {

	public static void main(String[] args) {
		
		Random random = new Random();
		int N = 100000000;
		int[] nums = new int[N];
		
		for(int i = 0;i<N;++i)
			nums[i] = random.nextInt(10);
		
		int n = Runtime.getRuntime().availableProcessors();
		
		for(int i =1;i<=n;i++) {
			long startTime = System.currentTimeMillis();
			ParallelSum parallel  = new ParallelSum(i);
		    int res = parallel.sum(nums);
			long endTime = System.currentTimeMillis();
			System.out.printf("Time taken to calculate %d with %d processors: %6d ms\n",res,i,endTime-startTime);
		}		
	}
}
