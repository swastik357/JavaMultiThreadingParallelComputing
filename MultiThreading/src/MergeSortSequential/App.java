package MergeSortSequential;

public class App {

	public static void main(String[] args) {
		int[] nums = {5,-6,0,7,2,3,2,1,0,1,2};
		MergeSort sort = new MergeSort(nums);
		sort.sort();
		sort.showArray();
		
	}

}
