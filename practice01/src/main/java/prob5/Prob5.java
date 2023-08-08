package prob5;

public class Prob5 {

	public static void main(String[] args) {
		for (int i = 1; i < 100; i++) {
			String s = Integer.toString(i);
			
			int cnt = 0;
			for (int j = 0; j < s.length(); j++) {
				if(s.charAt(j)=='3' || s.charAt(j)=='6' || s.charAt(j)=='9')
					cnt++;
			}
			
			if(cnt > 0) {
				System.out.print(i+" ");
				for (int j = 0; j < cnt; j++) {
					System.out.print("짝");
				}
				System.out.println();
			}
		}
	}
}
