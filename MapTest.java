package ch15_2;

import java.util.*;
import java.util.Map.*;


public class MapTest {
	public static void main(String args[]) {
//		test1();
		test2();
	}
	public static void test2() {
		/*
		 * 친구들 전화번호를 저장하는 map 객체 생성할 거임
		 * <이름, 전화번호>
		 */
		String[] names = {"고길동", "둘리", "마이콜", "희동이"};
		String[] phones = {"010-1234-5678", "010-2234-5576", "010-7895-4645","010-5464-4682"};
		Map<String, String>phoneBook = new HashMap<>();
		for (int i=0; i<names.length; i++) {
			phoneBook.put(names[i], phones[i]);
		}
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("친구 이름");
			String name = input.nextLine();
			if(name.equals("")) break;
			String phone = phoneBook.get(name);
			System.out.println(name+"의 전화번호는 "+phone);
		}
	}
	public static void test1() {
		/*
		 * Map 객체, Hashing 객체를 생성하자
		 * 맵은 <Key, value>을 쌍으로 저장하고
		 * key의 값을 통해 값을 이출한다
		 * Map이라는 높이 generic 인터페이스이고
		 * 이걸 구현한 HashMap, TreeMap, LinkHashMap 클래스들은
		 * 제네릭 클래스라는 것입니다
		 * 이 의미는
		 * 저객체들을 생성할 떄 타입을 지정해야 한다
		 */
		
		//ArrayList<Integer> list = new AarrayList<>();
		//ArrayList<String> list = new AarrayList<>();
		//ArrayList<Student> list = new AarrayList<>();
		
		//Map에 <학번, 학생객체> 이렇게 저장할 예정
		Map<String, Student> map = new HashMap<>();
		
//		map = new TreeMap<>();
		
		map.put("2001481",new Student(2001481,"고길동"));
		map.put("2001482",new Student(2001482,"둘리"));
		map.put("2001483",new Student(2001483,"마이콜"));
		map.put("2001484",new Student(2001484,"희동이"));
		map.put("2001485",new Student(2001485,"또치"));
		
		Student value = map.get("2001483");
		System.out.println(value.getName());
		map.put("2001483", new Student(2001483,"홍길동"));
		value = map.get("2001483");
		System.out.println(value.getName());
		
		Set<String> keyset = map.keySet();
		
		//set의 원소를 하나씩 접근하는 방법? ->iterator()
//		
//		Iterator<String>iter = keyset.iterator();
//		while(iter.hasNext()) { 
//			String key = iter.next(); 
//			Student val = map.get(key);
//			System.out.println("key"+key+", value"+val);
//		}
		
		//map에서 엔트리라 짧은 <key, value>의 쌍으로 구성된 객체
		//객페라 함은 이 객체를 정의하는 클래스가 있다는 의미
		Set<Entry<String, Student>>entryset = map.entrySet();
		Iterator<Entry<String, Student>> eIter = entryset.iterator();
		
		while(eIter.hasNext()) {
			Entry<String, Student>entryObj = eIter.next();
			String key = entryObj.getKey();
			Student val = entryObj.getValue();
			System.out.println("key"+key+", value"+val);
		}
		/*
		 * map이라는 저료구조에 들어가 있는 모든 원소들을 다 읽을 수 있는 방법은
		 * 1. map 에게 니가 가진 원소의 모든 키 집합을 요청하고
		 * 그 집합의 각 원소를 가지고 map이게 값을 요청하는 방법
		 * 2.map에게 <key,value>쌍으로 저장된 엔트리 집합을 요청해서
		 * 그 집합의 원소를 하나씩 읽는 방법
		 */
	}
}

class Student {
	private int hakbun;
	private String name;
	
	@Override
	public String toString() {
		return "[학번: " + hakbun + ", 이름: "+ name;
	}
	public Student(int hakbun, String name) {
		super();
		this.hakbun = hakbun;
		this.name = name;
	}
	public int getHakbun() {
		return hakbun;
	}
	public void setHakbun(int hakbun) {
		this.hakbun = hakbun;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
