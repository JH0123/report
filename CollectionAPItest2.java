package ch15_2;
import java.util.*;

public class CollectionAPItest2 {
	public static void main(String args[]) {
//		shuffleTest();
		
		//이진탐색은 정렬된 리스트에서
		//내가 원하는 값을 찾아주는 방법
		binarySearchTest();
	}
	public static void binarySearchTest() {
		//1. 무작위 정수를 지정한 리스트에 대해 이진탐색 <= 잘못된 결과
		//2. 정렬된 리스트에 대해 이진탐색 <= 이렇게 사용해야 함
		Random random = new Random();
		List<Integer> list = new ArrayList<>();
		for(int i =0; i<20;i++) {
			list.add(random.nextInt(100)+1);
		}
		Collections.sort(list);
		System.out.println(list);
		
		int idx = Collections.binarySearch(list, 73);
		if(idx>=0) {
		System.out.println((idx+1)+ "번째에 있습니다");
		}else {
			System.out.println("찾고자 하는 값은 리스트에 없습니다");
		}
	}
	public static void shuffleTest() {
		List<Integer> list = new ArrayList<>();
		
//		for(int i = 0; i<10; i++) {
//			list.add((i+1)*2);
//		}
		ArrayList<Student2>list2 = new ArrayList<>();
		for(int i=0; i<10; i++) {
			Student2 std = new Student2();
			std.grade = (i+1)*100;
			list2.add(std);
		}
		System.out.println("섞기 전");
		System.out.println(list2);
		
		Collections.shuffle(list2);
		System.out.println("섞은 후");
		System.out.println(list2);
	}
}
class Student2 {
	int grade;
	public String toString() {
		return String.valueOf(grade);
	}
}
