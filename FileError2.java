package ch14;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class FileError2 {
	public static void main(String args[]) {
		writeList();
	}
	public static void writeList() {
		PrintWriter out = null;
//		fw = new FileWriter("C:/abc/out.txt");
//		AutoClosable �������̽��� �����ؾ� �Ѵ�
//		�� ��ü�� try with resources ���� ����� �� �ִ�
		try (FileWriter fw = new FileWriter("out2.txt")){
			out = new PrintWriter(fw);
			out.println("Hello? I love you~~");
			System.out.println("�۾�����...");
//			out.close();   ��� �������
		}catch(IOException e) {
			System.out.println("catch:"+e.getMessage());
		}
	}
}
