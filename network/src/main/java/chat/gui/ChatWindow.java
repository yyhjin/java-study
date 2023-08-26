package chat.gui;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatWindow {

	private Frame frame;
	private Panel pannel;
	private Button buttonSend;
	private TextField textField;
	private TextArea textArea;
	
	private PrintWriter printWriter;
	private Socket socket;

	public ChatWindow(String name, Socket socket, PrintWriter printWriter) {
		frame = new Frame(name);
		pannel = new Panel();
		buttonSend = new Button("Send");
		textField = new TextField();
		textArea = new TextArea(30, 80);
		
		this.socket = socket;
		this.printWriter = printWriter;
	}

	public void show() {
		// Button
		buttonSend.setBackground(Color.GRAY);
		buttonSend.setForeground(Color.WHITE);
		buttonSend.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ) {
				sendMessage();
			}
		});

		// 위 코드 이렇게 줄일 수 있음
//		buttonSend.addActionListener((ActionEvent e) -> {
//		});

		// Textfield
		textField.setColumns(80);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				char keyCode = e.getKeyChar();
				if(keyCode == KeyEvent.VK_ENTER) {
					sendMessage();
				}
			}
		});

		// Pannel
		pannel.setBackground(Color.LIGHT_GRAY);
		pannel.add(textField);
		pannel.add(buttonSend);
		frame.add(BorderLayout.SOUTH, pannel);

		// TextArea
		textArea.setEditable(false);
		frame.add(BorderLayout.CENTER, textArea);

		// Frame
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				finish();
			}
		});
		frame.setVisible(true);
		frame.pack();
		
		// IOStream 받아오기
		// ChatClientThread 생성하고 실행
		new ChatClientThread().start();
	}
	
	private void finish() {
		// quit 프로토콜 구현
		printWriter.println("quit");
		try {
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// exit java(JVM)
		System.exit(0);
	}
	
	private void sendMessage() {
		String message = textField.getText();
		if("quit".equals(message)) {
			finish();
			return;
		}
		if(!"".equals(message)) {
			textField.setText("");

			printWriter.println("message:" + message);
			// ChatClientThread에서 서버로부터 받은 메세지가 있다고 가정하고
		}
		textField.requestFocus();
	}
	
	private void updateTextArea(String message) {
		textArea.append(message);
		textArea.append("\n");
	}
	
	private class ChatClientThread extends Thread {
		
		@Override
		public void run() {
			try {
				updateTextArea("채팅 서버에 입장했습니다!\n");
				
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(), "utf-8"));
				
				while (true) {
					String data = br.readLine();
					if (data == null) {
						break;
					}
				
					updateTextArea(data);
				}
				
			} catch (IOException e) {
				updateTextArea("error: " + e);				
			}
		}
		
	}
}
