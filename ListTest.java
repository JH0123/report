package ch15;
import java.util.*;

public class ListTest {
	public static void main(String args[]) {
		test1();
	}
	public static void test1() {
//		List<String> list = new ArrayList<>();
		List<String> list = new LinkedList<>();
		String[] sArr = {"�ڵ���","���","��ġ��","�б�","��äȯ"};
//		list.add("�ڵ���");
		for(String s : sArr) list.add(s);
		System.out.println(list); //ArrayList�� toString() �޼ҵ尡 �̿��
		
		for(int i=0; i<list.size();i++) {
			System.out.println(list.get(i)+" ");
			System.out.println();
			
		for(String s : list) System.out.print(s+" ");
		System.out.println();
		
		Iterator<String> iter = list.iterator(); //Iterator ��ü
		//unchecked exception�� ���״�
		//�̷� exception ��ü�� �߻����� �ʰ� �ڵ��ض�!
		while(iter.hasNext())
			System.out.println(iter.next()+" ");
		System.out.println();
		
		//overloading�� add �޼ҵ�� ���Ҹ� �߰��� ���Զ� �� �ִ�
		list.add(1,"����"); //2��°�� ���� ����
		
		iter = list.iterator();
			System.out.println(iter.next()+" ");
		System.out.println();
			
//		list.remove(list.size()-1); //������ ���� �����ϰ� �ʹ� -> ���� ��-1
//		while(list.size()>0) {
//			list.remove(0);
//		}
		list.remove(3); //4��° ���� ����
		System.out.println(list);
		}
	}
}