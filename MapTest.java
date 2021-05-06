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
		 * ģ���� ��ȭ��ȣ�� �����ϴ� map ��ü ������ ����
		 * <�̸�, ��ȭ��ȣ>
		 */
		String[] names = {"��浿", "�Ѹ�", "������", "����"};
		String[] phones = {"010-1234-5678", "010-2234-5576", "010-7895-4645","010-5464-4682"};
		Map<String, String>phoneBook = new HashMap<>();
		for (int i=0; i<names.length; i++) {
			phoneBook.put(names[i], phones[i]);
		}
		Scanner input = new Scanner(System.in);
		while(true) {
			System.out.println("ģ�� �̸�");
			String name = input.nextLine();
			if(name.equals("")) break;
			String phone = phoneBook.get(name);
			System.out.println(name+"�� ��ȭ��ȣ�� "+phone);
		}
	}
	public static void test1() {
		/*
		 * Map ��ü, Hashing ��ü�� ��������
		 * ���� <Key, value>�� ������ �����ϰ�
		 * key�� ���� ���� ���� �����Ѵ�
		 * Map�̶�� ���� generic �������̽��̰�
		 * �̰� ������ HashMap, TreeMap, LinkHashMap Ŭ��������
		 * ���׸� Ŭ������� ���Դϴ�
		 * �� �ǹ̴�
		 * ����ü���� ������ �� Ÿ���� �����ؾ� �Ѵ�
		 */
		
		//ArrayList<Integer> list = new AarrayList<>();
		//ArrayList<String> list = new AarrayList<>();
		//ArrayList<Student> list = new AarrayList<>();
		
		//Map�� <�й�, �л���ü> �̷��� ������ ����
		Map<String, Student> map = new HashMap<>();
		
//		map = new TreeMap<>();
		
		map.put("2001481",new Student(2001481,"��浿"));
		map.put("2001482",new Student(2001482,"�Ѹ�"));
		map.put("2001483",new Student(2001483,"������"));
		map.put("2001484",new Student(2001484,"����"));
		map.put("2001485",new Student(2001485,"��ġ"));
		
		Student value = map.get("2001483");
		System.out.println(value.getName());
		map.put("2001483", new Student(2001483,"ȫ�浿"));
		value = map.get("2001483");
		System.out.println(value.getName());
		
		Set<String> keyset = map.keySet();
		
		//set�� ���Ҹ� �ϳ��� �����ϴ� ���? ->iterator()
//		
//		Iterator<String>iter = keyset.iterator();
//		while(iter.hasNext()) { 
//			String key = iter.next(); 
//			Student val = map.get(key);
//			System.out.println("key"+key+", value"+val);
//		}
		
		//map���� ��Ʈ���� ª�� <key, value>�� ������ ������ ��ü
		//����� ���� �� ��ü�� �����ϴ� Ŭ������ �ִٴ� �ǹ�
		Set<Entry<String, Student>>entryset = map.entrySet();
		Iterator<Entry<String, Student>> eIter = entryset.iterator();
		
		while(eIter.hasNext()) {
			Entry<String, Student>entryObj = eIter.next();
			String key = entryObj.getKey();
			Student val = entryObj.getValue();
			System.out.println("key"+key+", value"+val);
		}
		/*
		 * map�̶�� ���ᱸ���� �� �ִ� ��� ���ҵ��� �� ���� �� �ִ� �����
		 * 1. map ���� �ϰ� ���� ������ ��� Ű ������ ��û�ϰ�
		 * �� ������ �� ���Ҹ� ������ map�̰� ���� ��û�ϴ� ���
		 * 2.map���� <key,value>������ ����� ��Ʈ�� ������ ��û�ؼ�
		 * �� ������ ���Ҹ� �ϳ��� �д� ���
		 */
	}
}

class Student {
	private int hakbun;
	private String name;
	
	@Override
	public String toString() {
		return "[�й�: " + hakbun + ", �̸�: "+ name;
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
