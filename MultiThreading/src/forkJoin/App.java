package forkJoin;

import java.util.concurrent.ForkJoinPool;

public class App {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		SimpleRecursiveAction action = new SimpleRecursiveAction(937);
		action.invoke();
	}

}
