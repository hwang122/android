package learing;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
	public static void main(String[] args){
		Socket socket = null;
		
		try{
			//����һ��socket����
			socket = new Socket("127.0.0.1", 4567);
			//ʹ��InputStream��ȡ�ļ�
			InputStream input = new FileInputStream("src/test.txt");
			//��socket�еõ�OutputStream
			OutputStream out = socket.getOutputStream();
			byte[] buffer = new byte[4*1024];
			int temp = 0;
			
			while((temp = input.read(buffer)) != -1){
				out.write(buffer, 0, temp);
			}
			out.flush();
			input.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		finally{
			try{
				socket.close();
			}
			catch(IOException e){
				e.printStackTrace();
			}
		}
	}

}
