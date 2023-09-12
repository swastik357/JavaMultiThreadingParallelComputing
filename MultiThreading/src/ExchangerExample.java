import java.util.concurrent.Exchanger;

class FirstThread implements Runnable {
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public FirstThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while(true) {
			counter++;
			System.out.println("FirstThread incremented the counter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				System.out.println("FirstThread get the counter: " + counter);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}	
	}	
}

class SecondThread implements Runnable {
	private int counter;
	private Exchanger<Integer> exchanger;
	
	public SecondThread(Exchanger<Integer> exchanger) {
		this.exchanger = exchanger;
	}

	@Override
	public void run() {
		while(true) {
			counter--;
			System.out.println("SecondThread decremented the counter: " + counter);
			try {
				counter = exchanger.exchange(counter);
				System.out.println("SecondThread get the counter: " + counter);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}	
	}	
}


public class ExchangerExample {	
	public static void main(String[] args) {
		Exchanger<Integer> exchanger = new Exchanger<>();
		FirstThread first = new FirstThread(exchanger);
		SecondThread second = new SecondThread(exchanger);
		
		new Thread(first).start();
		new Thread(second).start();
		
	}
}