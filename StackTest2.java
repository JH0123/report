package ch15;
import java.util.*;

public class StackTest2 {
	public static void main(String args[]) {
		String str = "apple banana carrot pineapple grape mango strawberry watermelon melon ornge coconut kiwi lemon cherry blueberry peach cramberry raspberry";
		
//		String s = str.substring(0, "apple".length());
//		System.out.println(s);
		
		//���� ���ڸ� �������� �ϳ��� �߶��!
		/*
		 * �����ڸ� �Է����� �ְ�, �� ������(delimiter)�� ���еǴ� 
		 * ���ڿ����� �ϳ��� �̾� �� �� �ְ� ���ִ�
		 * java.util ��Ű���� Ŭ������ StringTokennizer �̴�
		 */
		StringTokenizer st = new StringTokenizer(str," ");
		
//		String s = st.nextToken();
//		System.out.println(s);
//		
//		s = st.nextToken();
//		System.out.println(s);
		
		System.out.println("token ��: "+st.countTokens());
		//��ū�� �� ��� ���� ���1
		//��ū�� ����ŭ for�� ���
		
		//��ū�� �� ��� ���� ���2
		//���� ������ �� ��ū�� �����ִ��� �����
		//������ nextToken() �޼ҵ带 ȣ���ؼ� �ϳ��� �޾Ƽ� ����ϱ�
		//�ַ� 2��° ����� ���� ���
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			System.out.println("["+token+"]");
		}
	}
}
