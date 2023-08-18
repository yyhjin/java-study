package echo;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

	public static final int PORT = 8000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩 (Binding)
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			log("starts...[port:" + PORT + "]");

			// 3. accept
			while(true) {
				Socket socket = serverSocket.accept();  // blocking
				new EchoRequestHandler(socket).start();
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
	
	private static void log(String message) {
		System.out.println("[EchoServer#" + Thread.currentThread().getId() + "]" + message);
	}

}
