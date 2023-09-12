package printIntegers;
import java.util.concurrent.RecursiveAction;


public class SimpleRecursiveAction extends RecursiveAction {
	private int low;
	private int high;
	private int[] nums;
	
	
	public SimpleRecursiveAction(int low, int high, int[] nums) {
		this.low = low;
		this.high = high;
		this.nums = nums;
	}

	@Override
	protected void compute() {
		if(low < high) {
//			System.out.println("Parallel execution and split the tasks..." + low + " "+ high);
			int mid = (low+high)/2;
			SimpleRecursiveAction action1 = new SimpleRecursiveAction(low,mid,nums);
			SimpleRecursiveAction action2 = new SimpleRecursiveAction(mid+1,high,nums);
			
			invokeAll(action1,action2);
		} else {
			System.out.println("Sequential execution..." + nums[low]);
		}	
	}
}
