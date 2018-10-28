import java.io.*;
import java.net.*;

public class Client {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		InetAddress IP = InetAddress.getLocalHost();
		DatagramSocket clientSocket = new DatagramSocket();
		byte send[] = new byte[1024];
		byte received[] = new byte[1024];
		
		System.out.println("Client is Ready...");
		while(true)
		{
			String msg = br.readLine();
			if(msg.equalsIgnoreCase("over"))
			{
					System.out.println("Connection Closed");
					break;
			}
			
			send = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(send, send.length, IP, 1222);
			clientSocket.send(sendPacket);
			
			DatagramPacket receivedPacket = new DatagramPacket(received, received.length);
			clientSocket.receive(receivedPacket);
			String str = new String(receivedPacket.getData());
			System.out.println(str);
			
		}	
		clientSocket.close();
		br.close();		
	}
}
