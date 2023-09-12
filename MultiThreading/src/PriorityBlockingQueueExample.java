import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

class PriorityFirstWorker implements Runnable {
	private BlockingQueue<Person> queue;
	
	public PriorityFirstWorker(BlockingQueue<Person> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			queue.put(new Person(12,"Adam"));
			queue.put(new Person(55,"Kevin"));
			queue.put(new Person(27,"Ana"));
			Thread.sleep(2000);
			queue.put(new Person(31,"Daniel"));
			Thread.sleep(1000);
			queue.put(new Person(15,"Joe"));
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class PrioritySecondWorker implements Runnable {
	private BlockingQueue<Person> queue;
	
	public PrioritySecondWorker(BlockingQueue<Person> queue) {
		this.queue = queue;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println(queue.take());
			Thread.sleep(1000);
			System.out.println(queue.take());
			Thread.sleep(2000);
			System.out.println(queue.take());
			System.out.println(queue.take());
			System.out.println(queue.take());
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Person implements Comparable<Person> {
	private int age;
	private String name;
	
	public Person(int age, String name) {
		this.age = age;
		this.name = name;
	}
	
	@Override
	public int compareTo(Person person) {
		//return Integer.compare(this.age,person.getAge());
		return name.compareTo(person.getName());
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [age=" + age + ", name=" + name + "]";
	}
	
}

public class PriorityBlockingQueueExample {
	public static void main(String[] args) {
		BlockingQueue<Person> queue = new PriorityBlockingQueue<>();
		PriorityFirstWorker first = new PriorityFirstWorker(queue);
		PrioritySecondWorker second = new PrioritySecondWorker(queue);
		
		new Thread(first).start();
		new Thread(second).start();
		
	}
}