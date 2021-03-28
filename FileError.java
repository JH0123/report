package ch14;
import java.io.*;

public class FileError {
	public static void main(String args[]) {
		writeList();
	}
	public static void writeList() {
		PrintWriter out = null;
		FileWriter fw = null;
		try {
//			fw = new FileWriter("C:/abc/out.txt");
			fw = new FileWriter("out2.txt");
			out = new PrintWriter(fw);
			out.println("안녕하세용~~~");
			System.out.println("작업종료...");
//			out.close();   적어도 상관없음
		}catch(IOException e) {
			System.out.println("catch:"+e.getMessage());
		}finally {
			System.out.println("finally code...");
			if(out !=null)		
				out.close();
			System.out.println("finally end...");
		}
	}
}
