package com.learning.activity5;

import com.learning.utility.HttpDownloader;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Main extends Activity {
	private static TextView info = null;
	private Button Text = null;
	private Button File = null;

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		info = (TextView)findViewById(R.id.text);
		Text = (Button)findViewById(R.id.btn1);
		File = (Button)findViewById(R.id.btn2);
		
		Text.setOnClickListener(new DownloadTextListener());
		File.setOnClickListener(new DownloadFileListener());
	}
	
	class DownloadTextListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String fileContent = null;
					HttpDownloader loader = new HttpDownloader();
					fileContent = loader.download("http://du.pfwx.com/modules/" +
							"article/packdown.php?type=txt&id=267&cid=191495");
					System.out.println(fileContent);
				}	
			};
			thread.start();
			thread = null;
			info.setText("Successful!");
		}
	}
	
	class DownloadFileListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Thread thread = new Thread(){
				@Override
				public void run() {
					// TODO Auto-generated method stub
					HttpDownloader loader = new HttpDownloader();
					int flag = loader.downFile("http://du.pfwx.com/modules/article/packdown.php?type=txt&id=267&cid=191495",
							"Download", "/novel.txt");
//					if(flag == 0){
//						info.setText("Download successful!");
//					}
//					else if(flag == -1){
//						info.setText("Download failed!");
//					}
//					else{
//						info.setText("File already exists!");
//					}
					Message msg = Myhandler.obtainMessage();
					msg.arg1 = flag;
					Myhandler.sendMessage(msg);
				}		
			};
			thread.start();
			thread = null;
		}
	}
	
	private static Handler Myhandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			int flag = msg.arg1;
			if(flag == 0){
			info.setText("Download successful!");
			}
			else if(flag == -1){
				info.setText("Download failed!");
			}
			else{
				info.setText("File already exists!");
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
