package com.learning.sockettest;

import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	private Button tcp = null;
	private Button udp = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tcp = (Button)findViewById(R.id.tcp);
		udp = (Button)findViewById(R.id.udp);
		
		tcp.setOnClickListener(new tcpListener());
		udp.setOnClickListener(new udpListener());
	}
	
	class tcpListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(new tcpThread());
			thread.start();
		}
		
	}
	
	class udpListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(new udpThread());
			thread.start();
		}
		
	}
	
	class tcpThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			ServerSocket serverSocket = null;
			
			try{
				//����һ��serversocket���󣬼���4567�˿�
				serverSocket = new ServerSocket(4567);
				//����Accept()���������ܿͻ��˷�����������������
				Socket socket = serverSocket.accept();
				//��socket�л�ȡinputstream
				InputStream input = socket.getInputStream();
				System.out.println("Got inputStream");
				byte[] buffer = new byte[4*1024];
				int temp = 0;
				
				while((temp = input.read(buffer)) != -1){
					System.out.println(new String(buffer, 0, temp));
				}
			}
			catch(IOException e){
				e.printStackTrace();
			}
			finally{
				try{
					serverSocket.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}		
	}
	
	class udpThread implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			try{
				//����һ��datagramsocket����
				DatagramSocket socket = new DatagramSocket(4568);
				
				//����һ��datagrampacket���󣬻�ȡ�ͻ��˷��͹�������
				byte data[] = new byte[1024];
				DatagramPacket packet = new DatagramPacket(data, data.length);
				
				//��receive()�������ܿͻ������ݣ���������
				socket.receive(packet);
				
				String result = new String(packet.getData(), packet.getOffset(), packet.getLength());
				
				System.out.println("result---->" + result);
 			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
