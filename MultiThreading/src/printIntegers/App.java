package printIntegers;
import java.util.concurrent.ForkJoinPool;


public class App {
	

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		int[] nums = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		SimpleRecursiveAction action = new SimpleRecursiveAction(0,nums.length-1,nums);
		action.invoke();
	}

}