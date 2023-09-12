package MergeSortParallel;

public class ParallelMergeSort {
	private int[] nums;
	private int[] tempArray;
	
	public ParallelMergeSort(int[] nums) {
		this.nums = nums;
		this.tempArray = new int[nums.length];
	}
	
	public void parallelMergeSort(int low,int high,int numOfThreads) {
		if(numOfThreads<=1) {
			mergeSort(low,high);
			return;
		}
		int middleIndex = (low+high)/2;
		Thread leftSorter = createThread(low,middleIndex,numOfThreads);
		Thread rightSorter = createThread(middleIndex+1,high,numOfThreads);
		
		leftSorter.start();
		rightSorter.start();
		
		try {
			leftSorter.join();
			rightSorter.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		merge(low,middleIndex,high);
	}
	
	private Thread createThread(int low, int high, int numOfThreads) {
		return new Thread() {
			@Override
			public void run() {
				parallelMergeSort(low,high,numOfThreads/2);
			}
		};
	}
	private void mergeSort(int low, int high) {
		if(low>=high)
			return;
		
		int middleIndex = (low+high)/2;
		mergeSort(low,middleIndex);
		mergeSort(middleIndex+1,high);
		merge(low,middleIndex,high);
	}
	
	private void merge(int low, int middle, int high) {
		for(int i = low;i<=high;++i)
			tempArray[i] = nums[i];
		
		int i = low;
		int j = middle+1;
		int k = low;
		
		while(i<=middle && j<=high) {
			if(tempArray[i]<=tempArray[j]) {
				nums[k] = tempArray[i];
				++i;
			} else {
				nums[k] = tempArray[j];
				++j;
			}
			++k;
		}
		while(i<=middle) {
			nums[k] = tempArray[i];
			++k;
			++i;
		}
		while(j<=high) {
			nums[k] = tempArray[j];
			++k;
			++j;
		}
	}
	
	public void showArray() {
		for(int i = 0;i<nums.length;++i)
			System.out.print(nums[i]+" ");
	}

}
