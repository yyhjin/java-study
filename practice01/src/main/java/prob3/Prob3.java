package prob3;

import java.util.Scanner;

public class Prob3 {
	
	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);

		System.out.print("숫자를 입력하세요: ");
		int N = scanner.nextInt();
		int sum = 0;
		
		// 짝수
		if(N%2 == 0) {
			for (int i = 0; i <= N; i++) {
				if(i%2 == 0) sum += i;
			}
		}
		// 홀수
		else  {
			for (int i = 0; i <= N; i++) {
				if(i%2 == 1) sum += i;
			}			
		}
		
		System.out.println("결과 값 : "+sum);
		scanner.close();
	}
}
