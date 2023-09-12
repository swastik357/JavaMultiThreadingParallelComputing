package forkJoinFibonacci;

import java.util.concurrent.RecursiveTask;

public class SimpleRecursiveTask extends RecursiveTask<Integer> {

	private int num;
	
	public SimpleRecursiveTask(int num) {
		this.num = num;
	}
	
	@Override
	protected Integer compute() {
		if(num>1) {
			System.out.println("Parallel execution so split the task..."+num);
			SimpleRecursiveTask task1 = new SimpleRecursiveTask(num-1);
			SimpleRecursiveTask task2 = new SimpleRecursiveTask(num-2);
//			task1.fork();
			task2.fork();
			
//			int subSolution = 0;
//			subSolution+=task1.join();
//			subSolution+=task2.join();
//			return subSolution;
			return task1.compute()+task2.join();
		} else {
			System.out.println("Sequential execution for the task..."+num);
			return num;
		}
	}

}
