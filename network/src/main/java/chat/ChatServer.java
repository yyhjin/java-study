package chat;

import java.io.IOException;
import java.io.Writer;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatServer {
	private static final int PORT = 8008;
	private static List<Writer> listWriters = new ArrayList<Writer>();
	
	public static void main(String[] args) {
		
		ServerSocket serverSocket = null;
		
		try {
			// 1. 서버 소켓 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩
			String hostAddress = InetAddress.getLocalHost().getHostAddress();
			serverSocket.bind(new InetSocketAddress(hostAddress, PORT));
			log("연결 기다림 " + hostAddress + ":" + PORT);

			// 3. 요청 대기
			while (true) {
				Socket socket = serverSocket.accept();
				new ChatServerThread(socket, listWriters).start();
			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if(serverSocket != null) {
				serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	public static void log( String message ) {
		System.out.println( "[ChatServer#" + Thread.currentThread().getId() + "] " + message );
	}

}
