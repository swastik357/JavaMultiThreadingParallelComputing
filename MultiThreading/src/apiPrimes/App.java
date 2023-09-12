package apiPrimes;

import java.util.stream.IntStream;

public class App {
	
	public static void main(String[] args) {
		
		long currentTime = System.currentTimeMillis();
		long numOfPrimes = IntStream.rangeClosed(2,Integer.MAX_VALUE/10).filter(App::isPrime).count();
		System.out.println("Num of primes (sequential): "+numOfPrimes);
		System.out.println("Time taken (sequential): "+(System.currentTimeMillis()-currentTime));
		
		currentTime = System.currentTimeMillis();
		numOfPrimes = IntStream.rangeClosed(2,Integer.MAX_VALUE/10).parallel().filter(App::isPrime).count();
		System.out.println("Num of primes (sequential): "+numOfPrimes);
		System.out.println("Time taken (parallel): "+(System.currentTimeMillis()-currentTime));
	}
	
	public static boolean isPrime(long num) {
		if(num<=1) return false;
		if(num == 2) return true;
		if(num%2==0) return false;
		
		long maxDivisor = (long) Math.sqrt(num);
		for(int i = 3;i<=maxDivisor;i+=2)
			if(num%i == 0)
				return false;
		
		return true;
	}

}
