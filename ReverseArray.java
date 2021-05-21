package programmers;

import java.util.Scanner;

public class ReverseArray {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("자연수를 입력해 주세요");
		String num = sc.next();
		
		for(int i = num.length()-1; i>=0; i--) {
			System.out.print(num.charAt(i));
			
			}
	}
}
