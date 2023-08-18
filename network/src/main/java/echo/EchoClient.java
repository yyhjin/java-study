package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class EchoClient {
	
//	private static final String SERVER_IP = "127.0.0.1";
	private static final String SERVER_IP = "192.168.0.149";

	public static void main(String[] args) {
		Socket socket = null;
		Scanner scanner = null;
		
		try {
			// 1. 소켓 생성
			socket = new Socket();
			
			// 2. 서버 연결
			socket.connect(new InetSocketAddress(SERVER_IP, EchoServer.PORT));
			log("connected");
			
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 4. 쓰기
			scanner = new Scanner(System.in);
			
			while(true) {
				System.out.print(">> ");
				String line = scanner.nextLine();
				
				if("exit".equals(line)) {
					break;
				}
				
				pw.println(line);
				
				// 5. 읽기
				String data = br.readLine();
				
				if(data == null) {
					// 서버가 정상적으로 close() 호출
					log("closed by server");
					break;
				}
				
				System.out.println("<< " + data);
			}

		} catch (SocketException e) {
			System.out.println("[client] suddenly closed by server");
		} catch (IOException e) {
			System.out.println("[client] error: " + e);
		} finally {
			try {
				if(socket != null && !socket.isClosed()) {
					socket.close();					
				}
				if(scanner != null) {
					scanner.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private static void log(String message) {
		System.out.println("[EchoClient] " + message);
	}

}
