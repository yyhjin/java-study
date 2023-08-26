package chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ChatServerThread extends Thread {

	private String nickname;
	private Socket socket;
	private List<Writer> listWriters;

	public ChatServerThread(Socket socket, List<Writer> listWriters) {
		this.socket = socket;
		this.listWriters = listWriters;
	}

	@Override
	public void run() {
		BufferedReader bufferedReader = null;
		PrintWriter printWriter = null;

		// 1. Remote Host Information

		// 2. 스트림 얻기
		try {
			InetSocketAddress remoteInetSocketAddress = (InetSocketAddress)socket.getRemoteSocketAddress();
			String remoteHostAddress = remoteInetSocketAddress.getAddress().getHostAddress();
			int remotePort = remoteInetSocketAddress.getPort();
			log("connected by client[" + remoteHostAddress + ":" + remotePort + "]");
			
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8),
					true);
			
			// 3. 요청 처리
			while (true) {
				String request = bufferedReader.readLine();
				if (request == null) {
					log("클라이언트로 부터 연결 끊김");
					doQuit(printWriter);
					break;
				}

				// 4. 프로토콜 분석
				String[] tokens = request.split(":");

				if ("join".equals(tokens[0])) {
					doJoin(tokens[1], printWriter);
				} else if ("message".equals(tokens[0])) {
					doMessage(tokens[1]);
				} else if ("quit".equals(tokens[0])) {
					doQuit(printWriter);
					break;
				} else {
					ChatServer.log("에러:알수 없는 요청(" + tokens[0] + ")");
				}

			}
		} catch (IOException e) {
			log("error: " + e);
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				if (printWriter != null) {
					printWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void doQuit(Writer writer) {
		removeWriter(writer);

		String data = "\n" + nickname + "님이 퇴장 하였습니다.\n";
		broadcast(data, "quit");
	}

	private void addWriter(Writer writer) {
		synchronized (listWriters) {
			listWriters.add(writer);
		}
	}

	private void removeWriter(Writer writer) {
		/* 잘 구현 해보기 */
		synchronized (listWriters) {
			listWriters.remove(writer);
		}
	}

	private void doMessage(String message) {
		broadcast(message, "msg");
	}

	private void doJoin(String nickName, PrintWriter writer) {
		this.nickname = nickName;

		String data = "\n" + nickName + "님이 참여하였습니다.\n";
		broadcast(data, "join");

		/* writer pool에 저장 */
		addWriter(writer);

		// ack
		writer.println("join:ok");
//		writer.flush();
	}

	private void broadcast(String data, String action) {
		synchronized (listWriters) {
			for (Writer writer : listWriters) {
				System.out.println(data);
				PrintWriter printWriter = (PrintWriter) writer;
				if("msg".equals(action)) {
					printWriter.print(nickname + ": ");
				}
				printWriter.println(data);
				printWriter.flush();
			}
		}
	}

	public void log(String message) {
		System.out.println("[ChatServerThread#" + getId() + "] " + message);
	}
}
