package Test;

import java.util.*;

public class PriorityQueueTest {
	public static void main(String args[]) {
		test1();
		
	}
	public static void test1() {
		/*
		 * 우선순위큐 객체를 생성하고
		 * Task 인스턴스를 삽입, 인출해 보자 
		 */
		//우선순위큐: 기본적으로 오름차순으로 출력
//	Queue<Task> queue = new PriorityQueue<>();
	Queue<Task> queue = new PriorityQueue<>(new TaskComparator());
	queue.offer(new Task("작업1",3));
	queue.offer(new Task("작업2",2));
	queue.offer(new Task("작업3",5));
	queue.offer(new Task("작업4",1));
	queue.offer(new Task("작업5",4));
	//작업4,작업2,작업1,작업5,작업3
	
	while(queue.isEmpty()== false) {
		Task task = queue.poll();
		
		System.out.println(task);
		}
	}
}
//Task 인스턴스를 비교가능한 객체로 생성하기 위해 Comparable 인터페이스 구현
class Task implements  Comparable<Task>{
	String desc; //성명
	int priority = 5; //이 작업의 우선순위
	
	@Override  //Object 클래스에 정의된 toString 메소드를 재정의 하는것
	public String toString() {
		return "[desc:"+desc+",priority: "+priority+"]";
	}
	
	public Task(String desc, int priority) {
		this.desc= desc;
		this.priority = priority;
	}

	@Override
	public int compareTo(Task o) {
		// TODO Auto-generated method stub
		//이 객체의 값이 크면 양수, 같으면 0, 작으면 음수를 반환
		return this.priority - o.priority;
//		return (-1)* (this.priority - o.priority); //작업3,작업5,작업1,작업2,작업4
	}	
}

class TaskComparator implements Comparator<Task>{

	@Override
	public int compare(Task o1, Task o2) {
		// TODO Auto-generated method stub
//		return o1.priority - o2.priority;
		return (-1)* (o1.priority - o2.priority); //내림차순으로 정리
	}
	
}

