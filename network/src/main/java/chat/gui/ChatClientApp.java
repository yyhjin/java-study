package chat.gui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClientApp {
	private static final String SERVER_IP = "192.168.0.149";
	private static final int SERVER_PORT = 8008;

	public static void main(String[] args) {
		String name = null;
		Scanner scanner = new Scanner(System.in);
		Socket socket = null;

		while( true ) {
			
			System.out.println("대화명을 입력하세요.");
			System.out.print(">>> ");
			name = scanner.nextLine();
			
			if (name.isEmpty() == false ) {
				break;
			}
			
			System.out.println("대화명은 한글자 이상 입력해야 합니다.\n");
		}
		
		scanner.close();
		
		try {
			// 1. create socket
			socket = new Socket();
			
			// 2. connect server
			socket.connect(new InetSocketAddress(SERVER_IP, SERVER_PORT));
			
			// 4. reader/writer 생성
			PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 3. join protocol 진행
			printWriter.println("join:" + name);
			String ack = br.readLine();
			if("join:ok".equals(ack)) {
				System.out.println(name+" 님 가입 성공!");
				new ChatWindow(name, socket, printWriter).show();
			}			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}

}
