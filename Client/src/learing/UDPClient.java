package learing;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args){
		DatagramSocket socket = null;
		try{
			//����DatagramSocket����
			socket = new DatagramSocket(4568);
			//����InetAddress����
			InetAddress address = InetAddress.getByName("192.168.1.104");
			String str = "Hello";
			byte buffer[] = str.getBytes();
			
			//����һ��DatagramPacket���󣬲���ָ����ַ���˿�
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4568);
			//����packet
			socket.send(packet);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
