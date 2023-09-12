package Streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class App {

	public static void main(String[] args) {
//		int[] nums = {1,5,3,-2,9,12};
//		Arrays.stream(nums).forEach(System.out::println);
//		System.out.println(Arrays.stream(nums).sum());
//		IntStream.range(0,5).forEach(System.out::println);
//		IntStream.range(0,10).forEach(x->System.out.print(x+" "));
//		System.out.println(" ");
//		IntStream.range(0,10).filter(x->x>4).forEach(x->System.out.print(x+" "));
//		System.out.println(" ");
//		IntStream.range(0,10).filter(x->x%2!=0).forEach(x->System.out.print(x+" "));
		
		String[] names = {"Adam","Daniel","Yoku","Zenith"};
		Stream.of(names).filter(x->x.startsWith("Z")).forEach(System.out::println);
		Stream.of(names).sorted(Comparator.reverseOrder()).forEach(x->System.out.print(x+" "));
		
	}

}
