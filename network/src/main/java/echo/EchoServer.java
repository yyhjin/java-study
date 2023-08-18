package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class EchoServer {

	private static final int PORT = 8000;
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		
		try {
			// 1. Server Socket 생성
			serverSocket = new ServerSocket();

			// 2. 바인딩 (Binding)
			serverSocket.bind(new InetSocketAddress("0.0.0.0", PORT), 10);
			log("starts...[port:" + PORT + "]");

			// 3. accept
			Socket socket = serverSocket.accept();  // blocking
			
			try {
				InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
				String remoteHostAddress = remoteInetSocketAddress.getAddress().getHostAddress();
				int remotePort = remoteInetSocketAddress.getPort();
				log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");
			
								
				while(true) {
					// 4. IO Stream 받아오기
					PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true);  // true는 auto flush 옵션
					BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));   // read는 flush 없음

					// 5. 데이터 읽기
					while(true) {
						String data = br.readLine();			
						if(data == null) {
							log("closed by client");
							break;
						}
						
						log("received: " + data);
						
						// 6. 데이터 쓰기
						pw.println(data);
					}
					
				}
				
			} catch (SocketException e) {
				log("suddenly closed by client");
			} catch (IOException e) {
				log("error: " + e);				
			} finally {
				try {
					if(socket != null && !socket.isClosed()) {
						socket.close();						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
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
		System.out.println("[EchoServer] " + message);
	}

}
