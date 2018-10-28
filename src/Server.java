import java.io.*;
import java.net.*;

public class Server {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket serverSocket = new DatagramSocket(1222);
		byte send[] = new byte[1024];
		byte received[] = new byte[1024];
		
		System.out.println("Server is running...");
		while(true)
		{
			DatagramPacket receivedPacket = new DatagramPacket(received, received.length);
			serverSocket.receive(receivedPacket);
			String str = new String(receivedPacket.getData());
			System.out.println(str);
			
			InetAddress IP = receivedPacket.getAddress();
			int portNo = receivedPacket.getPort();
			String msg = br.readLine();
			if(msg.equalsIgnoreCase("over"))
			{
					System.out.println("Connection Closed");
					break;
			}
			
			send = msg.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(send, send.length, IP, portNo);
			serverSocket.send(sendPacket);
		}
		serverSocket.close();
		br.close();
	}
}
