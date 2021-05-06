package Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionAPITest {
	public static void main(String args[]) {
		String[] sample = {"i", "walk", "the", "line"};
		List<String> list = Arrays.asList(sample);
	
		System.out.println("정렬전...");
		System.out.println(list);
		
		Collections.sort(list);
		System.out.println("정렬후...");
		System.out.println(list);
		
		Collections.sort(list, new ComparableTest());
		System.out.println("내림차순으로 정렬...");
		System.out.println(list);
		
//		int sum = CollectionAPItest.add(3,4);
//		System.out.println("3+4= "+sum);
	}

}
class ComparableTest implements Comparator<String> {

	@Override
	public int compare(String o1, String o2) {
		return o1.compareTo(o2)*(-1);
	}
	
}
