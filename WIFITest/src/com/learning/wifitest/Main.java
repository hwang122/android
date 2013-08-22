package com.learning.wifitest;

import android.app.Activity;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Main extends Activity {
	private Button startWIFI = null;
	private Button stopWIFI = null;
	private Button checkWIFI = null;
	
	private WifiManager wifiManager = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startWIFI = (Button)findViewById(R.id.startWIFI);
		stopWIFI = (Button)findViewById(R.id.stopWIFI);
		checkWIFI = (Button)findViewById(R.id.checkWIFI);
		
		startWIFI.setOnClickListener(new startListener());
		stopWIFI.setOnClickListener(new stopListener());
		checkWIFI.setOnClickListener(new checkListener());
	}
	
	class startListener implements OnClickListener{

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager)Main.this.getSystemService(WIFI_SERVICE);
			wifiManager.setWifiEnabled(true);
			
			System.out.println("WIFI state--->" + wifiManager.getWifiState());
			Toast.makeText(Main.this, "Current WIFI state" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	class stopListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager)Main.this.getSystemService(WIFI_SERVICE);
			wifiManager.setWifiEnabled(false);
			
			System.out.println("WIFI state--->" + wifiManager.getWifiState());
			Toast.makeText(Main.this, "Current WIFI state" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		}
		
	}
	
	class checkListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			wifiManager = (WifiManager)Main.this.getSystemService(WIFI_SERVICE);

			System.out.println("WIFI state--->" + wifiManager.getWifiState());
			Toast.makeText(Main.this, "Current WIFI state" + wifiManager.getWifiState(), Toast.LENGTH_SHORT).show();
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
