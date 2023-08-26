package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
	private static final String SERVER_IP = "192.168.0.149";
	private static final int SERVER_PORT = 8008;

	public static void main(String[] args) {
		Scanner scanner = null;
		Socket socket = null;
		try {
			// 1. 키보드 연결
			scanner = new Scanner(System.in);
			
			// 2. socket 생성
			socket = new Socket();
			
			// 3. 연결
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			log("connected");

			// 4. reader/writer 생성
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));

			// 5. join 프로토콜
			String nickname = "";
			while(true) {
				System.out.print("닉네임>> ");
				nickname = scanner.nextLine();
				if(!"".equals(nickname))
					break;
			}
			printWriter.println("join:" + nickname);
			String ack = br.readLine();
			if("join:ok".equals(ack)) {
				System.out.println(nickname+" 님 가입 성공!");
			}

			// 6. ChatClientThread 시작
			new ChatClientThread(socket).start();

			// 7. 키보드 입력 처리
			while (true) {
				System.out.print(">> ");
				String input = scanner.nextLine();
				
				if("".equals(input)) {
					System.out.println("메시지를 입력하세요");
				} else if ("quit".equals(input)) {
					// 8. quit 프로토콜 처리
					printWriter.println("quit");
					break;
				} else {
					// 9. 메시지 처리
					printWriter.println("message:" + input);
				}
			}

		} catch (IOException ex) {
			log("error:" + ex);
		} finally {
			// 10. 자원정리
			try {
				if (scanner != null) {
					scanner.close();
				}
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void log( String message ) {
		System.out.println( "[ChatClient#" + Thread.currentThread().getId() + "] " + message );
	}

}
