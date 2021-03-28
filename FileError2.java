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
//		AutoClosable 인터페이스를 구현해야 한다
//		그 객체는 try with resources 절에 사용할 수 있다
		try (FileWriter fw = new FileWriter("out2.txt")){
			out = new PrintWriter(fw);
			out.println("Hello? I love you~~");
			System.out.println("작업종료...");
//			out.close();   적어도 상관없음
		}catch(IOException e) {
			System.out.println("catch:"+e.getMessage());
		}
	}
}
