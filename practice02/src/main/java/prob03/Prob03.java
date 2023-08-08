package prob03;

public class Prob03 {
	public static void main(String args[]) {
		char c[] = { 'T', 'h', 'i', 's', ' ', 'i', 's', ' ', 'a', ' ', 'p', 'e', 'n', 'c', 'i', 'l', '.' };

		// 원래 배열 원소 출력
		printCharArray(c);

		// 공백 문자 바꾸기
		c = replaceSpace(c);

		// 수정된 배열 원소 출력
		printCharArray(c);
	}

	private static char[] replaceSpace(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == ' ') arr[i] = ',';
		}
		return arr;
	}

	private static void printCharArray(char[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}

}
