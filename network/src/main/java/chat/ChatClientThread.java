package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ChatClientThread extends Thread {
	private Socket socket;
	
	public ChatClientThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			log("채팅 서버에 입장했습니다!");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
			
			while (true) {
				String data = br.readLine();
				if (data == null) {
					log("closed by client");
					break;
				}
				
				log(data);
			}
			
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
	
	public void log( String message ) {
		System.out.println( message );
	}
}
