import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket serverSocket = new DatagramSocket(1055);
		byte send[] = new byte[1024];
		byte received[] = new byte[1024];
		
		System.out.println("Server is up and running...");
		while(true)
		{
			DatagramPacket receivedPacket = new DatagramPacket(received, received.length);
			serverSocket.receive(receivedPacket);
			received = receivedPacket.getData();
			String str = new String(received);
			System.out.println(str);
			
			String msg = br.readLine();
			if(msg.equalsIgnoreCase("over"))
			{
					System.out.println("Connection Closed");
					break;
			}
			
			InetAddress IP = receivedPacket.getAddress();
			send = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(send, send.length, IP, receivedPacket.getPort());
			serverSocket.send(sendPacket);
		}
		serverSocket.close();
		br.close();
	}
}
