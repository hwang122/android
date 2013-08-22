package learing;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClient {
	public static void main(String[] args){
		DatagramSocket socket = null;
		try{
			//创建DatagramSocket对象
			socket = new DatagramSocket(4568);
			//创建InetAddress对象
			InetAddress address = InetAddress.getByName("192.168.1.104");
			String str = "Hello";
			byte buffer[] = str.getBytes();
			
			//创建一个DatagramPacket对象，并且指定地址及端口
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, 4568);
			//发送packet
			socket.send(packet);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}
