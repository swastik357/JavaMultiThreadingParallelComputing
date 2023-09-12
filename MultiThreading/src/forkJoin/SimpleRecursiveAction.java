package forkJoin;

import java.util.concurrent.RecursiveAction;

public class SimpleRecursiveAction extends RecursiveAction {
	private int simulatedWork;
	
	public SimpleRecursiveAction(int simulatedWork) {
		this.simulatedWork = simulatedWork;
	}

	@Override
	protected void compute() {
		if(simulatedWork>100) {
			System.out.println("Parallel execution and split the tasks..." + simulatedWork);
			SimpleRecursiveAction action1 = new SimpleRecursiveAction(simulatedWork/2);
			SimpleRecursiveAction action2 = new SimpleRecursiveAction(simulatedWork-simulatedWork/2);
			
			invokeAll(action1,action2);
		} else {
			System.out.println("Sequential execution..." + simulatedWork);
		}
		
	}

}
