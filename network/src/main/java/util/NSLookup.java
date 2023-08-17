package util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class NSLookup {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		try {
			while (true) {
				System.out.print(">> ");
				String line = scanner.nextLine();

				if ("exit".equals("line")) {
					break;
				}
				
				InetAddress[] arr = InetAddress.getAllByName(line);
				for (InetAddress inetAddr : arr) {
					System.out.println(inetAddr.getHostName() + " : " + inetAddr.getHostAddress());
				}
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			scanner.close();
		}
		
	}

}
