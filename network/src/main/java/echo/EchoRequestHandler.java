package echo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;

public class EchoRequestHandler extends Thread {
	private Socket socket;
	
	public EchoRequestHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = remoteInetSocketAddress.getAddress().getHostAddress();
			int remotePort = remoteInetSocketAddress.getPort();
			log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");
			
			// auto flush 옵션 : true
			PrintWriter pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "utf-8"), true); 
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			// 5. 데이터 읽기
			while (true) {
				String data = br.readLine();
				if (data == null) {
					log("closed by client");
					break;
				}
				
				log("received: " + data);
				
				// 6. 데이터 쓰기
				pw.println(data);
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
		
	}

	private void log(String message) {
		System.out.println("[EchoServer#" + getId() +"] " + message);
	}
	
}
