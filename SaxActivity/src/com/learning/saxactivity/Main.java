package com.learning.saxactivity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	
	private Button parse = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		parse = (Button)findViewById(R.id.btn1);
		parse.setOnClickListener(new parseListener());
	}

	class parseListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			String XML = null;
			try {
				XML = XML2String("/teacher.xml");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try{
				SAXParserFactory factory = SAXParserFactory.newInstance();
				XMLReader reader = factory.newSAXParser().getXMLReader();
				
				reader.setContentHandler(new MyContentHandler());
				reader.parse(new InputSource(new StringReader(XML)));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}
	
	public String XML2String(String path) throws Exception{
		FileReader file = new FileReader(Environment.getExternalStorageDirectory().getPath()+ path);
		
//		if (!file.exists() || file.isDirectory()) { 
//			System.out.println("test");
//	        throw new FileNotFoundException();  
//	    }  
		
//		InputStreamReader inputReader = null;
		BufferedReader br = null;
		String line = null;
		StringBuffer sb = new StringBuffer();
		
		br= new BufferedReader(file);
		line = br.readLine();
		while(line != null){
			sb.append(line);
			line = br.readLine();
		}
		
		br.close();
//		read.close();
		
		return sb.toString();	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
