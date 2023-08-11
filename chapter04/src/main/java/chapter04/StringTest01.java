package chapter04;

public class StringTest01 {

	public static void main(String[] args) {
		// c:\temp
		System.out.println("c:\\temp");
		
		// "Hello"
		System.out.println("\"Hello\"");
		
		/* control character */
		// \t: tab
		// \r: carriage return
		// \n: newline
		// \b: bell
		
		// '
		char c = '\'';
		System.out.println(c);
		
		// 디렉토리 경로 저장할 떄 \로 설정하지 말고 /로 설정할 것. 알아서 바꿔줌
		// 자바 실행되는 위치에 따라 드라이브는 알아서 붙여줌
	}

}
