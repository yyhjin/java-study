package prob04;
public class Prob04 {

	public static void main(String[] args) {
		char[] c1 = reverse( "Hello World" );
		printCharArray( c1 );
		
		char[] c2 = reverse( "Java Programming!" );
		printCharArray( c2 );
	}
	
	public static char[] reverse(String str) {
		StringBuilder sb = new StringBuilder();
		sb.append(str);
		sb.reverse();
		
		str = sb.toString();
		char[] result = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			result[i] = str.charAt(i);
		}
		
		return result;
	}

	public static void printCharArray(char[] array){
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]);			
		}
		System.out.println();
	}
}